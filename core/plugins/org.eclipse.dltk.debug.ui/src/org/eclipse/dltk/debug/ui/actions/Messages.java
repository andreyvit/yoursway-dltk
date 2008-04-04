package org.eclipse.dltk.debug.ui.actions;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.debug.ui.actions.messages"; //$NON-NLS-1$
	public static String AddExceptionAction_addExceptionBreakpoint;
	public static String AddExceptionAction_scriptToggleExceptionBreakpoint;
	public static String AddExceptionAction_search;
	public static String AddExceptionAction_unableToCreateBreakpoint;
	public static String AddExceptionTypeDialogExtension_selectedItemIsNotAnException;
	public static String AddExceptionTypeDialogExtension_suspendOnCaught;
	public static String AddExceptionTypeDialogExtension_SuspendOnUncaught;
	public static String ToggleWatchPointAction_error;
	public static String ToggleWatchPointAction_toggleWatchpoint;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
