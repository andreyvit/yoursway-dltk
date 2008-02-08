package org.eclipse.dltk.tcl.internal.ui.preferences;

import java.util.ArrayList;

import org.eclipse.dltk.tcl.ui.TclPreferenceConstants;
import org.eclipse.dltk.ui.preferences.CodeAssistConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class TclContentAssistConfigurationBlock extends
		CodeAssistConfigurationBlock {
	public TclContentAssistConfigurationBlock(
			PreferencePage mainPreferencePage, OverlayPreferenceStore store) {
		super(mainPreferencePage, store);
	}

	protected void getOverlayKeys(ArrayList overlayKeys) {
		super.getOverlayKeys(overlayKeys);

		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				TclPreferenceConstants.CODEASSIST_FILTER_INTERNAL_API));
//
//		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
//				OverlayPreferenceStore.STRING,
//				TclPreferenceConstants.CODEASSIST_AUTOACTIVATION_TRIGGERS));
	}

//	protected void addAutoActivationSection(Composite composite) {
//		super.addAutoActivationSection(composite);
//		String label = "Auto activation triggers for &Tcl:";
//		addLabelledTextField(composite, label,
//				PreferenceConstants.CODEASSIST_AUTOACTIVATION_TRIGGERS, 4, 2,
//				false);
//	}

	public Control createControl(Composite parent) {
		Composite control = (Composite) super.createControl(parent);

		GridLayout layout = new GridLayout();
		layout.numColumns = 2;

		Composite composite = createSubsection(control, null, "Filtering");
		composite.setLayout(layout);
		addSortingSection(composite);
		return control;
	}

	protected void addSortingSection(Composite composite) {
		String label = "Filter internal Tcl API";
		addCheckBox(composite, label,
				TclPreferenceConstants.CODEASSIST_FILTER_INTERNAL_API, 2);
	}
}
