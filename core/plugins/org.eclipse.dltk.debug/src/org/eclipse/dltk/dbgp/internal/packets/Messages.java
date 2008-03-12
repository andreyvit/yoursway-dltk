package org.eclipse.dltk.dbgp.internal.packets;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.dbgp.internal.packets.messages"; //$NON-NLS-1$
	public static String DbgpStreamPacket_contentCannotBeNull;
	public static String DbgpStreamPacket_invalidTypeValue;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
