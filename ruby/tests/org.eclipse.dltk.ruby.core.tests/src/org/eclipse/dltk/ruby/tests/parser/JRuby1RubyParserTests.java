package org.eclipse.dltk.ruby.tests.parser;

import junit.framework.Test;

import org.eclipse.dltk.ruby.tests.parser.jruby.ZippedParserSuite;

public class JRuby1RubyParserTests {

	public static Test suite() {
		return new ZippedParserSuite("/workspace/jruby-tests1.zip");
	}

}
