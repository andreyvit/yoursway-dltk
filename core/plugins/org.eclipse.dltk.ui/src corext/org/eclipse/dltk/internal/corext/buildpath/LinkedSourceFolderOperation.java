/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
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
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.GenerateBuildPathActionGroup.CreateLinkedSourceFolderAction;
import org.eclipse.jface.viewers.StructuredSelection;


/**
 * Operation create a link to a source folder.
 * 
 */
public class LinkedSourceFolderOperation extends BuildpathModifierOperation {

    private IBuildpathModifierListener fListener;
	private IBuildpathInformationProvider fCPInformationProvider;

	/**
     * Constructor
     * 
     * @param listener a <code>IBuildpathModifierListener</code> that is notified about 
     * changes on buildpath entries or <code>null</code> if no such notification is 
     * necessary.
     * @param informationProvider a provider to offer information to the action
     * 
     * @see IBuildpathInformationProvider
     * @see BuildpathModifier
     */
    public LinkedSourceFolderOperation(IBuildpathModifierListener listener, IBuildpathInformationProvider informationProvider) {
        super(listener, informationProvider, NewWizardMessages.NewSourceContainerWorkbookPage_ToolBar_Link_tooltip, IBuildpathInformationProvider.CREATE_LINK); 
		fListener= listener;
		fCPInformationProvider= informationProvider;
    }
    
    /**
     * Method which runs the actions with a progress monitor.<br>
     * 
     * This operation requires the following query from the provider:
     * <li>ILinkToQuery</li>
     * 
     * @param monitor a progress monitor, can be <code>null</code>
     */
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
    	CreateLinkedSourceFolderAction action= new CreateLinkedSourceFolderAction();
		action.selectionChanged(new StructuredSelection(fCPInformationProvider.getScriptProject()));
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
	        fCPInformationProvider.handleResult(result, null, IBuildpathInformationProvider.CREATE_LINK);   
		} catch (ModelException e) {
			if (monitor == null) {
				fCPInformationProvider.handleResult(Collections.EMPTY_LIST, e, IBuildpathInformationProvider.CREATE_LINK);
			} else {
				throw new InvocationTargetException(e);
			}
		}
    }

    /**
     * This particular operation is always valid.
     * 
     * @param elements a list of elements
     * @param types an array of types for each element, that is, 
     * the type at position 'i' belongs to the selected element 
     * at position 'i' 
     * 
     * @return <code>true</code> if the operation can be 
     * executed on the provided list of elements, <code>
     * false</code> otherwise.
     * @throws ModelException 
     */
    public boolean isValid(List elements, int[] types) throws ModelException {
        return types.length == 1 && types[0] == DialogPackageExplorerActionGroup.SCRIPT_PROJECT;
    }

    /**
     * Get a description for this operation.
     * 
     * @param type the type of the selected object, must be a constant of 
     * <code>DialogPackageExplorerActionGroup</code>.
     * @return a string describing the operation
     */
    public String getDescription(int type) {
        return NewWizardMessages.PackageExplorerActionGroup_FormText_createLinkedFolder; 
    }

}
