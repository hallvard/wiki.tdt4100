package ord2012.weeklyplan;

public class TDT4100DayPlan extends DayPlan {

	private TDT4100Lecture tdt4100Lecture;

	public TDT4100DayPlan() {
		super();
		tdt4100Lecture = new TDT4100Lecture();
		super.addTimeSlot(tdt4100Lecture);
	}

	@Override
	public void addTimeSlot(TimeSlot timeSlot) {
		if (timeSlot.overlaps(tdt4100Lecture)) {
			throw new IllegalArgumentException("Cannot overlap TDT4100 lecture!");
		}
		super.addTimeSlot(timeSlot);
	}

	@Override
	public void removeTimeSlot(TimeSlot timeSlot) {
		if (timeSlot == tdt4100Lecture) {
			throw new IllegalArgumentException("Cannot remove TDT4100 lecture!");
		}
		super.removeTimeSlot(timeSlot);
	}
	

	public static void main(String[] args) {
		DayPlan tuesday = new TDT4100DayPlan();
		System.out.println(tuesday.getTimeSlotAt(10, 30)); 
		// prints "TDT4100 lecture@10:15-12:0"
		tuesday.addTimeSlot(new TimeSlot("Coffee break", 11, 30, 60));
		// throws appropriate exception, since it overlaps with the TDT4100 lecture
	}
}
