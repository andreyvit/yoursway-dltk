package org.eclipse.dltk.javascript.internal.debug.ui.interpreters;

import org.eclipse.dltk.internal.debug.ui.interpreters.AbstractInterpreterComboBlock;
import org.eclipse.dltk.javascript.core.JavaScriptNature;
import org.eclipse.jface.preference.IPreferencePage;


public class JavaScriptInterpreterComboBlock extends AbstractInterpreterComboBlock {
	
	protected void showInterpreterPreferencePage()  { 
		IPreferencePage page = new JavaScriptInterpreterPreferencePage(); 
		//showPrefPage("org.eclipse.dltk.tcl.debug.ui.interpreters.TclInterpreterPreferencePage", page); //$NON-NLS-1$
	}

	protected String getCurrentLanguageNature() {
		return JavaScriptNature.NATURE_ID;
	}
}
