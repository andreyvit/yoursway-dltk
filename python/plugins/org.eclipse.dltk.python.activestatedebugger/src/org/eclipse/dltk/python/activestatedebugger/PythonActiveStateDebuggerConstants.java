package org.eclipse.dltk.python.activestatedebugger;

import org.eclipse.jface.preference.IPreferenceStore;

public class PythonActiveStateDebuggerConstants {

	public static final String DEBUGGING_ENGINE_PATH_KEY = "activeStateEnginePath";

	public static void initializeDefaults(IPreferenceStore store) {
		store.setDefault(DEBUGGING_ENGINE_PATH_KEY, "");
	}

	private PythonActiveStateDebuggerConstants() {
		// private constructor
	}
}
