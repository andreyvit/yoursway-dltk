package org.eclipse.dltk.dbgp.internal.commands;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.dbgp.internal.commands.messages"; //$NON-NLS-1$
	public static String DbgpDataTypeCommands_invalidTypeAttribute;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
