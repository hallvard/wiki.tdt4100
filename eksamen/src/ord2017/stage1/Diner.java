package ord2017.stage1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Diner {
	
	private Collection<Table> tables = new ArrayList<>();	
	private Collection<Seating> seatings = new ArrayList<>();

	// to support testing
	int getTableCount() {
		return tables.size();
	}
	
	public boolean isOccupied(Table table) {
		for (Seating seating : seatings) {
			if (seating.getTable() == table) {
				return true;
			}
		}
		return false;
	}

	public int getCapacity(boolean includeOccupied) {
		int capacity = 0;
		for (Table table : tables) {
			if (includeOccupied || (! isOccupied(table))) {
				capacity += table.getCapacity();
			}
		}
		return capacity;
	}
	
	public void addTable(Table table) {
		tables.add(table); 
	}

	public void removeTable(Table table) {
		checkNotOccupied(table);
		tables.remove(table); 
	}

	void checkNotOccupied(Table table) {
		if (isOccupied(table)) {
			throw new IllegalArgumentException("The table cannot be occupied table");
		}
	}
	
	public void mergeTables(Table table1, Table table2, int lostCapacity) {
		checkNotOccupied(table1);
		checkNotOccupied(table2);
		Table table = new Table(table1.getCapacity() + table2.getCapacity() - lostCapacity);
		removeTable(table1);
		removeTable(table2);
		addTable(table);
	}
	
	public void splitTable(Table table, int capacity1, int capacity2) {
		checkNotOccupied(table);
		Table table1 = new Table(capacity1);
		Table table2 = new Table(capacity2);
		removeTable(table);
		addTable(table1);
		addTable(table2);
	}
	
	public boolean hasCapacity(Table table, int capacity) {
		return (! isOccupied(table)) && table.getCapacity() >= capacity;
	}

	public Collection<Table> findAvailableTables(int capacity) {
		List<Table> result = new ArrayList<>();
		for (Table table : tables) {
			if (hasCapacity(table, capacity)) {
				result.add(table);
			}
		}
		Collections.sort(result);
		return result;
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
	void addSeating(Seating seating) {
		seatings.add(seating);
	}

	public void removeSeating(int tableNum) {
		for (Seating seating : seatings) {
			if (seating.getTable().getNum() == tableNum) {
				seatings.remove(seating);
				return;
			}
		}
	}
}
