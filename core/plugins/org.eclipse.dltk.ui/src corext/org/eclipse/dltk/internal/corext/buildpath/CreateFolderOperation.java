/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.corext.buildpath;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.ui.wizards.NewWizardMessages;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.DialogPackageExplorerActionGroup;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.GenerateBuildPathActionGroup.CreateLocalSourceFolderAction;
import org.eclipse.jface.viewers.StructuredSelection;


public class CreateFolderOperation extends BuildpathModifierOperation {
	
	private final IBuildpathModifierListener fListener;
	private final IBuildpathInformationProvider fCPInformationProvider;

    /**
     * Creates a new <code>AddFolderOperation</code>.
     * 
     * @param listener a <code>IBuildpathModifierListener</code> that is notified about 
     * changes on buildpath entries or <code>null</code> if no such notification is 
     * necessary.
     * @param informationProvider a provider to offer information to the action
     * 
     * @see IBuildpathInformationProvider
     * @see BuildpathModifier
     */
	public CreateFolderOperation(IBuildpathModifierListener listener, IBuildpathInformationProvider informationProvider) {
		super(listener, informationProvider, NewWizardMessages.NewSourceContainerWorkbookPage_ToolBar_AddLibCP_tooltip, IBuildpathInformationProvider.CREATE_FOLDER);
		fListener= listener;
		fCPInformationProvider= informationProvider;
	}

	/**
	 * {@inheritDoc}
	 */
	public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		CreateLocalSourceFolderAction action= new CreateLocalSourceFolderAction();
		action.selectionChanged(new StructuredSelection(fCPInformationProvider.getDLTKProject()));
		action.run();
		IProjectFragment createdElement= (IProjectFragment)action.getCreatedElement();
		if (createdElement == null) {
			//Wizard was cancled.
			return;
		}
		try {
			IResource correspondingResource= createdElement.getCorrespondingResource();
			List result= new ArrayList();
			result.add(correspondingResource);
			if (fListener != null) {
				List entries= action.getBPListElements();
				fListener.buildpathEntryChanged(entries);
			}
	        fCPInformationProvider.handleResult(result, null, IBuildpathInformationProvider.CREATE_FOLDER);   
		} catch (ModelException e) {
			if (monitor == null) {
				fCPInformationProvider.handleResult(Collections.EMPTY_LIST, e, IBuildpathInformationProvider.CREATE_FOLDER);
			} else {
				throw new InvocationTargetException(e);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isValid(List elements, int[] types) throws ModelException {
		return types.length == 1 && types[0] == DialogPackageExplorerActionGroup.SCRIPT_PROJECT;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getDescription(int type) {
		return NewWizardMessages.PackageExplorerActionGroup_FormText_createNewSourceFolder; 
	}
}
