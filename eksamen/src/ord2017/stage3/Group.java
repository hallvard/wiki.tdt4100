package ord2017.stage3;

/**
 * A group (of people) dining together, and should be seated at the same table.
 * We currently only need to handle the size. 
 */
public class Group {

	private int guestCount;

	/**
	 * Initializes this Group with the provided guest count
	 * @param guestCount
	 */
	public Group(int guestCount) {
		this.guestCount = guestCount;
	}

	public int getGuestCount() {
		return guestCount;
	}
}
