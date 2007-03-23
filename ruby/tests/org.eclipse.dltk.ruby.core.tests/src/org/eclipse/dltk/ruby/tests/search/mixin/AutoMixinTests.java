package org.eclipse.dltk.ruby.tests.search.mixin;

import junit.framework.Test;

public class AutoMixinTests {

	public static Test suite() {
		return new MixinTestsSuite("/workspace/mixins/src/auto");
	}

}
