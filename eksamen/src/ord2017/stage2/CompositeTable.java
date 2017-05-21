package ord2017.stage2;

/**
 * A table that consists of other tables.
 */
public class CompositeTable implements Table {
	
	private Table table1, table2;
	private int lostCapacity;
	
	public CompositeTable(Table table1, Table table2, int lostCapacity) {
		this.table1 = table1;
		this.table2 = table2;
		this.lostCapacity = lostCapacity;
	}

	public Table getTable1() {
		return table1;
	}

	public Table getTable2() {
		return table2;
	}
	
	@Override
	public int getCapacity() {
		return table1.getCapacity() + table2.getCapacity() - lostCapacity;
	}
}
