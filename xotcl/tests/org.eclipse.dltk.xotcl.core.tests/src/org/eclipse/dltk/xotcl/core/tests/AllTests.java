package org.eclipse.dltk.xotcl.core.tests;

import org.eclipse.dltk.xotcl.core.tests.model.XOTclSelectionTests;
import org.eclipse.dltk.xotcl.core.tests.parser.TclCommandProcessorTests;
import org.eclipse.dltk.xotcl.core.tests.parser.XOTclComandProcessorTests;
import org.eclipse.dltk.xotcl.core.tests.parser.XOTclParserUtilTests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for org.ecipse.dltk.xotcl.core.tests");
		//$JUnit-BEGIN$
		suite.addTestSuite(XOTclComandProcessorTests.class);
		suite.addTestSuite(XOTclParserUtilTests.class);
		suite.addTestSuite(TclCommandProcessorTests.class);
		suite.addTest(XOTclSelectionTests.suite());
		//$JUnit-END$
		return suite;
	}

}
