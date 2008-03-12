package org.eclipse.dltk.debug.ui.breakpoints;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.debug.ui.breakpoints.messages"; //$NON-NLS-1$
	public static String ScriptToggleBreakpointAdapter_breakpointAlreadySetAtLine;
	public static String ScriptToggleBreakpointAdapter_invalidBreakpointPosition;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
