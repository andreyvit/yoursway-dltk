package org.eclipse.dltk.ruby.fastdebugger;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.ruby.fastdebugger.messages"; //$NON-NLS-1$
	public static String FastDebuggerRunner_fastDebuggerCanOnlyBeRunWithGenericRubyInterpreter;
	public static String FastDebuggerRunner_unableToDeployDebuggerSource;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
