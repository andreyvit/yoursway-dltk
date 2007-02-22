package org.eclipse.dltk.tcl.internal.debug.ui.interpreters;

import org.eclipse.dltk.internal.debug.ui.interpreters.AbstractInterpreterComboBlock;
import org.eclipse.dltk.tcl.core.TclNature;
import org.eclipse.jface.preference.IPreferencePage;


public class TclInterpreterComboBlock extends AbstractInterpreterComboBlock {
	
	protected void showInterpreterPreferencePage()  { 
		IPreferencePage page = new TclInterpreterPreferencePage(); 
		showPrefPage("org.eclipse.dltk.tcl.debug.ui.interpreters.TclInterpreterPreferencePage", page); //$NON-NLS-1$
	}

	protected String getCurrentLanguageNature() {
		return TclNature.NATURE_ID;
	}
}
