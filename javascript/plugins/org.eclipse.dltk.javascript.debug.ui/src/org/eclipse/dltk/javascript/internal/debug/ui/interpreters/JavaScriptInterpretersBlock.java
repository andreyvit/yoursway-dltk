package org.eclipse.dltk.javascript.internal.debug.ui.interpreters;

import org.eclipse.dltk.internal.debug.ui.interpreters.AddScriptInterpreterDialog;
import org.eclipse.dltk.internal.debug.ui.interpreters.InterpretersBlock;
import org.eclipse.dltk.javascript.core.JavaScriptNature;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.ScriptRuntime;

public class JavaScriptInterpretersBlock extends InterpretersBlock {
	protected AddScriptInterpreterDialog createInterpreterDialog(IInterpreterInstall standin) {
		AddJavaScriptInterpreterDialog dialog = new AddJavaScriptInterpreterDialog(this, 
				getShell(), ScriptRuntime.getInterpreterInstallTypes(getCurrentNature()), 
				standin);
		return dialog;
	}

	protected String getCurrentNature() {
		return JavaScriptNature.NATURE_ID;
	}
}
