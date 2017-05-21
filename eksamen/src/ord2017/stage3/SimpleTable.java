package ord2017.stage3;

public class SimpleTable implements Table, Comparable<SimpleTable> {

	private final int num;
	private final int capacity;
	
	private static int tableCounter = 1; 
	
	public SimpleTable(int capacity) {
		this.num = tableCounter++;
		this.capacity = capacity;
	}

	public int getNum() {
		return num;
	}
	
	public int getCapacity() {
		return capacity;
	}

	@Override
	public int compareTo(SimpleTable other) {
		return getCapacity() - other.getCapacity();
	}
}
