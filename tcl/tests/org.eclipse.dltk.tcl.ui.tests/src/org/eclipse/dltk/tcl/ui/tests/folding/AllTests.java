package org.eclipse.dltk.tcl.ui.tests.folding;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {
	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.eclipse.dltk.tcl.ui.tests.folding");
		//$JUnit-BEGIN$
		suite.addTestSuite(TclFoldingTest.class);
		//$JUnit-END$
		return suite;
	}
}
