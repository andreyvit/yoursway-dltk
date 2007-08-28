package org.eclipse.dltk.ruby.internal.debug.ui;

import org.eclipse.dltk.debug.ui.ScriptEditorDebugAdapterFactory;
import org.eclipse.dltk.debug.ui.breakpoints.ScriptToggleBreakpointAdapter;

/**
 * Debug adapter factory for the Ruby editor.
 */
public class RubyEditorDebugAdapterFactory extends
		ScriptEditorDebugAdapterFactory {

	/*
	 * @see org.eclipse.dltk.debug.ui.ScriptEditorDebugAdapterFactory#getBreakointAdapter()
	 */
	protected ScriptToggleBreakpointAdapter getBreakpointAdapter() {
		return new RubyToggleBreakpointAdapter();
	}
}
