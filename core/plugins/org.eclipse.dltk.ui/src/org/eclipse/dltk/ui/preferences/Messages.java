package org.eclipse.dltk.ui.preferences;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.ui.preferences.messages"; //$NON-NLS-1$
	public static String ScriptCorePreferencePage_manualReindex;
	public static String ScriptCorePreferencePage_reindex;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
