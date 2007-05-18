/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.debug.ui.breakpoints;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.debug.internal.core.model.ScriptDebugModel;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.ui.texteditor.ITextEditor;

public class BreakpointUtils {
	public static void addLineBreakpoint(ITextEditor textEditor, int lineNumber)
			throws CoreException {
		IDocument document = textEditor.getDocumentProvider().getDocument(
				textEditor.getEditorInput());

		IResource resource = (IResource) textEditor.getEditorInput()
				.getAdapter(IResource.class);
		if (resource != null) {
			try {
				IRegion line = document.getLineInformation(lineNumber - 1);
				int start = line.getOffset();
				int end = start + line.getLength() - 1;

				/*ILineBreakpoint b = */ScriptDebugModel.createLineBreakpoint(
						resource, lineNumber, start, end, 0, true, null);
			} catch (BadLocationException e) {
				if( DLTKCore.DEBUG ) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void addMethodEntryBreakpoint(ITextEditor textEditor, int lineNumber,String methodName,String methodSignature)
			throws CoreException {
		IDocument document = textEditor.getDocumentProvider().getDocument(
				textEditor.getEditorInput());

		IResource resource = (IResource) textEditor.getEditorInput()
				.getAdapter(IResource.class);
		if (resource != null) {
			try {
				IRegion line = document.getLineInformation(lineNumber - 1);
				int start = line.getOffset();
				int end = start + line.getLength() - 1;
				/*ILineBreakpoint b = */ScriptDebugModel.createMethodEntryBreakpoint(
						resource, lineNumber, start, end, 0, true, null,methodName,methodSignature);
			} catch (BadLocationException e) {
				if( DLTKCore.DEBUG ) {
					e.printStackTrace();
				}
			}
		}
	}
}
