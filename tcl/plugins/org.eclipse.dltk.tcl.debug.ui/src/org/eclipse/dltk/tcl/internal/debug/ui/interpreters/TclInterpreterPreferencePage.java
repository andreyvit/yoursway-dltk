package org.eclipse.dltk.tcl.internal.debug.ui.interpreters;

import org.eclipse.dltk.internal.debug.ui.interpreters.InterpreterPreferencePage;
import org.eclipse.dltk.internal.debug.ui.interpreters.InterpretersBlock;

public class TclInterpreterPreferencePage extends InterpreterPreferencePage {

	public static final String PAGE_ID = "org.eclipse.dltk.tcl.debug.ui.interpreters.TclInterpreterPreferencePage";

	public InterpretersBlock createInterpretersBlock() {
		return new TclInterpretersBlock();
	}
}
