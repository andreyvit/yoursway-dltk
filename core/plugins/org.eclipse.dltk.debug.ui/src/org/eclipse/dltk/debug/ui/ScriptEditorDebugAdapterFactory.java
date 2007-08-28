package org.eclipse.dltk.debug.ui;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.debug.ui.actions.IRunToLineTarget;
import org.eclipse.debug.ui.actions.IToggleBreakpointsTarget;
import org.eclipse.dltk.debug.ui.breakpoints.ScriptToggleBreakpointAdapter;
import org.eclipse.dltk.internal.debug.ui.ScriptRunToLineAdapter;

public abstract class ScriptEditorDebugAdapterFactory implements
		IAdapterFactory {

	/*
	 * @see org.eclipse.core.runtime.IAdapterFactory#getAdapter(java.lang.Object, java.lang.Class)
	 */
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adapterType == IRunToLineTarget.class) {
			return new ScriptRunToLineAdapter();
		} else if (adapterType == IToggleBreakpointsTarget.class) {
			return getBreakpointAdapter();
		}

		return null;
	}

	/*
	 * @see org.eclipse.core.runtime.IAdapterFactory#getAdapterList()
	 */
	public Class[] getAdapterList() {
		return new Class[] { IRunToLineTarget.class,
				IToggleBreakpointsTarget.class };
	}

	/**
	 * Returns the project specific breakpoint adapter.
	 */
	protected abstract ScriptToggleBreakpointAdapter getBreakpointAdapter();
}
