package ord2012.weeklyplan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DelegatingDayPlan extends DayPlan {

	private DayPlan delegate;
	
	public DelegatingDayPlan(DayPlan delegate) {
		super();
		this.delegate = delegate;
	}
	
	//

	@Override
	public TimeSlot getTimeSlotAt(int hours, int minutes) {
		TimeSlot timeSlot1 = super.getTimeSlotAt(hours, minutes);
		TimeSlot timeSlot2 = delegate.getTimeSlotAt(hours, minutes);
		if (timeSlot1 != null && timeSlot2 != null) {
			return timeSlot1.compareTo(timeSlot2) < 0 ? timeSlot1 : timeSlot2;
		} else if (timeSlot1 != null) {
			return timeSlot1;
		} else {
			return timeSlot2;
		}
	}

	//
	
	@Override
	protected List<TimeSlot> getAllTimeSlots() {
		List<TimeSlot> timeSlots = new ArrayList<TimeSlot>(super.getAllTimeSlots());
		timeSlots.addAll(delegate.getAllTimeSlots());
		Collections.sort(timeSlots);
		return timeSlots;
	}
}
