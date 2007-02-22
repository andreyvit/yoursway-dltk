package org.eclipse.dltk.tcl.core.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for org.eclipse.dltk.tcl.core.tests");
		//$JUnit-BEGIN$
		suite.addTest(org.eclipse.dltk.tcl.core.tests.model.AllTests.suite());
		suite.addTest(org.eclipse.dltk.tcl.parser.tests.AllTests.suite());
		//$JUnit-END$
		return suite;
	}

}
