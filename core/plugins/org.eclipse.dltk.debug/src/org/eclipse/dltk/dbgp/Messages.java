package org.eclipse.dltk.dbgp;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.dbgp.messages"; //$NON-NLS-1$
	public static String DbgpServer_acceptingDebuggingEngineConnection;
	public static String DbgpServer_startPortShouldBeLessThanOrEqualToEndPort;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
