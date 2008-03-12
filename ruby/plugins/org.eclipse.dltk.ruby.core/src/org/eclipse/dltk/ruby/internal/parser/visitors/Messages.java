package org.eclipse.dltk.ruby.internal.parser.visitors;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.ruby.internal.parser.visitors.messages"; //$NON-NLS-1$
	public static String RubySourceElementRequestor_addVariableExpressionCantBeNull;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
