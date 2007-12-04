package org.eclipse.dltk.python.internal.debug.ui.preferences;

import org.eclipse.dltk.debug.ui.preferences.AbstractScriptDebuggingEngineConfigurationBlock;
import org.eclipse.dltk.internal.ui.text.PreferencesAdapter;
import org.eclipse.dltk.python.core.PythonNature;
import org.eclipse.dltk.python.internal.debug.PythonDebugConstants;
import org.eclipse.dltk.python.internal.debug.PythonDebugPlugin;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage;
import org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;

/**
 * Python debug preference page
 */
public class PythonDebugPreferencePage extends
		AbstractConfigurationBlockPreferencePage {
	public static final String PAGE_ID = "org.eclipse.dltk.python.preferences.debug";

	protected static class PythonDebugConfigurationBlock extends
			AbstractScriptDebuggingEngineConfigurationBlock {
		public PythonDebugConfigurationBlock(OverlayPreferenceStore store,
				PreferencePage preferencePage) {
			super(store, preferencePage);
		}

		protected String getContributionPrefKey() {
			return PythonDebugConstants.DEBUGGING_ENGINE_ID_KEY;
		}

		protected String getNatureId() {
			return PythonNature.NATURE_ID;
		}
	}

	protected IPreferenceConfigurationBlock createConfigurationBlock(
			OverlayPreferenceStore overlayPreferenceStore) {
		return new PythonDebugConfigurationBlock(overlayPreferenceStore, this);
	}

	protected String getHelpId() {
		return null;
	}

	protected void setDescription() {
		setDescription(PythonDebugPreferencesMessages.PreferencesDescription);
	}

	protected void setPreferenceStore() {
		setPreferenceStore(new PreferencesAdapter(PythonDebugPlugin.getDefault()
				.getPluginPreferences()));
	}

	public boolean performOk() {
		super.performOk();
	    PythonDebugPlugin.getDefault().savePluginPreferences();
		return true;
	}
}
