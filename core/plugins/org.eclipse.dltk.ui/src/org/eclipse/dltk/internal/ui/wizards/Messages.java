package org.eclipse.dltk.internal.ui.wizards;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.internal.ui.wizards.messages"; //$NON-NLS-1$
	public static String BuildpathDetector_detectingBuildpath;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
