package ord2012.weeklyplan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class DayPlan implements Iterable<TimeSlot> {

	private List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
	
	public void addTimeSlot(TimeSlot timeSlot) {
		timeSlots.add(timeSlot);
		Collections.sort(timeSlots);
	}

	public void removeTimeSlot(TimeSlot timeSlot) {
		timeSlots.remove(timeSlot);
	}

	public TimeSlot getTimeSlotAt(int hours, int minutes) {
		for (TimeSlot timeSlot : timeSlots) {
			if (timeSlot.contains(hours, minutes)) {
				return timeSlot;
			}
		}
		return null;
	}

	public boolean isOccupied(int hours, int minutes) {
		return getTimeSlotAt(hours, minutes) != null;
	}
	//

	protected List<TimeSlot> getAllTimeSlots() {
		return this.timeSlots;
	}

	public static boolean containsOverlapping(List<TimeSlot> timeSlots) {
		for (int i = 0; i < timeSlots.size(); i++) {
			TimeSlot timeSlot = timeSlots.get(i);
			for (int j = i + 1; j < timeSlots.size(); j++) {
				TimeSlot otherTimeSlot = timeSlots.get(j);
				if (timeSlot != otherTimeSlot && timeSlot.overlaps(otherTimeSlot)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean containsOverlapping() {
		return containsOverlapping(getAllTimeSlots());
	}
	
	private static void addTimeSlotIfNonEmpty(Collection<TimeSlot> timeSlots, DayTime startTime, DayTime endTime) {
		int duration = (endTime.hours * 60 + endTime.minutes) - (startTime.hours * 60 + startTime.minutes);
		if (duration > 0) {
			timeSlots.add(new TimeSlot(null, startTime.hours, startTime.minutes, duration));
		}
	}
	
	public Collection<TimeSlot> getFreeTime() {
		Collection<TimeSlot> freeTime = new ArrayList<TimeSlot>();
		TimeSlot previous = new TimeSlot(null, 0, 0, 0);
		for (TimeSlot timeSlot : getAllTimeSlots()) {
			DayTime startTime = timeSlot.getStartTime();
			addTimeSlotIfNonEmpty(freeTime, previous.getEndTime(), startTime);
			DayTime endTime = timeSlot.getEndTime();
			if (! previous.contains(endTime.hours, endTime.minutes)) {
				previous = timeSlot;
			}
		}
		addTimeSlotIfNonEmpty(freeTime, previous.getEndTime(), new DayTime(24, 0));
		return freeTime;
	}

	//

	public Iterator<TimeSlot> iterator() {
		return getAllTimeSlots().iterator();
	}
}
