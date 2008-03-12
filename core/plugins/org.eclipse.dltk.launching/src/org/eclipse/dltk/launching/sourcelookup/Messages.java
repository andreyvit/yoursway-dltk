package org.eclipse.dltk.launching.sourcelookup;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.launching.sourcelookup.messages"; //$NON-NLS-1$
	public static String DBGPSourceLookupParticipant_debugResource;
	public static String DBGPSourceModule_dbgpSourceLookup;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
