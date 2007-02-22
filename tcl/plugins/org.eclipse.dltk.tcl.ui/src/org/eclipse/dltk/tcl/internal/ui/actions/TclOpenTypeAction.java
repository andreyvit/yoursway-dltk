package org.eclipse.dltk.tcl.internal.ui.actions;

import org.eclipse.dltk.tcl.internal.ui.TclUILanguageToolkit;
import org.eclipse.dltk.ui.IDLTKUILanguageToolkit;
import org.eclipse.dltk.ui.actions.OpenTypeAction;

public class TclOpenTypeAction extends OpenTypeAction {
	protected IDLTKUILanguageToolkit getUILanguageToolkit() {
		return TclUILanguageToolkit.getInstance();
	}
}
