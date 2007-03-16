package org.eclipse.dltk.ruby.tests.parser;

import junit.framework.Test;

import org.eclipse.dltk.ruby.tests.parser.jruby.ZippedParserSuite;

public class StdlibRubyParserTests {

	public static Test suite() {
		return new ZippedParserSuite("/workspace/stdlib.zip");
	}

}
