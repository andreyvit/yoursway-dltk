/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.editor.selectionaction;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceRange;
import org.eclipse.dltk.core.ISourceReference;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.core.SourceRange;
import org.eclipse.dltk.internal.ui.editor.EditorUtility;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.ITextSelection;

public abstract class StructureSelectionAction extends Action {

	public static final String NEXT= "SelectNextElement"; //$NON-NLS-1$
	public static final String PREVIOUS= "SelectPreviousElement"; //$NON-NLS-1$
	public static final String ENCLOSING= "SelectEnclosingElement"; //$NON-NLS-1$
	public static final String HISTORY= "RestoreLastSelection"; //$NON-NLS-1$

	private ScriptEditor fEditor;
	private SelectionHistory fSelectionHistory;

	protected StructureSelectionAction(String text, ScriptEditor editor, SelectionHistory history) {
		super(text);
		Assert.isNotNull(editor);
		Assert.isNotNull(history);
		fEditor= editor;
		fSelectionHistory= history;
	}

	/*
	 * This constructor is for testing purpose only.
	 */
	protected StructureSelectionAction() {
		super(""); //$NON-NLS-1$
	}

	/*
	 * Method declared in IAction.
	 */
	public final  void run() {
		IModelElement inputElement= EditorUtility.getEditorInputModelElement(fEditor, false);
		if (!(inputElement instanceof ISourceReference && inputElement.exists()))
			return;

		ISourceReference source= (ISourceReference)inputElement;
		ISourceRange sourceRange;
		try {
			sourceRange= source.getSourceRange();
			if (sourceRange == null || sourceRange.getLength() == 0) {
				MessageDialog.openInformation(fEditor.getEditorSite().getShell(),
					SelectionActionMessages.StructureSelect_error_title,
					SelectionActionMessages.StructureSelect_error_message);
				return;
			}
		} catch (ModelException e) {
		}
		ITextSelection selection= getTextSelection();
		ISourceRange newRange= getNewSelectionRange(createSourceRange(selection), source);
		// Check if new selection differs from current selection
		if (selection.getOffset() == newRange.getOffset() && selection.getLength() == newRange.getLength())
			return;
		fSelectionHistory.remember(new SourceRange(selection.getOffset(), selection.getLength()));
		try {
			fSelectionHistory.ignoreSelectionChanges();
			fEditor.selectAndReveal(newRange.getOffset(), newRange.getLength());
		} finally {
			fSelectionHistory.listenToSelectionChanges();
		}
	}

	public final ISourceRange getNewSelectionRange(ISourceRange oldSourceRange, ISourceReference sr) {
		if (DLTKCore.DEBUG) {
			System.out.println("TODO: Add correction here..."); //$NON-NLS-1$
		}
		return new SourceRange(oldSourceRange.getOffset(), oldSourceRange.getLength());	 	
	}

	/**
	 * Subclasses determine the actual new selection.
	 */
	abstract ISourceRange internalGetNewSelectionRange(ISourceRange oldSourceRange, ISourceReference sr) throws ModelException;

	protected final ITextSelection getTextSelection() {
		return (ITextSelection)fEditor.getSelectionProvider().getSelection();
	}
	//-- private helper methods

	private static ISourceRange createSourceRange(ITextSelection ts){
		return new SourceRange(ts.getOffset(), ts.getLength());
	}
	//-- helper methods for this class and subclasses

	static ISourceRange createSourceRange(int offset, int end){
		int length= end - offset + 1;
		if (length == 0) //to allow 0-length selection
			length= 1;
		return new SourceRange(Math.max(0, offset), length);
	}

	static int findIndex(Object[] array, Object o){
		for (int i= 0; i < array.length; i++) {
			Object object= array[i];
			if (object == o)
				return i;
		}
		return -1;
	}

}
