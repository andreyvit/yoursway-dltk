package org.eclipse.dltk.tcl.internal.debug.ui;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.dltk.debug.ui.breakpoints.BreakpointUtils;
import org.eclipse.dltk.internal.debug.ui.ScriptToggleBreakpointAdapter;
import org.eclipse.dltk.tcl.internal.debug.TclDebugConstants;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.texteditor.ITextEditor;

public class TclToggleBreakpointAdapter extends ScriptToggleBreakpointAdapter {
	public void toggleLineBreakpoints(IWorkbenchPart part, ISelection selection)
			throws CoreException {
		final IResource resource = getPartResource(part);

		// TODO: if resource == null ?

		if (selection instanceof ITextSelection && resource != null) {
			IBreakpoint[] breakpoints = getBreakpoints(TclDebugConstants.DEBUG_MODEL_ID);

			final int lineNumber = ((ITextSelection) selection).getStartLine() + 1; // one

			IBreakpoint breakpoint = findLineBreakpoint(breakpoints, resource,
					lineNumber);
			if (breakpoint != null) {
				breakpoint.delete();
			} else {
				final ITextEditor textEditor = getTextEditor(part);
				if (textEditor != null) {
					BreakpointUtils.addLineBreakpoint(textEditor, lineNumber);
				}
			}
		}
	}

	public boolean canToggleLineBreakpoints(IWorkbenchPart part,
			ISelection selection) {
		if (isRemote(part, selection)) {
			return false;
		}

		return selection instanceof ITextSelection;
	}

	public void toggleMethodBreakpoints(IWorkbenchPart part,
			ISelection selection) throws CoreException {
		// Not implemented for ruby yet
	}

	public boolean canToggleMethodBreakpoints(IWorkbenchPart part,
			ISelection selection) {
		return false;
	}

	public void toggleWatchpoints(IWorkbenchPart part, ISelection selection)
			throws CoreException {
		// Not implemented for ruby yet
	}

	public boolean canToggleWatchpoints(IWorkbenchPart part,
			ISelection selection) {
		return false;
	}

	public void toggleBreakpoints(IWorkbenchPart part, ISelection selection)
			throws CoreException {
		toggleLineBreakpoints(part, selection);
	}

	public boolean canToggleBreakpoints(IWorkbenchPart part,
			ISelection selection) {
		return canToggleLineBreakpoints(part, selection);
	}
}