package org.eclipse.dltk.python.internal.debug.ui;

import org.eclipse.dltk.debug.ui.ScriptEditorDebugAdapterFactory;
import org.eclipse.dltk.debug.ui.breakpoints.ScriptToggleBreakpointAdapter;

/**
 * Debug adapter factory for the Python editor.
 */
public class PythonEditorDebugAdapterFactory extends
		ScriptEditorDebugAdapterFactory {

	/*
	 * @see org.eclipse.dltk.debug.ui.ScriptEditorDebugAdapterFactory#getBreakpointAdapter()
	 */
	protected ScriptToggleBreakpointAdapter getBreakpointAdapter() {
		return new PythonToggleBreakpointAdapter();
	}

}
