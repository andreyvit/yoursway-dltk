/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.ScriptModelUtil;
import org.eclipse.dltk.core.ICodeAssist;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ISourceRange;
import org.eclipse.dltk.core.ISourceReference;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.ui.editor.EditorUtility;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.ui.ModelElementLabelProvider;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;


public class SelectionConverter {

	private static final IModelElement[] EMPTY_RESULT= new IModelElement[0];
	
	private SelectionConverter() {
		// no instance
	}

	/**
	 * Converts the selection provided by the given part into a structured selection.
	 * The following conversion rules are used:
	 * <ul>
	 *	<li><code>part instanceof ScriptEditor</code>: returns a structured selection
	 * 	using code resolve to convert the editor's text selection.</li>
	 * <li><code>part instanceof IWorkbenchPart</code>: returns the part's selection
	 * 	if it is a structured selection.</li>
	 * <li><code>default</code>: returns an empty structured selection.</li>
	 * </ul>
	 */
	public static IStructuredSelection getStructuredSelection(IWorkbenchPart part) throws ModelException {
		if (part instanceof ScriptEditor)
			return new StructuredSelection(codeResolve((ScriptEditor)part));
		ISelectionProvider provider= part.getSite().getSelectionProvider();
		if (provider != null) {
			ISelection selection= provider.getSelection();
			if (selection instanceof IStructuredSelection)
				return (IStructuredSelection)selection;
		}
		return StructuredSelection.EMPTY;
	}

	
	/**
	 * Converts the given structured selection into an array of Editor elements.
	 * An empty array is returned if one of the elements stored in the structured
	 * selection is not of type <code>IModelElement</code>
	 */
	public static IModelElement[] getElements(IStructuredSelection selection) {
		if (!selection.isEmpty()) {
			IModelElement[] result= new IModelElement[selection.size()];
			int i= 0;
			for (Iterator iter= selection.iterator(); iter.hasNext(); i++) {
				Object element= iter.next();
				if (!(element instanceof IModelElement))
					return EMPTY_RESULT;
				result[i]= (IModelElement)element;
			}
			return result;
		}
		return EMPTY_RESULT;
	}

	public static boolean canOperateOn(ScriptEditor editor) {
		if (editor == null)
			return false;
		return getInput(editor) != null;
		
	}
		
	public static IModelElement[] codeResolveOrInputForked(ScriptEditor editor) throws InvocationTargetException, InterruptedException {
		IModelElement input= getInput(editor);
		ITextSelection selection= (ITextSelection)editor.getSelectionProvider().getSelection();
		IModelElement[] result= performForkedCodeResolve(input, selection);
		if (result.length == 0) {
			result= new IModelElement[] {input};
		}
		return result;
	}
				
	public static IModelElement[] codeResolve(ScriptEditor editor) throws ModelException {
		return codeResolve(editor, true);
	}
		
	/**
	 * @param primaryOnly if <code>true</code> only primary working copies will be returned
	 *
	 */
	public static IModelElement[] codeResolve(ScriptEditor editor, boolean primaryOnly) throws ModelException {
		return codeResolve(getInput(editor, primaryOnly), (ITextSelection)editor.getSelectionProvider().getSelection());
	}
	
	/**
	 * Perform a code resolve in a separate thread.
	 * @param primaryOnly if <code>true</code> only primary working copies will be returned
	 * @throws InterruptedException 
	 * @throws InvocationTargetException 
	 *
	 */
	public static IModelElement[] codeResolveForked(ScriptEditor editor, boolean primaryOnly) throws InvocationTargetException, InterruptedException {
		return performForkedCodeResolve(getInput(editor, primaryOnly), (ITextSelection)editor.getSelectionProvider().getSelection());
	}
			
	public static IModelElement getElementAtOffset(ScriptEditor editor) throws ModelException {
		return getElementAtOffset(editor, true);
	}
	
	/**
	 * @param primaryOnly if <code>true</code> only primary working copies will be returned
	 *
	 */
	private static IModelElement getElementAtOffset(ScriptEditor editor, boolean primaryOnly) throws ModelException {
		return getElementAtOffset(getInput(editor, primaryOnly), (ITextSelection)editor.getSelectionProvider().getSelection());
	}	
	
	public static IModelElement getInput(ScriptEditor editor) {
		return getInput(editor, true);
	}
	
	/**
	 * @param primaryOnly if <code>true</code> only primary working copies will be returned
	 *
	 */
	private static IModelElement getInput(ScriptEditor editor, boolean primaryOnly) {
		if (editor == null)
			return null;
		return EditorUtility.getEditorInputModelElement(editor, primaryOnly);
	}
	
