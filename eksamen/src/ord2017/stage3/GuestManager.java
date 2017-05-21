package ord2017.stage3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Handles guests arriving at and departing from a Diner.
 */
public class GuestManager implements CapacityListener {

	private final Diner diner;
	
	/**
	 * Initialized this GuestManager for the provided Diner.
	 * @param diner
	 */
	public GuestManager(Diner diner) {
		this.diner = diner;
		diner.addCapacityListener(this);
	}
	
	private Collection<Group> waitingGroups = new ArrayList<>();

	/**
	 * Handles arriving groups, by either seating them immediately
	 * (if possible) or putting them in queue. Those enqueued will
	 * be seated when the Diner's (change in) capacity allows.
	 * @param group
	 */
	public void groupArrived(Group group) {
		if (! diner.addSeating(group)) {
			waitingGroups.add(group);
		}
	}

	/**
	 * Handles departing groups, by removing their seating.
	 * @param tableNum the table where the group was seated
	 */
	public void groupDeparted(int tableNum) {
		diner.removeSeating(tableNum);
	}

	@Override
	public void capacityChanged(Diner diner) {
		Iterator<Group> it = waitingGroups.iterator();
		while (it.hasNext()) {
			Group waiting = it.next();
			if (diner.addSeating(waiting)) {
				it.remove();
			}
		}
	}
}
