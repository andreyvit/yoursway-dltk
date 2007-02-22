package org.eclipse.dltk.internal.debug.ui;

import java.net.URI;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.model.IDebugElement;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.ISuspendResume;
import org.eclipse.debug.ui.actions.IRunToLineTarget;
import org.eclipse.dltk.debug.core.model.IScriptDebugTarget;
import org.eclipse.dltk.debug.internal.core.model.ScriptLineBreakpoint;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.texteditor.ITextEditor;

public class DltkRunToLineAdapter implements IRunToLineTarget {

	protected ITextEditor getPartEditor(IWorkbenchPart part) {
		if (part instanceof ITextEditor) {
			return (ITextEditor) part;
		}

		return null;
	}

	protected IResource getPartResource(IWorkbenchPart part) {
		ITextEditor textEditor = getPartEditor(part);
		if (textEditor != null) {
			IResource resource = (IResource) textEditor.getEditorInput()
					.getAdapter(IResource.class);
			return resource;
		}

		return null;
	}

	public DltkRunToLineAdapter() {

	}

	public boolean canRunToLine(IWorkbenchPart part, ISelection selection,
			ISuspendResume target) {				
		return true;
	}

	public void runToLine(IWorkbenchPart part, ISelection selection,
			ISuspendResume target) throws CoreException {

		if (selection instanceof ITextSelection) {
			ITextSelection textSelection = (ITextSelection) selection;

			IResource resource = getPartResource(part);

			URI uri = ScriptLineBreakpoint.makeUri(resource);

			int lineNumber = textSelection.getStartLine() + 1; // one based

			if (target instanceof IDebugElement) {
				IDebugTarget debugTarget = ((IDebugElement) target)
						.getDebugTarget();
				if (debugTarget instanceof IScriptDebugTarget) {
					((IScriptDebugTarget) debugTarget).runToLine(uri,
							lineNumber);
					target.resume();
				}
			}
		}
	}
}
