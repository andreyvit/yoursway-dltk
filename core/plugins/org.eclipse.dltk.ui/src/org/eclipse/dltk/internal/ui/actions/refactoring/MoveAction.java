/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.actions.refactoring;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.corext.refactoring.reorg.ReorgMoveAction;
import org.eclipse.dltk.internal.ui.actions.ActionUtil;
import org.eclipse.dltk.internal.ui.actions.SelectionConverter;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.internal.ui.refactoring.RefactoringMessages;
import org.eclipse.dltk.ui.actions.SelectionDispatchAction;
import org.eclipse.dltk.ui.util.ExceptionHandler;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchSite;


/**
 * This action moves Script elements to a new location. The action prompts
 * the user for the new location.
 * <p>
 * The action is applicable to a homogeneous selection containing either
 * projects, package fragment roots, package fragments, compilation units,
 * or static methods.
 * 
 * <p>
 * This class may be instantiated; it is not intended to be subclassed.
 * </p>
 * 
	 *
 */
public class MoveAction extends SelectionDispatchAction{
//TODO: remove duplicate availability checks. Look at
//- f...Action.selectionChanged
//- f...Action.isEnabled
//- ...Refactoring.isAvailable
//- try...
//... and remove duplicated code for text/structured selections.
//We have to clean this up, once we have a long term solution to
//bug 35748 (no ScriptElements for local types). 
	
	private ScriptEditor fEditor;
//	private MoveInstanceMethodAction fMoveInstanceMethodAction;
//	private MoveStaticMembersAction fMoveStaticMembersAction;
	private ReorgMoveAction fReorgMoveAction;
	
	/**
	 * Creates a new <code>MoveAction</code>. The action requires
	 * that the selection provided by the site's selection provider is of type <code>
	 * org.eclipse.jface.viewers.IStructuredSelection</code>.
	 * 
	 * @param site the site providing context information for this action
	 */
	public MoveAction(IWorkbenchSite site) {
		super(site);
		setText(RefactoringMessages.MoveAction_text); 
//		fMoveStaticMembersAction= new MoveStaticMembersAction(site);
//		fMoveInstanceMethodAction= new MoveInstanceMethodAction(site);
		fReorgMoveAction= new ReorgMoveAction(site);
		//PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IScriptHelpContextIds.MOVE_ACTION);
		if (DLTKCore.DEBUG) {
			System.err.println("Add help support here...");
		}		
	}
	
	/**
	 * Note: This constructor is for internal use only. Clients should not call this constructor.
	 * @param editor the compilation unit editor
	 */
	public MoveAction(ScriptEditor editor) {
		super(editor.getEditorSite());
		fEditor= editor;
		setText(RefactoringMessages.MoveAction_text); 
//		fMoveStaticMembersAction= new MoveStaticMembersAction(editor);
//		fMoveInstanceMethodAction= new MoveInstanceMethodAction(editor);
		fReorgMoveAction= new ReorgMoveAction(editor.getEditorSite());
		//PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IScriptHelpContextIds.MOVE_ACTION);
		if (DLTKCore.DEBUG) {
			System.err.println("Add help support here...");
		}
		
	}	

	/*
	 * @see ISelectionChangedListener#selectionChanged(SelectionChangedEvent)
	 */
	public void selectionChanged(SelectionChangedEvent event) {
//		fMoveStaticMembersAction.selectionChanged(event);
//		fMoveInstanceMethodAction.selectionChanged(event);
		fReorgMoveAction.selectionChanged(event);
		setEnabled(computeEnableState());	
	}

	public void run(IStructuredSelection selection) {
//		try {
//			if (fMoveInstanceMethodAction.isEnabled() && tryMoveInstanceMethod(selection)) 
//				return;
//	
//			if (fMoveStaticMembersAction.isEnabled() && tryMoveStaticMembers(selection)) 
//				return;
	
			if (fReorgMoveAction.isEnabled())
				fReorgMoveAction.run();
		
//		} catch (ModelException e) {
//			ExceptionHandler.handle(e, RefactoringMessages.OpenRefactoringWizardAction_refactoring, RefactoringMessages.OpenRefactoringWizardAction_exception); 
//		}

	}
	
	public void run(ITextSelection selection) {
		try {
			if (!ActionUtil.isProcessable(getShell(), fEditor))
				return;
//			if (fMoveStaticMembersAction.isEnabled() && tryMoveStaticMembers(selection))
//				return;
//		
//			if (fMoveInstanceMethodAction.isEnabled() && tryMoveInstanceMethod(selection))
//				return;
	
			if (tryReorgMove(selection))
				return;
			
			MessageDialog.openInformation(getShell(), RefactoringMessages.MoveAction_Move, RefactoringMessages.MoveAction_select); 
		} catch (ModelException e) {
			ExceptionHandler.handle(e, RefactoringMessages.OpenRefactoringWizardAction_refactoring, RefactoringMessages.OpenRefactoringWizardAction_exception); 
		}
	}	

	private static IMember[] getSelectedMembers(IStructuredSelection selection){
		if (selection.isEmpty())
			return null;
		
		for  (Iterator iter= selection.iterator(); iter.hasNext(); ) {
			if (! (iter.next() instanceof IMember))
				return null;
		}
		return convertToMemberArray(selection.toArray());
	}

	private static IMember[] convertToMemberArray(Object[] obj) {
		if (obj == null)
			return null;
		Set memberSet= new HashSet();
		memberSet.addAll(Arrays.asList(obj));
		return (IMember[]) memberSet.toArray(new IMember[memberSet.size()]);
	}	

	private static IMethod getSingleSelectedMethod(IStructuredSelection selection) {
		if (selection.isEmpty() || selection.size() != 1) 
			return null;
		
		Object first= selection.getFirstElement();
		if (! (first instanceof IMethod))
			return null;
		return (IMethod) first;
	}
	

	private boolean tryReorgMove(ITextSelection selection) throws ModelException{
		IModelElement element= SelectionConverter.getElementAtOffset(fEditor);
		if (element == null)
			return false;
		StructuredSelection mockStructuredSelection= new StructuredSelection(element);
		fReorgMoveAction.selectionChanged(mockStructuredSelection);
		if (!fReorgMoveAction.isEnabled())
			return false;
			
		fReorgMoveAction.run(mockStructuredSelection);
		return true;			
	}


	/*
	 * @see SelectionDispatchAction#update(ISelection)
	 */
	public void update(ISelection selection) {
//		fMoveStaticMembersAction.update(selection);
//		fMoveInstanceMethodAction.update(selection);
		fReorgMoveAction.update(selection);
		setEnabled(computeEnableState());
	}
	
	private boolean computeEnableState(){
		return /*fMoveStaticMembersAction.isEnabled()
				|| fMoveInstanceMethodAction.isEnabled()
				||*/ fReorgMoveAction.isEnabled();
	}
}
