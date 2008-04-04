package org.eclipse.dltk.ruby.internal.ui;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.ruby.internal.ui.messages"; //$NON-NLS-1$
	public static String InitializeAfterLoadJob_initializingDltkRuby;
	public static String InitializeAfterLoadJob_startingDltkRubyInitialization;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
