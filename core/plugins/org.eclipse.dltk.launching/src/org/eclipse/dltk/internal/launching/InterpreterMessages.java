package org.eclipse.dltk.internal.launching;

import org.eclipse.osgi.util.NLS;

public class InterpreterMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.dltk.internal.launching.InterpreterMessages";//$NON-NLS-1$

	public static String errNonExistentOrInvalidInstallLocation;
	public static String errBinDirectoryNotFound;
	public static String errNoInterpreterExecutablesFound;

	public static String statSearchingForDefaultLibraryLocations;

	public static String statusFetchingLibs;

	private InterpreterMessages() {
	}

	static {
		NLS.initializeMessages(BUNDLE_NAME, InterpreterMessages.class);
	}

}
