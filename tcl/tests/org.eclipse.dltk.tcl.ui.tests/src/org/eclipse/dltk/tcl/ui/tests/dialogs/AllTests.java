package org.eclipse.dltk.tcl.ui.tests.dialogs;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for org.eclipse.dltk.tcl.ui.tests.dialogs");
		//$JUnit-BEGIN$
		suite.addTestSuite(NewEmptyProjectWizardTest.class);
		suite.addTest(NewProjectWizardTest.suite());
		suite.addTestSuite(NewProjectWizardOperationTest.class);
		suite.addTestSuite(NewProjectTestSetup.class);
		//$JUnit-END$
		return suite;
	}

}