	public static ISourceModule getInputAsSourceModule(ScriptEditor editor) {
		Object editorInput= SelectionConverter.getInput(editor);
		if (editorInput instanceof ISourceModule)
			return (ISourceModule)editorInput;
		return null;
	}
	
	private static IModelElement[] performForkedCodeResolve(final IModelElement input, final ITextSelection selection) throws InvocationTargetException, InterruptedException {
		final class CodeResolveRunnable implements IRunnableWithProgress {
			IModelElement[] result;
			public void run(IProgressMonitor monitor) throws InvocationTargetException {
				try {
					result= codeResolve(input, selection);
				} catch (ModelException e) {
					throw new InvocationTargetException(e);
				}
			}
		}
		CodeResolveRunnable runnable= new CodeResolveRunnable();
		PlatformUI.getWorkbench().getProgressService().busyCursorWhile(runnable);
		return runnable.result;
	}

	public static IModelElement[] codeResolve(IModelElement input, ITextSelection selection) throws ModelException {
		if (DLTKCore.DEBUG) {
			System.err.println("SelectionConverter: Add Code assist support"); //$NON-NLS-1$
		}
			if (input instanceof ICodeAssist) {
				if (input instanceof ISourceModule) {
					ScriptModelUtil.reconcile((ISourceModule) input);
				}
				IModelElement[] elements= ((ICodeAssist)input).codeSelect(selection.getOffset(), selection.getLength());
				if (elements != null && elements.length > 0)
					return elements;
			}
			return EMPTY_RESULT;
	}
	
	public static IModelElement getElementAtOffset(IModelElement input, ITextSelection selection) throws ModelException {
		if (input instanceof ISourceModule) {
			ISourceModule cunit= (ISourceModule) input;
			ScriptModelUtil.reconcile(cunit);
			IModelElement ref= cunit.getElementAt(selection.getOffset());
			if (ref == null)
				return input;
			else
				return ref;
		}
		return null;
	}
	
//	public static IModelElement[] resolveSelectedElements(IModelElement input, ITextSelection selection) throws ModelException {
//		IModelElement enclosing= resolveEnclosingElement(input, selection);
//		if (enclosing == null)
//			return EMPTY_RESULT;
//		if (!(enclosing instanceof ISourceReference))
//			return EMPTY_RESULT;
//		ISourceRange sr= ((ISourceReference)enclosing).getSourceRange();
//		if (selection.getOffset() == sr.getOffset() && selection.getLength() == sr.getLength())
//			return new IModelElement[] {enclosing};
//	}
	
	public static IModelElement resolveEnclosingElement(ScriptEditor editor, ITextSelection selection) throws ModelException {
		return resolveEnclosingElement(getInput(editor), selection);
	}
	
	public static IModelElement resolveEnclosingElement(IModelElement input, ITextSelection selection) throws ModelException {
		IModelElement atOffset= null;
		if (input instanceof ISourceModule) {
			ISourceModule cunit= (ISourceModule)input;
			ScriptModelUtil.reconcile(cunit);
			atOffset= cunit.getElementAt(selection.getOffset());
		} else {
			return null;
		}
		if (atOffset == null) {
			return input;
		} else {
			int selectionEnd= selection.getOffset() + selection.getLength();
			IModelElement result= atOffset;
			if (atOffset instanceof ISourceReference) {
				ISourceRange range= ((ISourceReference)atOffset).getSourceRange();
				while (range.getOffset() + range.getLength() < selectionEnd) {
					result= result.getParent();
					if (! (result instanceof ISourceReference)) {
						result= input;
						break;
					}
					range= ((ISourceReference)result).getSourceRange();
				}
			}
			return result;
		}
	}
	/**
	 * Shows a dialog for resolving an ambiguous java element.
	 * Utility method that can be called by subclasses.
	 */
	public static IModelElement selectModelElement(IModelElement[] elements, Shell shell, String title, String message) {
		int nResults= elements.length;
		if (nResults == 0)
			return null;
		if (nResults == 1)
			return elements[0];
		
		int flags= ModelElementLabelProvider.SHOW_DEFAULT | ModelElementLabelProvider.SHOW_QUALIFIED | ModelElementLabelProvider.SHOW_ROOT;
						
		ElementListSelectionDialog dialog= new ElementListSelectionDialog(shell, new ModelElementLabelProvider(flags));
		dialog.setTitle(title);
		dialog.setMessage(message);
		dialog.setElements(elements);
		
		if (dialog.open() == Window.OK) {
			return (IModelElement) dialog.getFirstResult();
		}		
		return null;
	}
	
}
