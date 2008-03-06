package org.eclipse.dltk.ruby.basicdebugger;

import org.eclipse.jface.preference.IPreferenceStore;

public class RubyBasicDebuggerConstants {

	public static final String ENABLE_LOGGING = "enable_logging";
	public static final String LOG_FILE_PATH = "log_file_path";
	public static final String LOG_FILE_NAME = "log_file_name";
	
	public static void initializeDefaults(IPreferenceStore store) {
		store.setDefault(ENABLE_LOGGING, false);
		store.setDefault(LOG_FILE_NAME, "rubyBasicDebug_{0}.log");
		store.setDefault(LOG_FILE_PATH, "");
	}
	
	private RubyBasicDebuggerConstants() {
		// private constructor
	}
}
