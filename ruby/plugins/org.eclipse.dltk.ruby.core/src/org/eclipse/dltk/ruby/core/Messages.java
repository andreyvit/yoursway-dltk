package org.eclipse.dltk.ruby.core;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.ruby.core.messages"; //$NON-NLS-1$
	public static String PredefinedVariables_unableToLoadRubyPredefinedVariables;
	public static String RubyPlugin_initializingDltkRuby;
	public static String RubyPlugin_initializingSearchEngine;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
