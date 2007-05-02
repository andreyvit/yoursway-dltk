/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.refactoring.actions;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKModelUtil;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.corext.refactoring.RefactoringAvailabilityTester;
import org.eclipse.dltk.internal.corext.refactoring.RefactoringExecutionStarter;
import org.eclipse.dltk.internal.ui.actions.ActionUtil;
import org.eclipse.dltk.internal.ui.actions.SelectionConverter;
import org.eclipse.dltk.internal.ui.editor.ModelTextSelection;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.internal.ui.refactoring.RefactoringMessages;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.actions.SelectionDispatchAction;
import org.eclipse.dltk.ui.util.ExceptionHandler;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchSite;


public class RenameModelElementAction extends SelectionDispatchAction {

	private ScriptEditor fEditor;
	
	public RenameModelElementAction(IWorkbenchSite site) {
		super(site);
	}
	
	public RenameModelElementAction(ScriptEditor editor) {
		this(editor.getEditorSite());
		fEditor= editor;
		setEnabled(SelectionConverter.canOperateOn(fEditor));
	}

	//---- Structured selection ------------------------------------------------

	public void selectionChanged(IStructuredSelection selection) {
		try {
			if (selection.size() == 1) {
				setEnabled(canEnable(selection));
				return;
			}
		} catch (ModelException e) {
			// http://bugs.eclipse.org/bugs/show_bug.cgi?id=19253
			if (DLTKModelUtil.isExceptionToBeLogged(e)){
				DLTKUIPlugin.log(e);
			}
		} catch (CoreException e) {
			DLTKUIPlugin.log(e);
		}
		setEnabled(false);
	}
	
	private static boolean canEnable(IStructuredSelection selection) throws CoreException {
		IModelElement element= getModelElement(selection);
		if (element == null)
			return false;
		return isRenameAvailable(element);
	} 

	private static IModelElement getModelElement(IStructuredSelection selection) {
		if (selection.size() != 1)
			return null;
		Object first= selection.getFirstElement();
		if (! (first instanceof IModelElement))
			return null;
		return (IModelElement)first;
	}
	
	public void run(IStructuredSelection selection) {
		IModelElement element= getModelElement(selection);
		if (element == null)
			return;
		try {
			run(element);	
		} catch (CoreException e){
			ExceptionHandler.handle(e, RefactoringMessages.RenameScriptElementAction_name, RefactoringMessages.RenameScriptElementAction_exception);  
		}	
	}
	
	//---- text selection ------------------------------------------------------------

	public void selectionChanged(ITextSelection selection) {
		if (selection instanceof ModelTextSelection) {
			try {
				IModelElement[] elements= ((ModelTextSelection)selection).resolveElementAtOffset();
				if (elements.length == 1) {
					setEnabled(isRenameAvailable(elements[0]));
				} else {
					setEnabled(false);
				}
			} catch (CoreException e) {
				setEnabled(false);
			}
		} else {
			setEnabled(true);
		}
	}

	public void run(ITextSelection selection) {
		try {
			IModelElement element= getScriptElement();
			if (element != null && isRenameAvailable(element)) {
				run(element);
				return;
			}
		} catch (CoreException e) {
			ExceptionHandler.handle(e, RefactoringMessages.RenameScriptElementAction_name, RefactoringMessages.RenameScriptElementAction_exception);
		}
		MessageDialog.openInformation(getShell(), RefactoringMessages.RenameScriptElementAction_name, RefactoringMessages.RenameScriptElementAction_not_available);
	}
	
	public boolean canRun() {
		try {
			IModelElement element= getScriptElement();
			if (element == null)
				return false;

			return isRenameAvailable(element);
		} catch (ModelException e) {
			if (DLTKModelUtil.isExceptionToBeLogged(e))
				DLTKUIPlugin.log(e);
		} catch (CoreException e) {
			DLTKUIPlugin.log(e);
		}
		return false;
	}
	
	private IModelElement getScriptElement() throws ModelException {
		IModelElement[] elements= SelectionConverter.codeResolve(fEditor); 
		if (elements == null || elements.length != 1)
			return null;
		return elements[0];
	}
	
	//---- helper methods -------------------------------------------------------------------

	private void run(IModelElement element) throws CoreException {
		// Work around for http://dev.eclipse.org/bugs/show_bug.cgi?id=19104		
		if (!ActionUtil.isProcessable(getShell(), element))
			return;
		//XXX workaround bug 31998
		if (ActionUtil.mustDisableScriptModelAction(getShell(), element))
			return;
		RefactoringExecutionStarter.startRenameRefactoring(element, getShell());
	}

	private static boolean isRenameAvailable(IModelElement element) throws CoreException {
		if (DLTKCore.DEBUG) {
			System.err.println("TODO: Add other kind of rename refactoring support...");
		}
		switch (element.getElementType()) {
			case IModelElement.SCRIPT_PROJECT:
				return RefactoringAvailabilityTester.isRenameAvailable((IDLTKProject) element);
			case IModelElement.PROJECT_FRAGMENT:
				return RefactoringAvailabilityTester.isRenameAvailable((IProjectFragment) element);
			case IModelElement.SCRIPT_FOLDER:
				return RefactoringAvailabilityTester.isRenameAvailable((IScriptFolder) element);
			case IModelElement.SOURCE_MODULE			:
				return RefactoringAvailabilityTester.isRenameAvailable((ISourceModule) element);
//			case IModelElement.TYPE:
//				return RefactoringAvailabilityTester.isRenameAvailable((IType) element);
//			case IModelElement.METHOD:
//				final IMethod method= (IMethod) element;
//				if (method.isConstructor())
//					return RefactoringAvailabilityTester.isRenameAvailable(method.getDeclaringType());
//				else
//					return RefactoringAvailabilityTester.isRenameAvailable(method);
//			case IModelElement.FIELD:
//				final IField field= (IField) element;
//				if (Flags.isEnum(field.getFlags()))
//				return RefactoringAvailabilityTester.isRenameEnumConstAvailable(field);
//				else
//					return RefactoringAvailabilityTester.isRenameFieldAvailable(field);
//			case IModelElement.TYPE_PARAMETER:
//				return RefactoringAvailabilityTester.isRenameAvailable((ITypeParameter) element);
//			case IModelElement.LOCAL_VARIABLE:
//				return RefactoringAvailabilityTester.isRenameAvailable((ILocalVariable) element);
		}
		return false;
	}
}
