package org.eclipse.dltk.ruby.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.dltk.ruby.tests.assist.RubySelectionTests;
import org.eclipse.dltk.ruby.tests.parser.JRuby1RubyParserTests;
import org.eclipse.dltk.ruby.tests.parser.StdlibRubyParserTests;
import org.eclipse.dltk.ruby.tests.search.mixin.AutoMixinTests;
import org.eclipse.dltk.ruby.tests.text.completion.RubyCompletionTests;
import org.eclipse.dltk.ruby.tests.typeinference.MethodsTest;
import org.eclipse.dltk.ruby.tests.typeinference.VariablesTest;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.eclipse.dltk.ruby.tests");
		//$JUnit-BEGIN$
		suite.addTest(RubySelectionTests.suite());
		suite.addTest(StdlibRubyParserTests.suite());
		suite.addTest(JRuby1RubyParserTests.suite());
		suite.addTest(AutoMixinTests.suite());
		suite.addTest(RubyCompletionTests.suite());
		suite.addTest(VariablesTest.suite());
		suite.addTest(MethodsTest.suite());
		//$JUnit-END$
		return suite;
	}

}
