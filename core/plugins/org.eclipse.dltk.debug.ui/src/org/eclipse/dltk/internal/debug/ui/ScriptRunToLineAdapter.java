/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.debug.ui;

import java.net.URI;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.model.IDebugElement;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.ISuspendResume;
import org.eclipse.debug.ui.actions.IRunToLineTarget;
import org.eclipse.dltk.debug.core.model.IScriptDebugTarget;
import org.eclipse.dltk.internal.debug.core.model.ScriptLineBreakpoint;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.texteditor.ITextEditor;

public class ScriptRunToLineAdapter implements IRunToLineTarget {

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

	public ScriptRunToLineAdapter() {

	}

	public boolean canRunToLine(IWorkbenchPart part, ISelection selection,
			ISuspendResume target) {				
		return true;
	}

	public void runToLine(IWorkbenchPart part, ISelection selection,
			ISuspendResume target) throws CoreException {

		if (selection instanceof ITextSelection) {
			final ITextSelection textSelection = (ITextSelection) selection;
			final IResource resource = getPartResource(part);
			final URI uri = ScriptLineBreakpoint.makeUri(resource.getLocation());
			int humanLineNumber = textSelection.getStartLine() + 1; // one based

			if (target instanceof IDebugElement) {
				IDebugTarget debugTarget = ((IDebugElement) target)
						.getDebugTarget();
				
				if (debugTarget instanceof IScriptDebugTarget) {
					((IScriptDebugTarget) debugTarget).runToLine(uri,
							humanLineNumber);					
				}
			}
		}
	}
}
