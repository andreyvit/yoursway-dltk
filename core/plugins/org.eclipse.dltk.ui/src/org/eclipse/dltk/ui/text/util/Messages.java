package org.eclipse.dltk.ui.text.util;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.ui.text.util.messages"; //$NON-NLS-1$
	public static String AutoEditUtils_cannotCalculateVisualLengthForSeveralLines;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
