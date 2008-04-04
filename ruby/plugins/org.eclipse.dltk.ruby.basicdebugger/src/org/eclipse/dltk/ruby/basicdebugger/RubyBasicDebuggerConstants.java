package org.eclipse.dltk.ruby.basicdebugger;

import org.eclipse.jface.preference.IPreferenceStore;

public class RubyBasicDebuggerConstants {

	public static final String ENABLE_LOGGING = "enable_logging"; //$NON-NLS-1$
	public static final String LOG_FILE_PATH = "log_file_path"; //$NON-NLS-1$
	public static final String LOG_FILE_NAME = "log_file_name"; //$NON-NLS-1$
	
	public static void initializeDefaults(IPreferenceStore store) {
		store.setDefault(ENABLE_LOGGING, false);
		store.setDefault(LOG_FILE_NAME, "rubyBasicDebug_{0}.log"); //$NON-NLS-1$
		store.setDefault(LOG_FILE_PATH, ""); //$NON-NLS-1$
	}
	
	private RubyBasicDebuggerConstants() {
		// private constructor
	}
}
