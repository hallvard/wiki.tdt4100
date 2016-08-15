package kont2016;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(CourseTest.class);
		suite.addTestSuite(ExamTest.class);
		//$JUnit-END$
		return suite;
	}

}
