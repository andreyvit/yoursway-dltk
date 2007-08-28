package org.eclipse.dltk.tcl.internal.debug.ui;

import org.eclipse.dltk.debug.ui.ScriptEditorDebugAdapterFactory;
import org.eclipse.dltk.debug.ui.breakpoints.ScriptToggleBreakpointAdapter;

/**
 * Debug adapter factory for the Tcl editor.
 */
public class TclEditorDebugAdapterFactory extends
		ScriptEditorDebugAdapterFactory {

	/*
	 * @see org.eclipse.dltk.debug.ui.ScriptEditorDebugAdapterFactory#getBreakointAdapter()
	 */
	protected ScriptToggleBreakpointAdapter getBreakpointAdapter() {
		return new TclToggleBreakpointAdapter();
	}
}
