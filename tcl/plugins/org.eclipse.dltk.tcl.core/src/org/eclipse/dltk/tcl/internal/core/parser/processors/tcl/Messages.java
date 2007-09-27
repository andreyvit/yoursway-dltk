package org.eclipse.dltk.tcl.internal.core.parser.processors.tcl;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.xotcl.internal.core.parser.processors.tcl.messages"; //$NON-NLS-1$
	public static String TclProcProcessor_Empty_Proc_Name;
	public static String TclProcProcessor_Wrong_Number_of_Arguments;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
