package org.eclipse.dltk.core.tests.model;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.dltk.core.tests.buildpath.BuildpathTests;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for org.eclipse.dltk.core.tests.model");
		// $JUnit-BEGIN$
		suite.addTest(BufferTests.suite());
		suite.addTest(ModelMembersTests.suite());
		suite.addTest(BuildpathTests.suite());
		suite.addTest(WorkingCopyTests.suite());
		// $JUnit-END$
		return suite;
	}
}
