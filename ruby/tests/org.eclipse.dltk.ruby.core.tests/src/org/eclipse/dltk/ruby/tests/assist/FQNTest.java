package org.eclipse.dltk.ruby.tests.assist;

import junit.framework.Test;


public class FQNTest {

	public static Test suite() {
		return new RubySelectionTestsSuite("/workspace/Selection/src/fqns.rb");
	}

}