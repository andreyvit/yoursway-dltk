package org.eclipse.dltk.internal.debug.ui.log;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.internal.debug.ui.log.messages"; //$NON-NLS-1$
	public static String ScriptDebugLogView_clear;
	public static String ScriptDebugLogView_copy;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
