package org.eclipse.dltk.ruby.tests.assist;

import junit.framework.Test;

import org.eclipse.dltk.ruby.tests.typeinference.TypeInferenceSuite;


public class FQNTest {

	public static Test suite() {
		return new RubySelectionTestsSuite("/workspace/Selection/src/fqns.rb");
	}

}