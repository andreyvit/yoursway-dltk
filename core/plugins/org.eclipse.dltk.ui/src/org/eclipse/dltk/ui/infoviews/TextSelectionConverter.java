/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.infoviews;

import org.eclipse.dltk.core.DLTKModelUtil;
import org.eclipse.dltk.core.ICodeAssist;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.IWorkingCopyManager;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.IEditorInput;


/**
 * Helper class to convert text selections to Script elements.
 *
	 *
 */
class TextSelectionConverter {

	/** Empty result. */
	private static final IModelElement[] EMPTY_RESULT= new IModelElement[0];

	/** Prevent instance creation. */
	private TextSelectionConverter() {
	}

	/**
	 * Finds and returns the Script elements for the given editor selection.
	 *
	 * @param editor the Script editor
	 * @param selection the text selection
	 * @return	the Script elements for the given editor selection
	 * @throws ModelException
	 */
	public static IModelElement[] codeResolve(ScriptEditor editor, ITextSelection selection) throws ModelException {
		return codeResolve(getInput(editor), selection);
	}

	/**
	 * Finds and returns the Script element that contains the
	 * text selection in the given editor.
	 *
	 * @param editor the Script editor
	 * @param selection the text selection
	 * @return	the Script elements for the given editor selection
	 * @throws ModelException
	 */
	public static IModelElement getElementAtOffset(ScriptEditor editor, ITextSelection selection) throws ModelException {
		return getElementAtOffset(getInput(editor), selection);
	}

	//-------------------- Helper methods --------------------

	private static IModelElement getInput(ScriptEditor editor) {
		if (editor == null)
			return null;
		IEditorInput input= editor.getEditorInput();		
		IWorkingCopyManager manager= DLTKUIPlugin.getDefault().getWorkingCopyManager();
		return manager.getWorkingCopy(input);
	}

	private static IModelElement[] codeResolve(IModelElement input, ITextSelection selection) throws ModelException {
			if (input instanceof ICodeAssist) {
				if (input instanceof ISourceModule) {
					ISourceModule cunit= (ISourceModule)input;
					if (cunit.isWorkingCopy())
						DLTKModelUtil.reconcile(cunit);
				}
				IModelElement[] elements= ((ICodeAssist)input).codeSelect(selection.getOffset(), selection.getLength());
				if (elements != null && elements.length > 0)
					return elements;
			}
			return EMPTY_RESULT;
	}

	private static IModelElement getElementAtOffset(IModelElement input, ITextSelection selection) throws ModelException {
		if (input instanceof ISourceModule) {
			ISourceModule cunit= (ISourceModule)input;
			if (cunit.isWorkingCopy())
				DLTKModelUtil.reconcile(cunit);
			IModelElement ref= cunit.getElementAt(selection.getOffset());
			if (ref == null)
				return input;
			else
				return ref;
		} 
		return null;
	}
}
