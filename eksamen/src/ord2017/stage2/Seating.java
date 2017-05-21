package ord2017.stage2;

public class Seating {

	private final Table table;
	private final Group group;

	public Seating(Table table, Group group) {
		this.table = table;
		this.group = group;
	}
	
	public Table getTable() {
		return table;
	}
	
	public Group getGroup() {
		return group;
	}
}
