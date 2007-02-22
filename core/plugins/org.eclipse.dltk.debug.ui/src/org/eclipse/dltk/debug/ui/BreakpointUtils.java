package org.eclipse.dltk.debug.ui;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.model.ILineBreakpoint;
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

				ILineBreakpoint b = ScriptDebugModel.createLineBreakpoint(
						resource, lineNumber, start, end, 0, true, null);
			} catch (BadLocationException e) {
				// TODO: log exception
				e.printStackTrace();
			}
		}
	}
}
