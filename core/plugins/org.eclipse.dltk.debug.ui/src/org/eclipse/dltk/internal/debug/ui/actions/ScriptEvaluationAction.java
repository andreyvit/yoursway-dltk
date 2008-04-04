/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.eclipse.dltk.internal.debug.ui.actions;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.Iterator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.IDebugModelPresentation;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.dltk.debug.core.eval.IScriptEvaluationEngine;
import org.eclipse.dltk.debug.core.eval.IScriptEvaluationListener;
import org.eclipse.dltk.debug.core.eval.IScriptEvaluationResult;
import org.eclipse.dltk.debug.core.model.IScriptStackFrame;
import org.eclipse.dltk.debug.core.model.IScriptValue;
import org.eclipse.dltk.debug.core.model.IScriptVariable;
import org.eclipse.dltk.debug.ui.DLTKDebugUIPlugin;
import org.eclipse.dltk.internal.debug.ui.ScriptEvaluationContextManager;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.internal.ui.text.ScriptWordFinder;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.texteditor.ITextEditor;

public class ScriptEvaluationAction implements IWorkbenchWindowActionDelegate,
		IObjectActionDelegate, IPartListener, IScriptEvaluationListener,
		IEditorActionDelegate, IViewActionDelegate {

	private static class ObjectResolver {
		private IWorkbenchPart part;
		private IRegion region;

		protected static ISelection getSelection(IWorkbenchPart part) {
			if (part != null) {
				ISelectionProvider provider = part.getSite()
						.getSelectionProvider();
				if (provider != null) {
					return provider.getSelection();
				}
			}

			return null;
		}

		protected static boolean textHasContent(String text) {
			if (text != null) {
				int length = text.length();
				if (length > 0) {
					for (int i = 0; i < length; i++) {
						if (Character.isLetterOrDigit(text.charAt(i))) {
							return true;
						}
					}
				}
			}

			return false;
		}

		protected Object resolveTextSelection(ITextSelection selection) {
			String text = selection.getText();
			if (textHasContent(text)) {
				region = new Region(selection.getOffset(), selection
						.getLength());
				return text;
			} else if (part instanceof IEditorPart) {
				IEditorPart editor = (IEditorPart) part;
				if (editor instanceof ITextEditor) {
					ITextEditor textEditor = (ITextEditor) editor;
					IDocument doc = textEditor.getDocumentProvider()
							.getDocument(editor.getEditorInput());
					region = ScriptWordFinder.findWord(doc, selection
							.getOffset());
					if (region != null) {
						try {
							return doc.get(region.getOffset(), region
									.getLength());
						} catch (BadLocationException e) {
						}
					}
				}
			}

			return null;
		}

		protected Object resolveStructuredSelection(
				IStructuredSelection selection) {
			if (!selection.isEmpty()) {
				if (part.getSite().getId().equals(
						IDebugUIConstants.ID_DEBUG_VIEW)) {
					IEditorPart editor = part.getSite().getPage()
							.getActiveEditor();

					ISelection newSelection = getSelection(editor);
					if (newSelection instanceof ITextSelection) {
						return resolveTextSelection((ITextSelection) newSelection);
					}
				} else {
					Iterator elements = selection.iterator();
					while (elements.hasNext()) {
						Object element = elements.next();
						if (!(element instanceof IScriptVariable)) {
							return null;
						}
					}

					return selection;
				}
			}

			return null;
		}

		public ObjectResolver(IWorkbenchPart part) {
			this.part = part;
		}

		public Object resolveSelectedObject() {
			ISelection selection = getSelection(part);

			if (selection instanceof ITextSelection) {
				return resolveTextSelection((ITextSelection) selection);
			} else if (selection instanceof IStructuredSelection) {
				return resolveStructuredSelection((IStructuredSelection) selection);
			}

			return null;
		}

		public IRegion getRegion() {
			return region;
		}
	}

	private IWorkbenchWindow window;
	private IWorkbenchPart part;
	private IAction action;
	private Object selectedObject;
	private boolean evaluation;

	protected static IDebugModelPresentation getDebugModelPresentation(
			String identifier) {
		return DebugUITools.newDebugModelPresentation(identifier);
	}

	private void setWindow(IWorkbenchWindow window) {
		this.window = window;
	}

	protected IWorkbenchWindow getWindow() {
		return this.window;
	}

	private void setPart(IWorkbenchPart part) {
		this.part = part;
	}

	protected IWorkbenchPart getPart() {
		return this.editor != null ? this.editor : this.part;
	}

	private void setAction(IAction action) {
		this.action = action;
	}

	protected IAction getAction() {
		return action;
	}

	private void setSelectedObject(Object object) {
		this.selectedObject = object;
	}

	protected Object getSelectedObject() {
		return this.selectedObject;
	}

	private void setEvaluating(boolean evaluation) {
		this.evaluation = evaluation;
	}

	protected boolean isEvaluating() {
		return this.evaluation;
	}

	protected void evaluationCleanup() {
		setEvaluating(false);
		// setTargetPart(fNewTargetPart);
	}

	private ScriptEditor editor;

	private void setEditor(ScriptEditor editor) {
		this.editor = editor;
	}

	protected ScriptEditor getEditor() {
		return this.editor;
	}

	// IWorkbenchWindowActionDelegate
	public void init(IWorkbenchWindow window) {
		setWindow(window);

		IWorkbenchPage page = window.getActivePage();
		if (page != null) {
			setPart(page.getActivePart());
		}

		window.getPartService().addPartListener(this);
		update();
	}

	public void dispose() {
		// disposeDebugModelPresentation();

		IWorkbenchWindow window = getWindow();
		if (window != null) {
			window.getPartService().removePartListener(this);
		}
	}

	// IObjectActionDelegate
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		setAction(action);
		setPart(targetPart);
		update();
	}

	// IActionDelegate
	public void run(IAction action) {
		update();
		run();
	}

	public void selectionChanged(IAction action, ISelection selection) {
		setAction(action);
	}

	// IPartListener
	public void partActivated(IWorkbenchPart part) {
		setPart(part);
	}

	public void partBroughtToTop(IWorkbenchPart part) {

	}

	public void partClosed(IWorkbenchPart part) {
		if (part == getPart()) {
			setPart(null);
		}
	}

	public void partDeactivated(IWorkbenchPart part) {

	}

	public void partOpened(IWorkbenchPart part) {

	}

	// Other stuff
	protected void update() {
		IAction action = getAction();
		if (action != null) {
			ObjectResolver or = new ObjectResolver(getPart());
			setSelectedObject(or.resolveSelectedObject());
		}
	}

	protected void showExpressionView() {
		if (getPart().getSite().getId().equals(
				IDebugUIConstants.ID_EXPRESSION_VIEW)) {
			return;
		}

		IWorkbenchPage page = DLTKDebugUIPlugin.getActivePage();
		if (page != null) {
			IViewPart part = page
					.findView(IDebugUIConstants.ID_EXPRESSION_VIEW);
			if (part == null) {
				try {
					page.showView(IDebugUIConstants.ID_EXPRESSION_VIEW);
				} catch (PartInitException e) {
					// reportError(e.getStatus().getMessage());
				}
			} else {
				page.bringToTop(part);
			}
		}
	}

	public static StyledText getStyledText(IWorkbenchPart part) {
		ITextViewer viewer = (ITextViewer) part.getAdapter(ITextViewer.class);
		StyledText textWidget = null;
		if (viewer == null) {
			Control control = (Control) part.getAdapter(Control.class);
			if (control instanceof StyledText) {
				textWidget = (StyledText) control;
			}
		} else {
			textWidget = viewer.getTextWidget();
		}
		return textWidget;
	}

	public static Point getPopupAnchor(StyledText textWidget) {
		if (textWidget != null) {
			Point docRange = textWidget.getSelectionRange();
			int midOffset = docRange.x + (docRange.y / 2);
			Point point = textWidget.getLocationAtOffset(midOffset);
			point = textWidget.toDisplay(point);

			GC gc = new GC(textWidget);
			gc.setFont(textWidget.getFont());
			int height = gc.getFontMetrics().getHeight();
			gc.dispose();
			point.y += height;
			return point;
		}

		return null;
	}

	protected Shell getShell() {
		if (getPart() != null) {
			return getPart().getSite().getShell();
		}

		return DLTKDebugUIPlugin.getActiveWorkbenchShell();
	}

	protected IScriptStackFrame getStackFrameContext() {
		IWorkbenchPart part = getPart();
		IScriptStackFrame frame = null;
		if (part == null) {
			frame = ScriptEvaluationContextManager
					.getEvaluationContext(getWindow());
		} else {
			frame = ScriptEvaluationContextManager.getEvaluationContext(part);
		}
		return frame;
	}

	protected void run() {
		final IScriptStackFrame stackFrame = getStackFrameContext();
		if (stackFrame == null) {
			reportError(Messages.ScriptEvaluationAction_cannotGetStackFrame);
			return;
		}

		// setNewTargetPart(getTargetPart());

		// Preparing runnable
		IRunnableWithProgress runnable = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException, InterruptedException {
				if (stackFrame.isSuspended()) {
					Object selection = getSelectedObject();
					if (!(selection instanceof String)) {
						return;
					}
					String expression = (String) selection;

					IScriptEvaluationEngine engine = stackFrame
							.getScriptThread().getEvaluationEngine();
					setEvaluating(true);
					engine.asyncEvaluate(expression, stackFrame,
							ScriptEvaluationAction.this);
				} else {
					throw new InvocationTargetException(null,
							Messages.ScriptEvaluationAction_threadIsNotSuspended);
				}
			}
		};

		// Run
		try {
			IWorkbench workbench = DLTKDebugUIPlugin.getDefault()
					.getWorkbench();
			workbench.getProgressService().busyCursorWhile(runnable);
		} catch (InvocationTargetException e) {
			evaluationCleanup();
			String message = e.getMessage();
			if (message == null) {
				message = e.getClass().getName();
				if (e.getCause() != null) {
					message = e.getCause().getClass().getName();
					if (e.getCause().getMessage() != null) {
						message = e.getCause().getMessage();
					}
				}
			}
			reportError(message);
		} catch (InterruptedException e) {
		}
	}

	// Error reporting
	public static String getExceptionMessage(Throwable exception) {
		if (exception instanceof CoreException) {
			CoreException ce = (CoreException) exception;
			Throwable throwable = ce.getStatus().getException();
			if (throwable instanceof CoreException) {
				// Traverse nested CoreExceptions
				return getExceptionMessage(throwable);
			}
			return ce.getStatus().getMessage();
		}
		String message = MessageFormat.format(Messages.ScriptEvaluationAction_anExceptionOccurred,
				new Object[] { exception.getClass() });
		if (exception.getMessage() != null) {
			message = MessageFormat.format(Messages.ScriptEvaluationAction_anExceptionOccurred2, new Object[] { message,
					exception.getMessage() });
		}
		return message;
	}

	protected static String getErrorMessage(IScriptEvaluationResult result) {
		String[] errors = result.getErrorMessages();
		if (errors.length == 0) {
			return getExceptionMessage(result.getException());
		}
		return getErrorMessage(errors);
	}

	protected static String getErrorMessage(String[] errors) {
		String message = ""; //$NON-NLS-1$
		for (int i = 0; i < errors.length; i++) {
			String msg = errors[i];
			if (i == 0) {
				message = msg;
			} else {
				message = MessageFormat.format(Messages.ScriptEvaluationAction_errorMessage, new Object[] {
						message, msg });
			}
		}
		return message;
	}

	protected void reportErrors(IScriptEvaluationResult result) {
		String message = getErrorMessage(result);
		reportError(message);
	}

	protected void reportError(String message) {
		Status status = new Status(IStatus.ERROR, DLTKDebugUIPlugin
				.getUniqueIdentifier(), IStatus.ERROR, message, null);
		ErrorDialog.openError(getShell(), Messages.ScriptEvaluationAction_errorEvaluating, null, status);
	}

	// IScriptEvaluationListener
	public void evaluationComplete(IScriptEvaluationResult result) {
		// if plug-in has shutdown, ignore - see bug# 8693
		if (DLTKDebugUIPlugin.getDefault() == null) {
			return;
		}

		final IScriptValue value = result.getValue();
		if (result.hasErrors() || value != null) {
			final Display display = DLTKDebugUIPlugin.getStandardDisplay();
			if (display.isDisposed()) {
				return;
			}

			// Each action should implement this method for own purposes
			displayResult(result);
		}

	}

	protected void displayResult(IScriptEvaluationResult result) {
		// Nothing by default
	}

	// IEditorActionDelegate
	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		setEditor((ScriptEditor) targetEditor);
	}

	// IViewActionDelegate
	public void init(IViewPart view) {
		setPart(view);
	}
}
