package org.eclipse.dltk.python.activestatedebugger;

import org.eclipse.jface.preference.IPreferenceStore;

public class PythonActiveStateDebuggerConstants {

	public static final String DEBUGGING_ENGINE_PATH_KEY = "activeStateEnginePath";

	public static final String ENABLE_LOGGING = "enable_logging";
	public static final String LOG_FILE_PATH = "log_file_path";
	public static final String LOG_FILE_NAME = "log_file_name";
	
	public static void initializeDefaults(IPreferenceStore store) {
		store.setDefault(DEBUGGING_ENGINE_PATH_KEY, "");
		
		store.setDefault(ENABLE_LOGGING, false);
		store.setDefault(LOG_FILE_NAME, "pythonDebug_{0}.log");
		store.setDefault(LOG_FILE_PATH, "");
	}

	private PythonActiveStateDebuggerConstants() {
		// private constructor
	}
}
