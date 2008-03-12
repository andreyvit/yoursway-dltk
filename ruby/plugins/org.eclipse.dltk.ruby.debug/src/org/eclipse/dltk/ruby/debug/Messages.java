package org.eclipse.dltk.ruby.debug;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.ruby.debug.messages"; //$NON-NLS-1$
	public static String RubyHotCodeReplaceProvider_unableToComputeSourceFilePath;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
