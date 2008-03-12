package org.eclipse.dltk.ui.viewsupport;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.ui.viewsupport.messages"; //$NON-NLS-1$
	public static String AppearanceAwareLabelProvider_storeMustNotBeNull;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
