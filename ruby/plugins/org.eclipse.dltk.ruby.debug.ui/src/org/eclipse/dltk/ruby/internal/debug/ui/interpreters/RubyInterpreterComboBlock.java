package org.eclipse.dltk.ruby.internal.debug.ui.interpreters;


import org.eclipse.dltk.internal.debug.ui.interpreters.AbstractInterpreterComboBlock;
import org.eclipse.dltk.ruby.core.RubyNature;
import org.eclipse.jface.preference.IPreferencePage;

public class RubyInterpreterComboBlock extends AbstractInterpreterComboBlock {
	
	protected void showInterpreterPreferencePage()  { 
		IPreferencePage page = new RubyInterpreterPreferencePage(); 
		showPrefPage("org.eclipse.dltk.ruby.debug.ui.interpreters.RubyInterpreterPreferencePage", page); //$NON-NLS-1$
	}

	protected String getCurrentLanguageNature () {
		return RubyNature.NATURE_ID;
	}
	
}
