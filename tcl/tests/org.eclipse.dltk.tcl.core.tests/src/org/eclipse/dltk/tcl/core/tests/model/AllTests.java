package org.eclipse.dltk.tcl.core.tests.model;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for org.eclipse.dltk.tcl.core.tests.model");
		//$JUnit-BEGIN$
		suite.addTest(SearchTests.suite());
		suite.addTest(TclSelectionTests.suite());
		suite.addTest(CompletionTests.suite());
		//$JUnit-END$
		return suite;
	}

}
