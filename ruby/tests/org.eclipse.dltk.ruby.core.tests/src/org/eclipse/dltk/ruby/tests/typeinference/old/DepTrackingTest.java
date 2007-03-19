package org.eclipse.dltk.ruby.tests.typeinference.old;

import org.eclipse.dltk.ruby.tests.typeinference.TypeInferenceSuite;

import junit.framework.Test;

public class DepTrackingTest {

	public static Test suite() {
		return new TypeInferenceSuite("/workspace/typeinference/deptracking");
	}

}
