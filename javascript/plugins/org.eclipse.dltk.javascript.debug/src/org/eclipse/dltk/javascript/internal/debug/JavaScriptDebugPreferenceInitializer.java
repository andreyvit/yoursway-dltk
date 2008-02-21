package org.eclipse.dltk.javascript.internal.debug;

import org.eclipse.core.runtime.Preferences;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.dltk.debug.core.DLTKDebugPreferenceConstants;

public class JavaScriptDebugPreferenceInitializer extends
		AbstractPreferenceInitializer {

	public void initializeDefaultPreferences() {
		Preferences store = JavaScriptDebugPlugin.getDefault().getPluginPreferences();

		store.setDefault(JavaScriptDebugConstants.DEBUGGING_ENGINE_ID_KEY, "");
		
		store.setDefault(
				DLTKDebugPreferenceConstants.PREF_DBGP_BREAK_ON_FIRST_LINE,
				false);
		store.setDefault(DLTKDebugPreferenceConstants.PREF_DBGP_ENABLE_LOGGING,
				false);
	}

}
