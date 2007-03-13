package org.eclipse.dltk.ruby.tests.parser;

import org.eclipse.dltk.ruby.tests.parser.jruby.ZippedParserSuite;

import junit.framework.Test;

public class StdlibRubyParserTests {

	public static Test suite() {
		return new ZippedParserSuite("/workspace/stdlib.zip");
	}

}
