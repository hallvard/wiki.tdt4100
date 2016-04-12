package ord2012.weeklyplan;

import junit.framework.TestCase;

public class TimeSlotTest extends TestCase {

	public void testToString() {
		assertEquals("ts1@08:00-08:59", new TimeSlot("ts1", 8, 0, 59).toString());
	}
	
	private void testEndTime(TimeSlot timeSlot, int hours, int minutes) {
		assertEquals(hours, timeSlot.getEndTime().hours);
		assertEquals(minutes, timeSlot.getEndTime().minutes);
	}

	public void testEndTime() {
		testEndTime(new TimeSlot("ts1", 8, 0, 59), 8, 59);
		testEndTime(new TimeSlot("ts2", 8, 0, 60), 9, 0);
		testEndTime(new TimeSlot("ts3", 8, 1, 59), 9, 0);
		testEndTime(new TimeSlot("ts4", 8, 0, 61), 9, 1);
		testEndTime(new TimeSlot("ts5", 8, 1, 60), 9, 1);
	}

	public void testCompareTo(float expected, int actual) {
		assertEquals(expected, Math.signum(actual));
	}

	public void testContains() {
		assertFalse(new TimeSlot("ts1", 8, 0, 59).contains(7, 59));
		assertFalse(new TimeSlot("ts1", 8, 1, 59).contains(8, 0));
		assertTrue(new TimeSlot("ts1", 8, 0, 59).contains(8, 0));
		assertTrue(new TimeSlot("ts1", 8, 0, 59).contains(8, 1));
		assertTrue(new TimeSlot("ts1", 8, 0, 59).contains(8, 58));
		assertFalse(new TimeSlot("ts1", 8, 0, 59).contains(8, 59));
		assertFalse(new TimeSlot("ts1", 8, 0, 58).contains(8, 59));
		assertFalse(new TimeSlot("ts1", 8, 0, 59).contains(9, 0));
	}

	private void testOverlaps(boolean expected, TimeSlot ts1, TimeSlot ts2) {
		assertEquals(expected, ts1.overlaps(ts2));
		assertEquals(expected, ts2.overlaps(ts1));
	}
	public void testOverlaps() {
		testOverlaps(true, new TimeSlot("ts1", 8, 0, 59), new TimeSlot("ts2", 8, 0, 59));
		testOverlaps(true, new TimeSlot("ts1", 7, 59, 2), new TimeSlot("ts2", 8, 0, 59));
		testOverlaps(false, new TimeSlot("ts1", 8, 0, 59), new TimeSlot("ts2", 8, 59, 2));
	}
	
	public void testCompareTo() {
		testCompareTo(-1, new TimeSlot("ts1", 8, 0, 59).compareTo(new TimeSlot("ts2", 8, 1, 59)));
		testCompareTo( 0, new TimeSlot("ts1", 8, 0, 59).compareTo(new TimeSlot("ts2", 8, 0, 59)));
		testCompareTo( 0, new TimeSlot("ts1", 8, 1, 59).compareTo(new TimeSlot("ts2", 8, 1, 59)));
		testCompareTo( 1, new TimeSlot("ts1", 8, 1, 59).compareTo(new TimeSlot("ts2", 8, 0, 59)));

		testCompareTo(-1, new TimeSlot("ts1", 8, 0, 58).compareTo(new TimeSlot("ts2", 8, 1, 59)));
		testCompareTo( 1, new TimeSlot("ts1", 8, 0, 59).compareTo(new TimeSlot("ts2", 8, 0, 58)));
		testCompareTo( 1, new TimeSlot("ts1", 8, 1, 59).compareTo(new TimeSlot("ts2", 8, 1, 58)));
		testCompareTo( 1, new TimeSlot("ts1", 8, 1, 59).compareTo(new TimeSlot("ts2", 8, 0, 59)));
		testCompareTo( 0, new TimeSlot("ts1", 8, 1, 59).compareTo(new TimeSlot("ts2", 8, 1, 59)));
	}
}
