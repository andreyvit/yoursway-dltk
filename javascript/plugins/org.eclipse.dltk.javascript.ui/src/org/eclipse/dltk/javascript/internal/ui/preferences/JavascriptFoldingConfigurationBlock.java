package org.eclipse.dltk.javascript.internal.ui.preferences;

import org.eclipse.dltk.javascript.internal.ui.text.folding.JavascriptFoldingPreferenceBlock;
import org.eclipse.dltk.ui.preferences.FoldingConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.dltk.ui.text.folding.IFoldingPreferenceBlock;
import org.eclipse.jface.preference.PreferencePage;


public class JavascriptFoldingConfigurationBlock extends FoldingConfigurationBlock {

	public JavascriptFoldingConfigurationBlock(OverlayPreferenceStore store, PreferencePage p) {
		super(store, p);		
	}
	
	protected IFoldingPreferenceBlock createFoldingPreferenceBlock () {
		return new JavascriptFoldingPreferenceBlock(fStore);
	}
}
