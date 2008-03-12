/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.actions.refactoring;

import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.internal.ui.actions.ActionUtil;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.internal.ui.refactoring.RefactoringMessages;
import org.eclipse.dltk.internal.ui.refactoring.actions.RenameModelElementAction;
import org.eclipse.dltk.internal.ui.refactoring.actions.RenameResourceAction;
import org.eclipse.dltk.ui.actions.SelectionDispatchAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IWorkbenchSite;


/**
 * Renames a Script element or workbench resource.
 * <p>
 * Action is applicable to selections containing elements of type
 * <code>IScriptElement</code> or <code>IResource</code>.
 * 
 * <p>
 * This class may be instantiated; it is not intended to be subclassed.
 * </p>
 * 
	 *
 */
public class RenameAction extends SelectionDispatchAction {

	private RenameModelElementAction fRenameScriptElement;
	private RenameResourceAction fRenameResource;

	private ScriptEditor fEditor;
	
	/**
	 * Creates a new <code>RenameAction</code>. The action requires
	 * that the selection provided by the site's selection provider is of type <code>
	 * org.eclipse.jface.viewers.IStructuredSelection</code>.
	 * 
	 * @param site the site providing context information for this action
	 */
	public RenameAction(IWorkbenchSite site) {
		super(site);
		setText(RefactoringMessages.RenameAction_text); 
		fRenameScriptElement= new RenameModelElementAction(site);
		fRenameScriptElement.setText(getText());
		fRenameResource= new RenameResourceAction(site);
		fRenameResource.setText(getText());
		if (DLTKCore.DEBUG) {
			System.err.println("Add help support here..."); //$NON-NLS-1$
		}
		
	//	PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IScriptHelpContextIds.RENAME_ACTION);
	}
	
	/**
	 * Note: This constructor is for internal use only. Clients should not call this constructor.
	 * @param editor the compilation unit editor
	 */
	public RenameAction(ScriptEditor editor) {
		this(editor.getEditorSite());
		fEditor= editor;
		fRenameScriptElement= new RenameModelElementAction(editor);
	}
	
	/*
	 * @see ISelectionChangedListener#selectionChanged(SelectionChangedEvent)
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		fRenameScriptElement.selectionChanged(event);
		if (fRenameResource != null)
			fRenameResource.selectionChanged(event);
		setEnabled(computeEnabledState());		
	}

	/*
	 * @see SelectionDispatchAction#update(ISelection)
	 */
	public void update(ISelection selection) {
		fRenameScriptElement.update(selection);
		
		if (fRenameResource != null)
			fRenameResource.update(selection);
	
		setEnabled(computeEnabledState());		
	}
	
	private boolean computeEnabledState(){
		if (fRenameResource != null) {
			return fRenameScriptElement.isEnabled() || fRenameResource.isEnabled();
		} else {
			return fRenameScriptElement.isEnabled();
		}
	}
	
	public void run(IStructuredSelection selection) {
		if (fRenameScriptElement.isEnabled())
			fRenameScriptElement.run(selection);
		if (fRenameResource != null && fRenameResource.isEnabled())
			fRenameResource.run(selection);
	}

	public void run(ITextSelection selection) {
		if (!ActionUtil.isProcessable(getShell(), fEditor))
			return;
		if (fRenameScriptElement.canRun())
			fRenameScriptElement.run(selection);
		else
			MessageDialog.openInformation(getShell(), RefactoringMessages.RenameAction_rename, RefactoringMessages.RenameAction_unavailable);  
	}
}
