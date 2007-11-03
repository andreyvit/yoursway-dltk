package org.eclipse.dltk.python.launching;

import org.eclipse.core.runtime.Preferences;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;

public class PythonLaunchingPluginPreferenceInitializer extends
		AbstractPreferenceInitializer {

	public PythonLaunchingPluginPreferenceInitializer() {
		super();
	}

	public void initializeDefaultPreferences() {
		final Preferences prefs = PythonLaunchingPlugin.getDefault()
				.getPluginPreferences();
	}
}
