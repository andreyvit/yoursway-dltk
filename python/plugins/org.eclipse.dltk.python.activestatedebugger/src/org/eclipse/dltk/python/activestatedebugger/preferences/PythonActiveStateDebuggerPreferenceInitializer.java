package org.eclipse.dltk.python.activestatedebugger.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;

import org.eclipse.dltk.python.activestatedebugger.PythonActiveStateDebuggerConstants;
import org.eclipse.dltk.python.activestatedebugger.PythonActiveStateDebuggerPlugin;

import org.eclipse.jface.preference.IPreferenceStore;

public class PythonActiveStateDebuggerPreferenceInitializer extends
		AbstractPreferenceInitializer {

	public void initializeDefaultPreferences() {
		IPreferenceStore store = PythonActiveStateDebuggerPlugin.getDefault()
				.getPreferenceStore();

		PythonActiveStateDebuggerConstants.initializeDefaults(store);
	}
}
