/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceReference;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.ui.editor.EditorUtility;
import org.eclipse.dltk.ui.ModelElementLabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;


public class OpenActionUtil {
	
	private OpenActionUtil() {
		// no instance.
	}
		
	/**
	 * Opens the editor on the given element and subsequently selects it.
	 */
	public static void open(Object element) throws ModelException, PartInitException {
		open(element, true);
	}
	
	/**
	 * Opens the editor on the given element and subsequently selects it.
	 */
	public static void open(Object element, boolean activate) throws ModelException, PartInitException {
		IEditorPart part= EditorUtility.openInEditor(element, activate);
		if (element instanceof IModelElement)
			EditorUtility.revealInEditor(part, (IModelElement)element);
	}
	
	/**
	 * Filters out source references from the given code resolve results.
	 * A utility method that can be called by subclasses. 
	 */
	public static List filterResolveResults(IModelElement[] codeResolveResults) {
		int nResults= codeResolveResults.length;
		List refs= new ArrayList(nResults);
		for (int i= 0; i < nResults; i++) {
			if (codeResolveResults[i] instanceof ISourceReference)
				refs.add(codeResolveResults[i]);
		}
		return refs;
	}
						
	/**
	 * Shows a dialog for resolving an ambiguous script element.
	 * Utility method that can be called by subclasses.
	 */
	public static IModelElement selectModelElement(IModelElement[] elements, Shell shell, String title, String message) {
		
		int nResults= elements.length;
		
		if (nResults == 0)
			return null;
		
		if (nResults == 1)
			return elements[0];
		
		int flags= ModelElementLabelProvider.SHOW_DEFAULT
//						| ModelElementLabelProvider.SHOW_QUALIFIED
//						| ModelElementLabelProvider.SHOW_ROOT
						| ModelElementLabelProvider.SHOW_FILE_QUALIFIED
						;
						
		ElementListSelectionDialog dialog= new ElementListSelectionDialog(shell, new ModelElementLabelProvider(flags));
		dialog.setTitle(title);
		dialog.setMessage(message);
		dialog.setElements(elements);
		
		if (dialog.open() == Window.OK) {
			Object[] selection= dialog.getResult();
			if (selection != null && selection.length > 0) {
				nResults= selection.length;
				for (int i= 0; i < nResults; i++) {
					Object current= selection[i];
					if (current instanceof IModelElement)
						return (IModelElement) current;
				}
			}
		}		
		return null;
	}	
}
