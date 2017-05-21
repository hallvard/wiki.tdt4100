package ord2017.stage3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A place where groups of guests can buy a meal
 */
public class Diner {
	
	private Collection<Table> tables = new ArrayList<>();	
	private Collection<Seating> seatings = new ArrayList<>();

	// to support testing
	int getTableCount() {
		return tables.size();
	}

	// to support testing
	Table getTable(int n) {
		Iterator<Table> it = tables.iterator();
		while (n > 0) {
			it.next();
		}
		return it.next();
	}

	/**
	 * Tells whether a Table is occupied.
	 * @param table the Table to check
	 * @return true if anyone is sitting at the provided Table
	 */
	public boolean isOccupied(Table table) {
		return seatings.stream().anyMatch(s -> s.getTable() == table);
	}
	
	/**
	 * Computes the guest capacity,
	 * either the remaining (includeOccupied == false) or total (includeOccupied).
	 * @param includeOccupied controls whether to include tables that are occupied.
	 * @return the guest capacity
	 */
	public int getCapacity(boolean includeOccupied) {
		Stream<Table> stream = tables.stream();
		if (! includeOccupied) {
			stream = stream.filter(t -> ! isOccupied(t));
		}
		return stream.mapToInt(Table::getCapacity).sum();
	}
	
	/**
	 * Adds a table to this Diner
	 * @param table
	 */
	public void addTable(Table table) {
		tables.add(table);
		fireCapacityChanged();
	}

	/**
	 * Removes a Table from this Diner.
	 * If the table is occupied an IllegalArgumentException exception should be thrown.
	 * @param table
	 * @throws IllegalArgumentException
	 */
	public void removeTable(Table table) {
		if (isOccupied(table)) {
			throw new IllegalArgumentException("Cannot remove an occupied table");
		}
		tables.remove(table); 
		fireCapacityChanged();
	}
	
	/**
	 * Merges two tables, i.e. replaces two tables with one table.
	 * lostCapacity is the difference between the old capacity and the new.
	 * This number is typically positive, since seats are lost when moving two tables close to each other.
	 * @param table1
	 * @param table2
	 * @param lostCapacity
	 * @throws IllegalArgumentException if any of the tables are occupied
	 */
	public void mergeTables(Table table1, Table table2, int lostCapacity) {
		CompositeTable table = new CompositeTable(table1, table2, lostCapacity);
		removeTable(table1);
		removeTable(table2);
		addTable(table);
	}

	/**
	 * Splits a table into two, i.e. replaces one tables with two tables.
	 * The two capacities are the capacities of the two new tables.
	 * @param table
	 * @param capacity1
	 * @param capacity2
	 * @throws IllegalArgumentException if any of the table is occupied
	 */
	public void splitTable(CompositeTable table, int capacity1, int capacity2) {
		removeTable(table);
		addTable(table.getTable1());
		addTable(table.getTable2());
	}

	/**
	 * Tells whether a table has the provided capacity,
	 * i.e. if that number of new guests can be seated there.
	 * Note that a table cannot be shared among different groups.
	 * @param table
	 * @param capacity
	 * @return true of capacity number of guests can be seated here, false otherwise.
	 */
	public boolean hasCapacity(Table table, int capacity) {
		return (! isOccupied(table)) && table.getCapacity() >= capacity;
	}

	/**
	 * Returns the tables that has the provided capacity.
	 * The tables should be sorted with the one with the least capacity (but enough) first.
	 * @param capacity
	 * @return the tables that has the provided capacity
	 */
	public Collection<Table> findAvailableTables(int capacity) {
		return tables.stream().
				filter(t -> (! isOccupied(t)) && t.getCapacity() >= capacity).
				sorted((t1, t2) -> t1.getCapacity() - t2.getCapacity()).
				collect(Collectors.toList());
	}
	
	/**
	 * Finds a suitable table for the provided group, and creates(but doesn't add) a corresponding Seating.
	 * The chosen table should be the one with the least capacity.
	 * @param group the group to be seated
	 * @return the newly created Seating
	 * @throws IllegalArgumentException if Group already is seated (in this Diner)
	 */
	public Seating createSeating(Group group) {
		for (Seating seating : seatings) {
			if (seating.getGroup() == group) {
				throw new IllegalArgumentException("Group is already seated");
			}
		}
		Collection<Table> availableTables = findAvailableTables(group.getGuestCount());
		if (availableTables.isEmpty()) {
			return null;
		}
		return new Seating(availableTables.iterator().next(), group);
	}
	
	/**
	 * Creates and adds a Seating for the provided group, using the createSeating method.
	 * @param group
	 * @return true if a Seating was created and added, false otherwise.
	 */
	public boolean addSeating(Group group) {
		Seating seating = createSeating(group);
		if (seating != null) {
			addSeating(seating);
			return true;
		}
		return false;
	}

	// to support testing
	void addSeating(Seating seating) {
		seatings.add(seating);
		fireCapacityChanged();
	}

	private boolean isTable(Table table, int tableNum) {
		if (table instanceof SimpleTable) {
			return (((SimpleTable) table).getNum() == tableNum);
		} else if (table instanceof CompositeTable) {
			CompositeTable comp = (CompositeTable) table;
			if (isTable(comp.getTable1(), tableNum) || isTable(comp.getTable2(), tableNum)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Removes the seating for the provided table (number), if one exists
	 * @param tableNum the number of the table to be removed
	 */
	public void removeSeating(int tableNum) {
		removeSeating(findSeating(tableNum));
	}

	private Seating findSeating(int tableNum) {
		for (Seating seating : seatings) {
			if (isTable(seating.getTable(), tableNum)) {
				return seating;
			}
		}
		return null;
	}
	
	private void removeSeating(Seating seating) {
		if (seating != null) {
			seatings.remove(seating);
			fireCapacityChanged();
		}
	}
	
	//

	private Collection<CapacityListener> capacityListeners = new ArrayList<>();
	
	private void fireCapacityChanged() {
		for (CapacityListener listener : capacityListeners) {
			listener.capacityChanged(this);
		}
	}
	
	public void addCapacityListener(CapacityListener listener) {
		capacityListeners.add(listener);
	}
	
	public void removeCapacityListener(CapacityListener listener) {
		capacityListeners.remove(listener);
	}
}
