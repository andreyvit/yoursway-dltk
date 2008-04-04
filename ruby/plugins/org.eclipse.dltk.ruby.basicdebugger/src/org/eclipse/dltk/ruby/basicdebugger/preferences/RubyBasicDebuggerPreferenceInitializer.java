package org.eclipse.dltk.ruby.basicdebugger.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.dltk.ruby.basicdebugger.RubyBasicDebuggerConstants;
import org.eclipse.dltk.ruby.basicdebugger.RubyBasicDebuggerPlugin;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Preference initializer for the 'basic' ruby debugger
 */
public class RubyBasicDebuggerPreferenceInitializer extends
		AbstractPreferenceInitializer {

	/*
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = RubyBasicDebuggerPlugin.getDefault()
				.getPreferenceStore();

		RubyBasicDebuggerConstants.initializeDefaults(store);
	}
}
