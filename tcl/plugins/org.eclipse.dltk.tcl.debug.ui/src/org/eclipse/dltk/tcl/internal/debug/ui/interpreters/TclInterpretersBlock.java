package org.eclipse.dltk.tcl.internal.debug.ui.interpreters;

import org.eclipse.dltk.internal.debug.ui.interpreters.AddDLTKInterpreterDialog;
import org.eclipse.dltk.internal.debug.ui.interpreters.InterpretersBlock;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.ScriptRuntime;
import org.eclipse.dltk.tcl.core.TclNature;

public class TclInterpretersBlock extends InterpretersBlock {
	protected AddDLTKInterpreterDialog createInterpreterDialog(IInterpreterInstall standin) {
		AddTclInterpreterDialog dialog = new AddTclInterpreterDialog(this, 
				getShell(), ScriptRuntime.getInterpreterInstallTypes(getCurrentNature()), 
				standin);
		return dialog;
	}

	protected String getCurrentNature() {
		return TclNature.NATURE_ID;
	}
}
