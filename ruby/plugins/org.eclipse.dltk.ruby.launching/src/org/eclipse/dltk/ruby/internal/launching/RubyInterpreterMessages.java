package org.eclipse.dltk.ruby.internal.launching;

import org.eclipse.osgi.util.NLS;

public class RubyInterpreterMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.dltk.ruby.internal.launching.RubyInterpreterMessages";//$NON-NLS-1$

	private RubyInterpreterMessages() {
		// dont instatiate
	}

	static {
		// load message values from bundle file
		NLS.initializeMessages(BUNDLE_NAME, RubyInterpreterMessages.class);
	}

}
