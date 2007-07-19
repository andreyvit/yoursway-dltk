package org.eclipse.dltk.ruby.internal.debug.ui;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.dltk.debug.ui.DLTKDebugUIPlugin;
import org.eclipse.dltk.debug.ui.breakpoints.BreakpointUtils;
import org.eclipse.dltk.internal.debug.ui.ScriptToggleBreakpointAdapter;
import org.eclipse.dltk.ruby.debug.RubyDebugConstants;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

public class RubyToggleBreakpointAdapter extends ScriptToggleBreakpointAdapter {

	protected int findValidLine(IDocument document, int startLine) {
		String line = null;
		int lineNumber = startLine;
		int numberOfLines = document.getNumberOfLines();

		if (lineNumber < 0 || lineNumber >= numberOfLines) {
			return -1;
		}

		while (lineNumber < numberOfLines) {
			try {
				IRegion region = document.getLineInformation(lineNumber);
				line = document.get(region.getOffset(), region.getLength());

				// Non empty line and not a comment
				final String trimmedLine = line.trim();
				
				if (trimmedLine.length() > 0 && (trimmedLine.indexOf('#') != 0) ) {
					return lineNumber;
				}
				lineNumber++;
			} catch (BadLocationException e) {
				DLTKDebugUIPlugin.log(e);
			}
		}

		return -1;
	}

	public void toggleLineBreakpoints(final IWorkbenchPart part,
			final ISelection selection) throws CoreException {

		Job job = new Job("Ruby Toggle Line Breakpoint") { //$NON-NLS-1$
			protected IStatus run(IProgressMonitor monitor) {
				final ITextEditor editor = getTextEditor(part);
				if (editor != null && selection instanceof ITextSelection) {
					if (monitor.isCanceled()) {
						return Status.CANCEL_STATUS;
					}

					try {
						report(null, part);

						int lineNumber = ((ITextSelection) selection)
								.getStartLine();

						final IResource resource = getPartResource(part);
						final IBreakpoint[] rubyBreakpoints = getBreakpoints(RubyDebugConstants.DEBUG_MODEL_ID);
						final IBreakpoint breakpoint = findLineBreakpoint(
								rubyBreakpoints, resource, lineNumber + 1);

						if (breakpoint != null) {
							breakpoint.delete();
						} else {
							final IDocumentProvider documentProvider = editor
									.getDocumentProvider();
							if (documentProvider == null) {
								return Status.CANCEL_STATUS;
							}

							final IDocument document = documentProvider
									.getDocument(editor.getEditorInput());

							lineNumber = findValidLine(document, lineNumber);

							if (lineNumber != -1) {
								// Check if already breakpoint set to the same
								// location
								if (findLineBreakpoint(rubyBreakpoints,
										resource, lineNumber + 1) == null) {
									BreakpointUtils.addLineBreakpoint(editor,
											lineNumber + 1);
								} else {
									report("Breakpoint already set at line "
											+ (lineNumber + 1), part);
								}
							} else {
								report("Invalid breakpoint position.", part);
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
