package org.eclipse.dltk.ui.templates;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.ui.templates.messages"; //$NON-NLS-1$
	public static String ScriptTemplateAccess_unableToLoadTemplateStore;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
