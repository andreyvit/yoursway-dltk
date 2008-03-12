/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.debug.ui.breakpoints;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.ui.actions.IToggleBreakpointsTargetExtension;
import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.debug.ui.DLTKDebugUIPlugin;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.IWorkingCopyManager;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.IEditorStatusLine;
import org.eclipse.ui.texteditor.ITextEditor;

import java.text.MessageFormat;

public abstract class ScriptToggleBreakpointAdapter implements
		IToggleBreakpointsTargetExtension {

	protected boolean isRemote(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection ss = (IStructuredSelection) selection;
			Object element = ss.getFirstElement();
			if (element instanceof IMember) {
				IMember member = (IMember) element;
				return !member.getScriptProject().getProject().exists();
			}
		}
		ITextEditor editor = getTextEditor(part);
		if (editor != null) {
			IEditorInput input = editor.getEditorInput();
			Object adapter = Platform.getAdapterManager().getAdapter(input,
					"org.eclipse.team.core.history.IFileRevision"); //$NON-NLS-1$
			return adapter != null;
		}
		return false;
	}

	protected String getSelectedFirstLine(ITextEditor editor,
			ITextSelection selection) {
		try {
			IDocument doc = editor.getDocumentProvider().getDocument(null);
			final int line = selection.getStartLine();
			IRegion region = doc.getLineInformation(line /* - 1 */);
			return doc.get(region.getOffset(), region.getLength());
		} catch (BadLocationException e) {
			DLTKUIPlugin.log(e);
		}

		return null;
	}

	protected String getSelectedText(ITextEditor editor,
			ITextSelection selection) {
		try {
			IDocument doc = editor.getDocumentProvider().getDocument(null);
			return doc.get(selection.getOffset(), selection.getLength());
		} catch (BadLocationException e) {
			DLTKUIPlugin.log(e);
		}

		return null;
	}

	protected void report(final String message, final IWorkbenchPart part) {
		DLTKDebugUIPlugin.getStandardDisplay().asyncExec(new Runnable() {
			public void run() {
				IEditorStatusLine statusLine = (IEditorStatusLine) part
						.getAdapter(IEditorStatusLine.class);
				if (statusLine != null) {
					if (message != null) {
						statusLine.setMessage(true, message, null);
					} else {
						statusLine.setMessage(true, null, null);
					}
				}
				if (message != null
						&& DLTKDebugUIPlugin.getActiveWorkbenchShell() != null) {
					DLTKDebugUIPlugin.getActiveWorkbenchShell().getDisplay()
							.beep();
				}
			}
		});
	}

	protected ITextEditor getTextEditor(IWorkbenchPart part) {
		if (part instanceof ITextEditor) {
			return (ITextEditor) part;
		}
		return (ITextEditor) part.getAdapter(ITextEditor.class);
	}

	protected static final int BREAKPOINT_LINE_NOT_FOUND = -1;

	protected static int findBreakpointLine(IDocument document, int startLine,
			IScriptBreakpointLineValidator validator) {
		String line = null;
		int lineNumber = startLine;
		int numberOfLines = document.getNumberOfLines();

		if (lineNumber < 0 || lineNumber >= numberOfLines) {
			return BREAKPOINT_LINE_NOT_FOUND;
		}

		while (lineNumber < numberOfLines) {
			try {
				IRegion region = document.getLineInformation(lineNumber);
				line = document.get(region.getOffset(), region.getLength());

				if (validator.isValid(line, lineNumber)) {
					return lineNumber;
				}

				lineNumber++;
			} catch (BadLocationException e) {
				DLTKDebugUIPlugin.log(e);
			}
		}

		return BREAKPOINT_LINE_NOT_FOUND;
	}

	public ScriptToggleBreakpointAdapter() {

	}

	public boolean canToggleLineBreakpoints(IWorkbenchPart part,
			ISelection selection) {
		if (isRemote(part, selection)) {
			return false;
		}

		return selection instanceof ITextSelection;
	}

	/**
	 * Returns a selection of the member in the given text selection, or the
	 * original selection if none.
	 * 
	 * @param part
	 * @param selection
	 * @return a structured selection of the member in the given text selection,
	 *         or the original selection if none
	 * @exception CoreException
	 *                if an exception occurs
	 */
	protected ISelection translateToMembers(IWorkbenchPart part,
			ISelection selection) throws CoreException {
		ITextEditor textEditor = getTextEditor(part);
		if (textEditor != null && selection instanceof ITextSelection) {
			ITextSelection textSelection = (ITextSelection) selection;
			IEditorInput editorInput = textEditor.getEditorInput();
			IDocumentProvider documentProvider = textEditor
					.getDocumentProvider();
			if (documentProvider == null) {
				throw new CoreException(Status.CANCEL_STATUS);
			}
			IDocument document = documentProvider.getDocument(editorInput);
			int offset = textSelection.getOffset();
			if (document != null) {
				try {
					IRegion region = document
							.getLineInformationOfOffset(offset);
					int end = region.getOffset() + region.getLength();
					while (Character.isWhitespace(document.getChar(offset))
							&& offset < end) {
						offset++;
					}
				} catch (BadLocationException e) {
				}
			}
			IMember m = null;

			IWorkingCopyManager manager = DLTKUIPlugin.getDefault()
					.getWorkingCopyManager();
			ISourceModule unit = manager.getWorkingCopy(editorInput);
			if (unit != null) {
				synchronized (unit) {
					unit.reconcile(false, null, null);
				}
			}

			IModelElement e = unit.getElementAt(offset);
			if (e instanceof IMember) {
				m = (IMember) e;
			}

			if (m != null) {
				return new StructuredSelection(m);
			}
		}
		return selection;
	}

	public void toggleLineBreakpoints(final IWorkbenchPart part,
			final ISelection selection) throws CoreException {

		Job job = new Job("Script Toggle Line Breakpoint") { //$NON-NLS-1$
			protected IStatus run(IProgressMonitor monitor) {
				final ITextEditor editor = getTextEditor(part);
				if (editor != null && selection instanceof ITextSelection) {
					if (monitor.isCanceled()) {
						return Status.CANCEL_STATUS;
					}

					try {
						report(null, part);

						int lineNumber = ((ITextSelection) selection)
								.getStartLine() + 1;

						final IBreakpoint breakpoint = BreakpointUtils
								.findLineBreakpoint(editor, lineNumber);

						if (breakpoint != null) {
							// if breakpoint already exists, delete it
							breakpoint.delete();
						} else {
							final IDocumentProvider documentProvider = editor
									.getDocumentProvider();
							if (documentProvider == null) {
								return Status.CANCEL_STATUS;
							}

							final IDocument document = documentProvider
									.getDocument(editor.getEditorInput());

							lineNumber = findBreakpointLine(document,
									lineNumber - 1, getValidator()) + 1;

							if (lineNumber != BREAKPOINT_LINE_NOT_FOUND) {
								// Check if already breakpoint set to the same
								// location
								if (BreakpointUtils.findLineBreakpoint(editor,
										lineNumber) == null) {
									BreakpointUtils.addLineBreakpoint(editor,
											lineNumber);
								} else {
									report(MessageFormat.format(Messages.ScriptToggleBreakpointAdapter_breakpointAlreadySetAtLine,
											new Object[] { lineNumber }), part);
								}
							} else {
								report(Messages.ScriptToggleBreakpointAdapter_invalidBreakpointPosition, part);
							}
						}
					} catch (CoreException e) {
						DLTKDebugUIPlugin.log(e);
					}
				}

				return Status.OK_STATUS;
			}

		};
		job.setSystem(true);
		job.schedule();
	}

	protected abstract String getDebugModelId();

	protected IScriptBreakpointLineValidator getValidator() {
		return ScriptBreakpointLineValidatorFactory.NON_EMPTY_VALIDATOR;
	}
}