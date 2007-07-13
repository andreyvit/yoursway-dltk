package org.eclipse.dltk.tcl.internal.debug.ui.preferences;

import org.eclipse.dltk.debug.ui.preferences.AbstractScriptDebuggingEngineConfigurationBlock;
import org.eclipse.dltk.internal.ui.text.PreferencesAdapter;
import org.eclipse.dltk.tcl.core.TclNature;
import org.eclipse.dltk.tcl.internal.debug.TclDebugConstants;
import org.eclipse.dltk.tcl.internal.debug.TclDebugPlugin;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage;
import org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;

/**
 * Tcl debug preference page
 */
public class TclDebugPreferencePage extends
		AbstractConfigurationBlockPreferencePage {
	public static final String PAGE_ID = "org.eclipse.dltk.tcl.preferences.debug";

	protected static class TclDebugConfigurationBlock extends
			AbstractScriptDebuggingEngineConfigurationBlock {
		public TclDebugConfigurationBlock(OverlayPreferenceStore store,
				PreferencePage preferencePage) {
			super(store, preferencePage);
		}

		protected String getDebuggingEngineIdKey() {
			return TclDebugConstants.DEBUGGING_ENGINE_ID_KEY;
		}

		protected String getNatureId() {
			return TclNature.NATURE_ID;
		}
	}

	protected IPreferenceConfigurationBlock createConfigurationBlock(
			OverlayPreferenceStore overlayPreferenceStore) {
		return new TclDebugConfigurationBlock(overlayPreferenceStore, this);
	}

	protected String getHelpId() {
		return null;
	}

	protected void setDescription() {
		setDescription(TclDebugPreferencesMessages.PreferencesDescription);
	}

	protected void setPreferenceStore() {
		setPreferenceStore(new PreferencesAdapter(TclDebugPlugin.getDefault()
				.getPluginPreferences()));
	}

	public boolean performOk() {
		super.performOk();
		TclDebugPlugin.getDefault().savePluginPreferences();
		return true;
	}
}
