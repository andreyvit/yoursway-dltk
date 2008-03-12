package org.eclipse.dltk.debug.ui;

import org.eclipse.core.runtime.Preferences;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;

public class DLTKDebugUIPluginPreferenceInitializer extends
		AbstractPreferenceInitializer {

	public DLTKDebugUIPluginPreferenceInitializer() {
		super();
	}

	public void initializeDefaultPreferences() {
		Preferences prefs = DLTKDebugUIPlugin.getDefault()
				.getPluginPreferences();
		
		prefs.setDefault(
				IDLTKDebugUIPreferenceConstants.PREF_ACTIVE_FILTERS_LIST, ""); //$NON-NLS-1$
		prefs.setDefault(
				IDLTKDebugUIPreferenceConstants.PREF_INACTIVE_FILTERS_LIST, ""); //$NON-NLS-1$

		prefs.setDefault(IDLTKDebugUIPreferenceConstants.PREF_ALERT_HCR_FAILED,
				true);
		prefs.setDefault(
				IDLTKDebugUIPreferenceConstants.PREF_ALERT_HCR_NOT_SUPPORTED,
				true);
	}

}
