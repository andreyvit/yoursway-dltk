package org.eclipse.dltk.debug.ui.launchConfigurations;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.debug.ui.launchConfigurations.messages"; //$NON-NLS-1$
	public static String ScriptCommonTab_useSpecificDltkScriptConsole;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
