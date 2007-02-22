package org.eclipse.dltk.ruby.internal.debug.ui.interpreters;

import org.eclipse.dltk.debug.ui.launchConfigurations.InterpreterTab;
import org.eclipse.dltk.internal.debug.ui.interpreters.AbstractInterpreterComboBlock;
import org.eclipse.dltk.ruby.core.RubyNature;

public class RubyInterpreterTab extends InterpreterTab {
	
	protected AbstractInterpreterComboBlock getInterpreterBlock() {
		return new RubyInterpreterComboBlock();
	}

	protected String getNature() {
		return RubyNature.NATURE_ID;
	}
	
}
