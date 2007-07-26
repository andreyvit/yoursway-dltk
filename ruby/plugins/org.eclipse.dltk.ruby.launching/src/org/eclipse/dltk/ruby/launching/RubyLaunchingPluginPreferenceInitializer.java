package org.eclipse.dltk.ruby.launching;

import org.eclipse.core.runtime.Preferences;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;

public class RubyLaunchingPluginPreferenceInitializer extends
		AbstractPreferenceInitializer {

	public RubyLaunchingPluginPreferenceInitializer() {
		super();
	}

	public void initializeDefaultPreferences() {
		final Preferences prefs = RubyLaunchingPlugin.getDefault()
				.getPluginPreferences();
	}
}
