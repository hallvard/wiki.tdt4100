package ord2017.stage1;

/**
 * A table with a certain maximum capacity.
 */
public class Table implements Comparable<Table> {

	private final int num;
	private final int capacity;
	
	private static int tableCounter = 1; 
	
	// håndtering av løpenummer, global vs. Diner-lokal (hvordan?)
	/**
	 * Initializes this Table with the provided capacity.
	 * The table is also assigned a unique number.
	 * @param capacity
	 */
	public Table(int capacity) {
		this.num = tableCounter++;
		this.capacity = capacity;
	}

	/**
	 * @return the table number
	 */
	public int getNum() {
		return num;
	}

	public int getCapacity() {
		return capacity;
	}

	@Override
	public int compareTo(Table other) {
		return getCapacity() - other.getCapacity();
	}
}
