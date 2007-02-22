package org.eclipse.dltk.ruby.tests.typeinference;

import junit.framework.Test;

public class SimpleTest {

	public static Test suite() {
		return new TypeInferenceSuite("/workspace/typeinference/simple");
	}

}
