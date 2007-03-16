package org.eclipse.dltk.ruby.tests.parser;

import junit.framework.Test;

import org.eclipse.dltk.ruby.tests.parser.jruby.ParserSuite;

public class SimpleRubyParserTests {

	public static Test suite() {
		return new ParserSuite("/workspace/parser");
	}

}
