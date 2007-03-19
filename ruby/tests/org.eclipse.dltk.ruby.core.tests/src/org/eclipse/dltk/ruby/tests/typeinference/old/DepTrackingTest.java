package org.eclipse.dltk.ruby.tests.typeinference.old;

import junit.framework.Test;

import org.eclipse.dltk.ruby.tests.typeinference.TypeInferenceSuite;

public class DepTrackingTest {

	public static Test suite() {
		return new TypeInferenceSuite("/workspace/typeinference/deptracking");
	}

}
