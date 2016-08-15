package kont2016;

import junit.framework.TestCase;

public class CourseTest extends TestCase {

	private Course c1, c2;
	
	protected void setUp() {
		c1 = new Course("TDT4100");
		c1.setTime("S16");
		c2 = new Course("TDT4250");
		c2.setTime("F16");
	}
	
	public void testGetYear() {
		assertEquals(2016, c1.getYear());
		assertEquals(2016, c2.getYear());
	}

	public void testGetSemester() {
		assertEquals('S', c1.getSemester());
		assertEquals('F', c2.getSemester());
	}
	
	public void testSetTimeException() {
		try {
			c1.setTime("X00");
			fail();
		} catch (IllegalArgumentException iae) {
		} catch (Exception e) {
			fail();
		}
		assertEquals('S', c1.getSemester());
		assertEquals('F', c2.getSemester());
	}
	
	public void testCompareTo() {
		assertTrue(c1.compareTo(c2) < 0);
		assertTrue(c2.compareTo(c1) > 0);
		assertTrue(c1.compareTo(c1) == 0);
		assertTrue(c2.compareTo(c2) == 0);
	}
}
