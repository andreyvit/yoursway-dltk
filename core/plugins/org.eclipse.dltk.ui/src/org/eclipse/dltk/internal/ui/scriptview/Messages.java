package org.eclipse.dltk.internal.ui.scriptview;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.internal.ui.scriptview.messages"; //$NON-NLS-1$
	public static String LibraryContainer_libraries;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
