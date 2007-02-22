package org.eclipse.dltk.tcl.tests.model;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for org.eclipse.dltk.tcl.tests.model");
		//$JUnit-BEGIN$
		suite.addTest(ModelTclTests.suite());
		//$JUnit-END$
		return suite;
	}

}
