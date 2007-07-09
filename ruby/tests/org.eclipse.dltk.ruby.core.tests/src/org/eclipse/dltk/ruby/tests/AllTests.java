package org.eclipse.dltk.ruby.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.dltk.ruby.tests.assist.RubySelectionTests;
import org.eclipse.dltk.ruby.tests.parser.RubyParserTests;
import org.eclipse.dltk.ruby.tests.search.mixin.AutoMixinTests;
import org.eclipse.dltk.ruby.tests.search.mixin.MixinModelManipulationTests;
import org.eclipse.dltk.ruby.tests.text.completion.RubyCompletionTests;
import org.eclipse.dltk.ruby.tests.typeinference.MethodsTest;
import org.eclipse.dltk.ruby.tests.typeinference.SimpleTest;
import org.eclipse.dltk.ruby.tests.typeinference.StatementsTest;
import org.eclipse.dltk.ruby.tests.typeinference.VariablesTest;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.eclipse.dltk.ruby.core");
		//$JUnit-BEGIN$
		suite.addTest(AutoMixinTests.suite());
		suite.addTest(MixinModelManipulationTests.suite());
		
		
		suite.addTest(RubySelectionTests.suite());
		suite.addTest(RubyCompletionTests.suite());
		
		suite.addTestSuite(RubyParserTests.class);
		
		// FIXME: fix running of this tests under mac os x
//		suite.addTest(StdlibRubyParserTests.suite());
//		suite.addTest(JRuby1RubyParserTests.suite());
	
		
		// XXX: uncomment this tests, when type hierarchies
		// support will be implemented
		//suite.addTest(TypeHierarchyTests.suite());
		
		// Type inference
		suite.addTest(VariablesTest.suite());
		suite.addTest(MethodsTest.suite());
		suite.addTest(StatementsTest.suite());
		suite.addTest(SimpleTest.suite());
		//$JUnit-END$
		return suite;
	}

}
