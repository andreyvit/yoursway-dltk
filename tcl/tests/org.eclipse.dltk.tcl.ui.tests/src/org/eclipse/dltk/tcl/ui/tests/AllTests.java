package org.eclipse.dltk.tcl.ui.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for org.eclipse.dltk.tcl.ui.tests");
		//$JUnit-BEGIN$
//		suite.addTest(org.eclipse.dltk.tcl.ui.tests.dialogs.AllTests.suite());
		suite.addTest(org.eclipse.dltk.tcl.ui.tests.folding.AllTests.suite());
		suite.addTest(org.eclipse.dltk.tcl.ui.tests.indenting.AllTests.suite());
		suite.addTest(org.eclipse.dltk.tcl.ui.tests.wizardapi.AllTests.suite());
		//$JUnit-END$
		return suite;
	}

}
