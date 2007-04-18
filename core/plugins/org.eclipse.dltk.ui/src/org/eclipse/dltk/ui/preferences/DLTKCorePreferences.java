package org.eclipse.dltk.ui.preferences;

import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class DLTKCorePreferences extends
		AbstractConfigurationBlockPreferencePage {

	private static class GlobalConfigurationBlock extends
			AbstractConfigurationBlock {

		public GlobalConfigurationBlock(OverlayPreferenceStore store,
				PreferencePage mainPreferencePage) {
			super(store, mainPreferencePage);		
		}

		public Control createControl(Composite parent) {
			initializeDialogUnits(parent);
			Composite composite = new Composite(parent, SWT.NONE);
			composite.setLayout(new GridLayout());
			return composite;
		}
	}

	protected IPreferenceConfigurationBlock createConfigurationBlock(
			OverlayPreferenceStore overlayPreferenceStore) {
		return new GlobalConfigurationBlock(overlayPreferenceStore, this);
	}

	protected String getHelpId() {
		return null;
	}

	protected void setDescription() {
		setDescription("DLTK Core preferences");
	}

	protected void setPreferenceStore() {
		setPreferenceStore(DLTKUIPlugin.getDefault().getPreferenceStore());
	}
}
