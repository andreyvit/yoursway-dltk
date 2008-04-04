package org.eclipse.dltk.debug.ui;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.internal.ui.editor.EditorUtility;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.console.IHyperlink;
import org.eclipse.ui.console.TextConsole;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

public abstract class ScriptDebugConsoleGenericHyperlink implements IHyperlink {
	private TextConsole fConsole;

	public ScriptDebugConsoleGenericHyperlink(TextConsole console) {
		this.fConsole = console;
	}

	public void linkEntered() {
	}

	public void linkExited() {
	}

	protected TextConsole getConsole() {
		return fConsole;
	}

	/**
	 * Returns this link's text
	 * 
	 * @exception CoreException
	 *                if unable to retrieve the text
	 */
	protected String getLinkText() throws CoreException {
		try {
			IDocument document = getConsole().getDocument();
			IRegion region = getConsole().getRegion(this);
			int regionOffset = region.getOffset();

			int lineNumber = document.getLineOfOffset(regionOffset);
			IRegion lineInformation = document.getLineInformation(lineNumber);
			int lineOffset = lineInformation.getOffset();
			String line = document.get(lineOffset, lineInformation.getLength());

			return line;
		} catch (BadLocationException e) {
			return ""; //$NON-NLS-1$
		}
	}

	protected abstract String getFileName(String linkText) throws CoreException;

	protected abstract int getLineNumber(String linkText) throws CoreException;

	public void linkActivated() {
		try {
			String fileName;
			int lineNumber;
			try {
				String linkText = getLinkText();
				fileName = getFileName(linkText);
				lineNumber = getLineNumber(linkText);
			} catch (CoreException e1) {
				return;
			}

			// documents start at 0
			if (lineNumber > 0) {
				lineNumber--;
			}
			Object sourceElement = getSourceModule(fileName);
			if (sourceElement != null) {
				IEditorPart part = EditorUtility.openInEditor(sourceElement);
				IEditorPart editorPart = EditorUtility
						.openInEditor(sourceElement);
				if (editorPart instanceof ITextEditor && lineNumber >= 0) {
					ITextEditor textEditor = (ITextEditor) editorPart;
					IDocumentProvider provider = textEditor
							.getDocumentProvider();
					IEditorInput input = part.getEditorInput();
					provider.connect(input);
					IDocument document = provider.getDocument(input);
					try {
						IRegion line = document.getLineInformation(lineNumber);
						textEditor.selectAndReveal(line.getOffset(), line
								.getLength());
					} catch (BadLocationException e) {

					}
					provider.disconnect(input);
				}
				return;
			}
			// did not find source
		} catch (CoreException e) {
			return;
		}
	}

	public String getEditorId(IEditorInput input, Object inputObject) {
		try {
			IEditorDescriptor descriptor = IDE.getEditorDescriptor(input
					.getName());
			return descriptor.getId();
		} catch (PartInitException e) {
			return null;
		}
	}

	public IEditorInput getEditorInput(Object item) {
		try {
			return EditorUtility.getEditorInput(item);
		} catch (CoreException e) {
// DLTKDebugUIPlugin.log(e);
			return null;
		}
	}

	protected Object getSourceModule(String fileName) throws CoreException {
		IFile f = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(
				new Path(fileName));
		return f;
	}
}
