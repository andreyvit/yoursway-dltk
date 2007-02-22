package org.eclipse.dltk.tcl.internal.ui.preferences;

import org.eclipse.dltk.tcl.internal.ui.text.folding.TclFoldingPreferenceBlock;
import org.eclipse.dltk.ui.preferences.FoldingConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.dltk.ui.text.folding.IFoldingPreferenceBlock;
import org.eclipse.jface.preference.PreferencePage;



public class TclFoldingConfigurationBlock extends FoldingConfigurationBlock {

	
	
	public TclFoldingConfigurationBlock(OverlayPreferenceStore store, PreferencePage mainPreferencePage) {
		super(store, mainPreferencePage);				
	}
	
	protected IFoldingPreferenceBlock createFoldingPreferenceBlock () {
		return new TclFoldingPreferenceBlock(fStore, getPreferencePage());
	}
	
}
