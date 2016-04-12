package ord2012.weeklyplan;

import java.util.Iterator;

import junit.framework.TestCase;

public class DayPlanTest extends TestCase {

	protected DayPlan day;
	
	protected void setUp() {
		day = new DayPlan();
	}
	
	private void testTimeSlots(Iterator<TimeSlot> actualTimeSlots, TimeSlot... expectedTimeSlots) {
		int i = 0;
		while (actualTimeSlots.hasNext()) {
			assertSame(expectedTimeSlots[i], actualTimeSlots.next());
			i++;
		}
	}

	public void testAddTimeSlot1() {
		TimeSlot ts08 = new TimeSlot("ts08", 8, 00, 61);
		TimeSlot ts09 = new TimeSlot("ts09", 9, 00, 61);
		TimeSlot ts10 = new TimeSlot("ts10", 10, 00, 60);

		day.addTimeSlot(ts08);		testTimeSlots(day.iterator(), ts08);
		day.addTimeSlot(ts09);		testTimeSlots(day.iterator(), ts08, ts09);
		day.addTimeSlot(ts10);		testTimeSlots(day.iterator(), ts08, ts09, ts10);
	}

	public void testAddTimeSlot2() {
		TimeSlot ts08 = new TimeSlot("ts08", 8, 00, 61);
		TimeSlot ts09 = new TimeSlot("ts09", 9, 00, 61);
		TimeSlot ts10 = new TimeSlot("ts10", 10, 00, 60);

		day.addTimeSlot(ts09);		testTimeSlots(day.iterator(), ts09);
		day.addTimeSlot(ts10);		testTimeSlots(day.iterator(), ts09, ts10);
		day.addTimeSlot(ts08);		testTimeSlots(day.iterator(), ts08, ts09, ts10);
	}

	public void testAddTimeSlot3() {
		TimeSlot ts08 = new TimeSlot("ts08", 8, 00, 61);
		TimeSlot ts09 = new TimeSlot("ts09", 9, 00, 61);
		TimeSlot ts10 = new TimeSlot("ts10", 10, 00, 60);

		day.addTimeSlot(ts08);		testTimeSlots(day.iterator(), ts08);
		day.addTimeSlot(ts10);		testTimeSlots(day.iterator(), ts08, ts10);
		day.addTimeSlot(ts09);		testTimeSlots(day.iterator(), ts08, ts09, ts10);
	}

	public void testIsDoubleBooked() {
		day.addTimeSlot(new TimeSlot("ts1", 8, 00, 60));
		day.addTimeSlot(new TimeSlot("ts2", 9, 00, 60));
		assertFalse(day.containsOverlapping());

		day.addTimeSlot(new TimeSlot("ts1", 8, 00, 61));
		day.addTimeSlot(new TimeSlot("ts2", 9, 00, 60));
		assertTrue(day.containsOverlapping());

		day.addTimeSlot(new TimeSlot("ts1", 8, 00, 61));
		day.addTimeSlot(new TimeSlot("ts2", 8, 00, 60));
		assertTrue(day.containsOverlapping());
		
		day.addTimeSlot(new TimeSlot("ts1", 8, 00, 60));
		day.addTimeSlot(new TimeSlot("ts2", 8, 00, 61));
		assertTrue(day.containsOverlapping());
	}
}
