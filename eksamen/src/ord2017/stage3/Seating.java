package ord2017.stage3;

/**
 * Represents the fact that a Group is seated at and occupies a Table
 */
public class Seating {

	private final Table table;
	private final Group group;

	/**
	 * Initializes this Seating with the provided Table and Group.
	 * Note that the capacity of the Table needs to be sufficient for the Group.
	 * @param table
	 * @param group
	 */
	public Seating(Table table, Group group) {
		if (table.getCapacity() < group.getGuestCount()) {
			throw new IllegalArgumentException("The table is too small for the group");
		}
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
