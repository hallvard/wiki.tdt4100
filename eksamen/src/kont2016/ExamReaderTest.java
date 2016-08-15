package kont2016;

import java.io.StringReader;
import java.util.Collection;
import java.util.Iterator;

import junit.framework.TestCase;

public class ExamReaderTest extends TestCase {

	private String contents = "F2015\nTDT4105 7.5 C\nS2016\nTDT4100 7.5 F A";
	private ExamReader reader = new ExamReader(); 

	public void testReadExams() {
		Collection<Exam> exams = reader.readExams(new StringReader(contents));
		assertEquals(3, exams.size());
		Iterator<Exam> it = exams.iterator();
		Exam e1 = it.next(), e2 = it.next(), e3 = it.next();
		assertEquals("TDT4105", e1.getCourse().getCode());
		assertEquals(7.5, e1.getCourse().getCredits());
		assertEquals("F2015", e1.getCourse().getTime());
		assertEquals('C', e1.getGrade());
		assertEquals("TDT4100", e2.getCourse().getCode());
		assertSame(e2.getCourse(), e3.getCourse());
		assertEquals("S2016", e2.getCourse().getTime());
		assertEquals(7.5, e2.getCourse().getCredits());
		assertEquals('F', e2.getGrade());
		assertEquals('A', e3.getGrade());
	}
}
