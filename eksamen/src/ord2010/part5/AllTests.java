package ord2010.part5;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for part4");
		//$JUnit-BEGIN$
		suite.addTestSuite(RadioAlphabetTest.class);
		suite.addTestSuite(MorseRadioAlphabetTest.class);
		//$JUnit-END$
		return suite;
	}

}
