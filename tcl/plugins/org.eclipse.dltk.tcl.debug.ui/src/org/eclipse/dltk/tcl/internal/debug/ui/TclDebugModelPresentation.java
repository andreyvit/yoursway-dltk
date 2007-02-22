package org.eclipse.dltk.tcl.internal.debug.ui;

import org.eclipse.dltk.debug.ui.ScriptDebugModelPresentation;
import org.eclipse.ui.IEditorInput;

public class TclDebugModelPresentation extends ScriptDebugModelPresentation {
	public String getEditorId(IEditorInput input, Object element) {
		return "org.eclipse.dltk.tcl.ui.editor.TclEditor";
	}
}
