package org.eclipse.dltk.ruby.fastdebugger.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;

import org.eclipse.dltk.ruby.fastdebugger.FastDebuggerPlugin;
import org.eclipse.dltk.ruby.fastdebugger.FastDebuggerConstants;

import org.eclipse.jface.preference.IPreferenceStore;

public class FastDebuggerPreferenceInitializer extends
		AbstractPreferenceInitializer {

	public void initializeDefaultPreferences() {
		IPreferenceStore store = FastDebuggerPlugin.getDefault()
				.getPreferenceStore();

		FastDebuggerConstants.initializeDefaults(store);
	}
}
