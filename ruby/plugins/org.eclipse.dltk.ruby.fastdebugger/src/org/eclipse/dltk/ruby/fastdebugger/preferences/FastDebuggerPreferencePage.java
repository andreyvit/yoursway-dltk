package org.eclipse.dltk.ruby.fastdebugger.preferences;

import org.eclipse.dltk.ruby.fastdebugger.FastDebuggerPlugin;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage;
import org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;

public class FastDebuggerPreferencePage extends
		AbstractConfigurationBlockPreferencePage {

	public static final String PAGE_ID = FastDebuggerPreferencePage.class
			.getName();

	protected IPreferenceConfigurationBlock createConfigurationBlock(
			OverlayPreferenceStore overlayPreferenceStore) {
		return new FastDebuggerConfigurationBlock(overlayPreferenceStore, this);
	}

	protected String getHelpId() {
		return null;
	}

	protected void setDescription() {
		setDescription(FastDebuggerPreferenceMessages.PreferencesDescription);
	}

	protected void setPreferenceStore() {
		setPreferenceStore(FastDebuggerPlugin.getDefault()
				.getPreferenceStore());
	}

	public boolean performOk() {
		super.performOk();
		FastDebuggerPlugin.getDefault().savePluginPreferences();
		return true;
	}
}
