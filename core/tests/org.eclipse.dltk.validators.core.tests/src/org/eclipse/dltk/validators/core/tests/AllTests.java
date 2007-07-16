package org.eclipse.dltk.validators.core.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for org.eclipse.dltk.validators.core.tests");
		//$JUnit-BEGIN$
		suite.addTestSuite(ValidatorContainerTests.class);
		//$JUnit-END$
		return suite;
	}

}
