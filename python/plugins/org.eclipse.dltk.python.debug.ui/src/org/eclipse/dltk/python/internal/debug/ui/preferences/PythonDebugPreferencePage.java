package org.eclipse.dltk.python.internal.debug.ui.preferences;

import org.eclipse.dltk.debug.ui.preferences.AbstractScriptDebuggingEngineConfigurationBlock;
import org.eclipse.dltk.internal.ui.text.PreferencesAdapter;
import org.eclipse.dltk.python.core.PythonNature;
import org.eclipse.dltk.python.debug.PythonDebugConstants;
import org.eclipse.dltk.python.debug.PythonDebugPlugin;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage;
import org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;

/**
 * Ruby debug preference page
 */
public class PythonDebugPreferencePage extends
		AbstractConfigurationBlockPreferencePage {
	public static final String PAGE_ID = "org.eclipse.dltk.python.preferences.debug";

	protected static class RubyDebugConfigurationBlock extends
			AbstractScriptDebuggingEngineConfigurationBlock {
		public RubyDebugConfigurationBlock(OverlayPreferenceStore store,
				PreferencePage preferencePage) {
			super(store, preferencePage);
		}

		protected String getDebuggingEngineIdKey() {
			return PythonDebugConstants.DEBUGGING_ENGINE_ID_KEY;
		}

		protected String getNatureId() {
			return PythonNature.NATURE_ID;
		}
	}

	protected IPreferenceConfigurationBlock createConfigurationBlock(
			OverlayPreferenceStore overlayPreferenceStore) {
		return new RubyDebugConfigurationBlock(overlayPreferenceStore, this);
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