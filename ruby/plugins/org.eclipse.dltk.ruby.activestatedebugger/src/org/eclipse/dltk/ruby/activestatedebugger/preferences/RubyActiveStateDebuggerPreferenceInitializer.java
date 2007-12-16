package org.eclipse.dltk.ruby.activestatedebugger.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;

import org.eclipse.dltk.ruby.activestatedebugger.RubyActiveStateDebuggerConstants;
import org.eclipse.dltk.ruby.activestatedebugger.RubyActiveStateDebuggerPlugin;

import org.eclipse.jface.preference.IPreferenceStore;

public class RubyActiveStateDebuggerPreferenceInitializer extends
		AbstractPreferenceInitializer {

	/*
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = RubyActiveStateDebuggerPlugin.getDefault()
				.getPreferenceStore();

		RubyActiveStateDebuggerConstants.initializeDefaults(store);
	}
}
