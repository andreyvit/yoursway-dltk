package org.eclipse.dltk.ruby.fastdebugger.preferences;

import org.eclipse.core.runtime.Preferences;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.dltk.ruby.debug.RubyDebugConstants;
import org.eclipse.dltk.ruby.debug.RubyDebugPlugin;
import org.eclipse.dltk.ruby.fastdebugger.FastDebuggerConstants;
import org.eclipse.dltk.ruby.fastdebugger.FastDebuggerPlugin;
import org.eclipse.dltk.ruby.fastdebugger.FastDebuggerRunner;
import org.eclipse.jface.preference.IPreferenceStore;

public class FastDebuggerPreferenceInitializer extends
		AbstractPreferenceInitializer {

	public void initializeDefaultPreferences() {
		IPreferenceStore store = FastDebuggerPlugin.getDefault()
				.getPreferenceStore();

		FastDebuggerConstants.initializeDefaults(store);

		Preferences rubyDebugStore = RubyDebugPlugin.getDefault()
				.getPluginPreferences();
		rubyDebugStore.setDefault(RubyDebugConstants.DEBUGGING_ENGINE_ID_KEY,
				FastDebuggerRunner.ENGINE_ID);
	}
}
