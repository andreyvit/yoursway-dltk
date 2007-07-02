package org.eclipse.dltk.tcl.ui.tests.wizardapi;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for org.eclipse.dltk.tcl.ui.tests.wizardapi");
		//$JUnit-BEGIN$
		suite.addTest(NewTCLProjectWizardTest.suite());
		//$JUnit-END$
		return suite;
	}

}
