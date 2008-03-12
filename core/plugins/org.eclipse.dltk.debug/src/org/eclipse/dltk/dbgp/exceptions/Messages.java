package org.eclipse.dltk.dbgp.exceptions;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.dbgp.exceptions.messages"; //$NON-NLS-1$
	public static String DbgpDebuggingEngineException_dbgpDebuggingEngineException;
	public static String DbgpDebuggingEngineException_dbgpDebuggingEngineException2;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
