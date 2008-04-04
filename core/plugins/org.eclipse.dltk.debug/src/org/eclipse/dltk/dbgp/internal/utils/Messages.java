package org.eclipse.dltk.dbgp.internal.utils;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.dbgp.internal.utils.messages"; //$NON-NLS-1$
	public static String DbgpXmlEntityParser_invalidEncoding;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
