package org.eclipse.dltk.internal.debug.ui.interpreters;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.internal.debug.ui.interpreters.messages"; //$NON-NLS-1$
	public static String MultipleInputDialog_ignore;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
