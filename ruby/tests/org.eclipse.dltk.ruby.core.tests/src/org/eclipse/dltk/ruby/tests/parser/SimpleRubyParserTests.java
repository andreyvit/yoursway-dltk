package org.eclipse.dltk.ruby.tests.parser;

import org.eclipse.dltk.ruby.tests.parser.jruby.ParserSuite;

import junit.framework.Test;

public class SimpleRubyParserTests {

	public static Test suite() {
		return new ParserSuite("/workspace/parser");
	}

}
