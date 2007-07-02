package org.eclipse.dltk.tcl.internal.debug.ui;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.debug.ui.actions.IRunToLineTarget;
import org.eclipse.debug.ui.actions.IToggleBreakpointsTarget;
import org.eclipse.dltk.internal.debug.ui.ScriptRunToLineAdapter;

public class TclEditorDebugAdapterFactory implements IAdapterFactory {
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adapterType == IRunToLineTarget.class) {
			return new ScriptRunToLineAdapter();
		} else if (adapterType == IToggleBreakpointsTarget.class) {
			return new TclToggleBreakpointAdapter();
		}

		return null;
	}

	public Class[] getAdapterList() {
		return new Class[] { IRunToLineTarget.class,
				IToggleBreakpointsTarget.class };
	}
}
