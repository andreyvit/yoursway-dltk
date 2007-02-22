package org.eclipse.dltk.tcl.internal.debug.ui.interpreters;

import org.eclipse.dltk.debug.ui.launchConfigurations.InterpreterTab;
import org.eclipse.dltk.internal.debug.ui.interpreters.AbstractInterpreterComboBlock;
import org.eclipse.dltk.tcl.core.TclNature;

public class TclInterpreterTab extends InterpreterTab {
	
	protected AbstractInterpreterComboBlock getInterpreterBlock() {
		return new TclInterpreterComboBlock();
	}

	protected String getNature() {
		return TclNature.NATURE_ID;
	}
	
}
