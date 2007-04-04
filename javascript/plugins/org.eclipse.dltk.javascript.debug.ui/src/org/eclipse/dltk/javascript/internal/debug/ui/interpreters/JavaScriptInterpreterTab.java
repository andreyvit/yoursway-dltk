package org.eclipse.dltk.javascript.internal.debug.ui.interpreters;

import org.eclipse.dltk.debug.ui.launchConfigurations.InterpreterTab;
import org.eclipse.dltk.internal.debug.ui.interpreters.AbstractInterpreterComboBlock;
import org.eclipse.dltk.javascript.core.JavaScriptNature;

public class JavaScriptInterpreterTab extends InterpreterTab {
	
	protected AbstractInterpreterComboBlock getInterpreterBlock() {
		return new JavaScriptInterpreterComboBlock();
	}

	protected String getNature() {
		return JavaScriptNature.NATURE_ID;
	}
	
}
