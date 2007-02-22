package org.eclipse.dltk.tcl.internal.tclchecker.ui.actions;

import org.eclipse.osgi.util.NLS;

public class ActionMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.tcl.tclchecker.ui.actions.messages"; //$NON-NLS-1$

	public static String TclChecker_Error;

	public static String TclChecker_CannotAddNature;

	public static String TclChecker_CannotRemoveNature;
	
	public static String TclChecker_Work;

	static {
		NLS.initializeMessages(BUNDLE_NAME, ActionMessages.class);
	}
}
