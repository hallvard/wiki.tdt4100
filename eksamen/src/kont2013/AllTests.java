package kont2013;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(ScoreCardTest.class);
		suite.addTestSuite(ValuesDiceTest.class);
		//$JUnit-END$
		return suite;
	}

}
