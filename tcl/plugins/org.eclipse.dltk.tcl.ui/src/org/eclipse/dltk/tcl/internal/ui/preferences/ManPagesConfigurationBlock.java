package org.eclipse.dltk.tcl.internal.ui.preferences;

import java.util.ArrayList;

import org.eclipse.dltk.tcl.internal.ui.documentation.ManPagesLocationsBlock;
import org.eclipse.dltk.tcl.ui.TclPreferenceConstants;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;



class ManPagesConfigurationBlock extends AbstractConfigurationBlock {

	private ManPagesLocationsBlock fBlock;
	
	public ManPagesConfigurationBlock(OverlayPreferenceStore store, PreferencePage page) {
		super(store);

		store.addKeys(createOverlayStoreKeys());
		
		fBlock = new ManPagesLocationsBlock(store, page);
	}

	private OverlayPreferenceStore.OverlayKey[] createOverlayStoreKeys() {

		ArrayList overlayKeys = new ArrayList();

		overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.STRING,
				TclPreferenceConstants.DOC_MAN_PAGES_LOCATIONS));
		
		OverlayPreferenceStore.OverlayKey[] keys = new OverlayPreferenceStore.OverlayKey[overlayKeys
				.size()];
		overlayKeys.toArray(keys);
		return keys;

	}

	/**
	 * Creates page for mark occurrences preferences.
	 * 
	 * @param parent
	 *            the parent composite
	 * @return the control for the preference page
	 */
	public Control createControl(Composite parent) {
		Composite control = new Composite(parent, SWT.NONE);// parent=scrolled
		GridLayout layout = new GridLayout();
		control.setLayout(layout);
		
		fBlock.createControl(control);

		return control;
	}

	public void initialize() {
		super.initialize();
		fBlock.initialize();
	}

	public void performDefaults() {
		super.performDefaults();
		fBlock.setDefaults();
	}

	public void performOk() {
		super.performOk();
		fBlock.performApply();
	}
	
	

}