package org.eclipse.dltk.ruby.internal.debug.ui.preferences;

import org.eclipse.dltk.debug.ui.preferences.AbstractScriptDebuggingEngineConfigurationBlock;
import org.eclipse.dltk.internal.ui.text.PreferencesAdapter;
import org.eclipse.dltk.ruby.core.RubyNature;
import org.eclipse.dltk.ruby.debug.RubyDebugConstants;
import org.eclipse.dltk.ruby.debug.RubyDebugPlugin;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage;
import org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;

/**
 * Ruby debug preference page
 */
public class RubyDebugPreferencePage extends
		AbstractConfigurationBlockPreferencePage {
	public static final String PAGE_ID = "org.eclipse.dltk.ruby.preferences.debug";

	protected IPreferenceConfigurationBlock createConfigurationBlock(
			OverlayPreferenceStore overlayPreferenceStore) {
		return new AbstractScriptDebuggingEngineConfigurationBlock(
				overlayPreferenceStore, this) {
			protected String getDebuggingEngineIdKey() {
				return RubyDebugConstants.DEBUGGING_ENGINE_ID_KEY;
			}

			protected String getNatureId() {
				return RubyNature.NATURE_ID;
			}
		};
	}

	protected String getHelpId() {
		return null;
	}

	protected void setDescription() {
		setDescription(RubyDebugPreferencesMessages.PreferencesDescription);
	}

	protected void setPreferenceStore() {
		setPreferenceStore(new PreferencesAdapter(RubyDebugPlugin.getDefault()
				.getPluginPreferences()));
	}

	public boolean performOk() {
		super.performOk();
		RubyDebugPlugin.getDefault().savePluginPreferences();
		return true;
	}
}
