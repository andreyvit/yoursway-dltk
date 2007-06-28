package org.eclipse.dltk.ruby.internal.debug.ui.preferences;

import org.eclipse.dltk.debug.ui.preferences.AbstractScriptDebuggingEngineConfigurationBlock;
import org.eclipse.dltk.internal.ui.text.PreferencesAdapter;
import org.eclipse.dltk.ruby.core.RubyNature;
import org.eclipse.dltk.ruby.internal.launching.debug.RubyDebuggingConstants;
import org.eclipse.dltk.ruby.launching.RubyLaunchingPlugin;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage;
import org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;

/**
 * Ruby debug preference page
 */
public class RubyDebugPreferencePage extends
		AbstractConfigurationBlockPreferencePage {
	protected static class RubyDebugConfigurationBlock extends
			AbstractScriptDebuggingEngineConfigurationBlock {
		public RubyDebugConfigurationBlock(OverlayPreferenceStore store,
				PreferencePage preferencePage) {
			super(store, preferencePage);
		}

		protected String getDebuggingEngineIdKey() {
			return RubyDebuggingConstants.DEBUGGING_ENGINE_ID_KEY;
		}

		protected String getNatureId() {
			return RubyNature.NATURE_ID;
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
		setDescription(RubyDebugPreferencesMessages.PreferencesDescription);
	}

	protected void setPreferenceStore() {
		setPreferenceStore(new PreferencesAdapter(RubyLaunchingPlugin
				.getDefault().getPluginPreferences()));
	}

	public boolean performOk() {
		super.performOk();
		RubyLaunchingPlugin.getDefault().savePluginPreferences();
		return true;
	}
}
