package org.eclipse.dltk.tcl.parser.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for org.eclipse.dltk.tcl.parser.tests");
		//$JUnit-BEGIN$
		suite.addTestSuite(AllParseTests.class);
		suite.addTestSuite(SimpleParserTests.class);
		//$JUnit-END$
		return suite;
	}

}
