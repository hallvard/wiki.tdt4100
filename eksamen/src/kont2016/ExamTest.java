package kont2016;

import junit.framework.TestCase;

public class ExamTest extends TestCase {

	private Course c1, c2;
	private Exam e1, e2;
	
	protected void setUp() {
		c1 = new Course("TDT4100");
		c1.setTime("S16");
		c2 = new Course("TDT4250");
		c2.setTime("F16");
		
	}
	
	public void testAFExams() {
		assertEquals('A', new Exam(c1, 'A').getGrade());
		assertEquals('F', new Exam(c1, 'F').getGrade());
		assertEquals('A', new Exam(c2, 'a').getGrade());
	}
	
	public void testGExam() {
		try {
			new Exam(c1, 'G');
			fail();
		} catch (IllegalArgumentException iae) {
		} catch (Exception e) {
			fail();
		}
	}
	
	public void testIsPass() {
		assertFalse(new Exam(c1, 'F').isPass());
		assertTrue(new Exam(c1, 'A').isPass());
	}
	
	public void testCompareTo() {
		e1 = new Exam(c1, 'C');
		e2 = new Exam(c2, 'C');
		assertTrue(e1.compareTo(e2) < 0);
		assertTrue(e2.compareTo(e1) > 0);
		assertTrue(e1.compareTo(e1) == 0);
		assertTrue(e2.compareTo(e2) == 0);
	}
}
