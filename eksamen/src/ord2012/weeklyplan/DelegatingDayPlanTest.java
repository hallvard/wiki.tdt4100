package ord2012.weeklyplan;


public class DelegatingDayPlanTest extends DayPlanTest {

	private DayPlan delegate;
	
	@Override
	protected void setUp() {
		super.setUp();
		day = new DelegatingDayPlan(delegate = day);
	}
	
	public void testDelegate() {
		day.addTimeSlot(new TimeSlot(null, 8, 0, 60));
		delegate.addTimeSlot(new TimeSlot(null, 8, 30, 60));
		assertNotNull(day.getTimeSlotAt(9, 15));
		assertTrue(day.containsOverlapping());
	}

	public static void main(String[] args) {
		DayPlan repeatingTuesday = new TDT4100DayPlan();
		DelegatingDayPlan tuesday = new DelegatingDayPlan(repeatingTuesday);
		System.out.println(tuesday.getTimeSlotAt(10, 30)); 
		// prints "TDT4100 lecture@10:15-12:0" tuesday logically includes the TDT4100Lecture in repeatingTuesday

		tuesday.addTimeSlot(new TimeSlot("Coffee break", 11, 30, 60));
		System.out.println(tuesday.containsOverlapping());
		// prints "true" since timeSlot in tuesday overlaps with TDT4100Lecture in repeatingTuesday
	}
}
