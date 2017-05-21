package ord2017.stage2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	
	public boolean isOccupied(Table table) {
		return seatings.stream().anyMatch(s -> s.getTable() == table);
	}

	public int getCapacity(boolean includeOccupied) {
		Stream<Table> stream = tables.stream();
		if (! includeOccupied) {
			stream = stream.filter(t -> ! isOccupied(t));
		}
		return stream.mapToInt(Table::getCapacity).sum();
	}

	public void addTable(Table table) {
		tables.add(table); 
	}

	public void removeTable(Table table) {
		if (isOccupied(table)) {
			throw new IllegalArgumentException("Cannot remove an occupied table");
		}
		tables.remove(table); 
	}
	
	public void mergeTables(Table table1, Table table2, int lostCapacity) {
		CompositeTable table = new CompositeTable(table1, table2, lostCapacity);
		removeTable(table1);
		removeTable(table2);
		addTable(table);
	}

	public void splitTable(CompositeTable table) {
		removeTable(table);
		addTable(table.getTable1());
		addTable(table.getTable2());
	}

	public boolean hasCapacity(Table table, int capacity) {
		return (! isOccupied(table)) && table.getCapacity() >= capacity;
	}

	public Collection<Table> findAvailableTables(int capacity) {
		return tables.stream().
				filter(t -> hasCapacity(t, capacity)).
				sorted((t1, t2) -> t1.getCapacity() - t2.getCapacity()).
				collect(Collectors.toList());
	}
	
	public Seating createSeating(Group group) {
		Collection<Table> availableTables = findAvailableTables(group.getGuestCount());
		if (availableTables.isEmpty()) {
			return null;
		}
		return new Seating(availableTables.iterator().next(), group);
	}
	
	public boolean addSeating(Group group) {
		Seating seating = createSeating(group);
		if (seating != null) {
			addSeating(seating);
			return true;
		}
		return false;
	}

	// to support testing
	boolean addSeating(Seating seating) {
		return seatings.add(seating);
	}

	private boolean isTable(Table table, int tableNum) {
		if (table instanceof SimpleTable) {
			return (((SimpleTable) table).getNum() == tableNum);
		} else if (table instanceof CompositeTable) {
			CompositeTable comp = (CompositeTable) table;
			if (isTable(comp.getTable2(), tableNum) || isTable(comp.getTable1(), tableNum)) {
				return true;
			}
		}
		return false;
	}
	
	public void removeSeating(int tableNum) {
		for (Seating seating : seatings) {
			if (isTable(seating.getTable(), tableNum)) {
				seatings.remove(seating);
				return;
			}
		}
	}
}
