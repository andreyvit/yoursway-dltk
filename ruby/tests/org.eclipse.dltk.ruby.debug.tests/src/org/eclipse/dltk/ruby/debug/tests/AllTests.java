package org.eclipse.dltk.ruby.debug.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.dltk.ruby.debug.tests.launching.RubyLaunchingTests;

public class AllTests {
	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.eclipse.dltk.ruby.debug");
		// $JUnit-BEGIN$

		// Launching
		suite.addTest(RubyLaunchingTests.suite());

		// $JUnit-END$
		return suite;
	}
}
