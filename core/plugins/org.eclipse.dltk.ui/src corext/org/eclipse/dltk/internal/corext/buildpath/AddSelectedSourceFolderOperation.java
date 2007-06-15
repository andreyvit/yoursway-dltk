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
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.corext.util.Messages;
import org.eclipse.dltk.internal.ui.wizards.NewWizardMessages;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.DialogPackageExplorerActionGroup;



/**
 * Operation to add objects (of type <code>IFolder</code> or <code>
 * IModelElement</code> as source folder to the buildpath.
 * 
 */
public class AddSelectedSourceFolderOperation extends BuildpathModifierOperation {
    
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
    public AddSelectedSourceFolderOperation(IBuildpathModifierListener listener, IBuildpathInformationProvider informationProvider) {
        super(listener, informationProvider, NewWizardMessages.NewSourceContainerWorkbookPage_ToolBar_AddSelSFToCP_tooltip, IBuildpathInformationProvider.ADD_SEL_SF_TO_BP); 
    }
    
    /**
     * Method which runs the actions with a progress monitor.<br>
     * 
     * This operation requires the following query from the provider:
     * <li>IOutputFolderQuery</li>
     * 
     * @param monitor a progress monitor, can be <code>null</code>
     */
    public void run(IProgressMonitor monitor) throws InvocationTargetException {
        List result= null;
        fException= null;
        try {
            List elements= getSelectedElements();
            IScriptProject project= fInformationProvider.getDLTKProject();            
            result= addToBuildpath(elements, project, monitor);
        } catch (CoreException e) {
            fException= e;
            result= null;
        }
       super.handleResult(result, monitor);
    }
    
    /**
     * Find out whether this operation can be executed on 
     * the provided list of elements.
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
        if (elements.size() == 0)
            return false;
        for (int i= 0; i < elements.size(); i++) {
        	Object object= elements.get(i);
            switch (types[i]) {
                case DialogPackageExplorerActionGroup.SCRIPT_PROJECT: if (isSourceFolder((IScriptProject) object)) return false; break;
                case DialogPackageExplorerActionGroup.PACKAGE_FRAGMENT: break; // is ok
                case DialogPackageExplorerActionGroup.INCLUDED_FOLDER: break; // is ok
                case DialogPackageExplorerActionGroup.FOLDER: break; // is ok
                case DialogPackageExplorerActionGroup.EXCLUDED_FOLDER: break; // is ok
                default: return false; // all others are not ok
            }
            
        }
        return true;
    }
        
    /**
     * Get a description for this operation. The description depends on 
     * the provided type parameter, which must be a constant of 
     * <code>DialogPackageExplorerActionGroup</code>. If the type is 
     * <code>DialogPackageExplorerActionGroup.MULTI</code>, then the 
     * description will be very general to describe the situation of 
     * all the different selected objects as good as possible.
     * 
     * @param type the type of the selected object, must be a constant of 
     * <code>DialogPackageExplorerActionGroup</code>.
     * @return a string describing the operation
     */
    public String getDescription(int type) {
    	Object obj= getSelectedElements().get(0);
    	if (obj instanceof IModelElement) {
    		String name= escapeSpecialChars(((IModelElement) obj).getElementName());
            if (type == DialogPackageExplorerActionGroup.SCRIPT_PROJECT)
                return Messages.format(NewWizardMessages.PackageExplorerActionGroup_FormText_ProjectToBuildpath, name); 
            if (type == DialogPackageExplorerActionGroup.PACKAGE_FRAGMENT)
                return Messages.format(NewWizardMessages.PackageExplorerActionGroup_FormText_PackageToBuildpath, name); 
            if (type == DialogPackageExplorerActionGroup.MODIFIED_FRAGMENT_ROOT)
                return Messages.format(NewWizardMessages.PackageExplorerActionGroup_FormText_PackageToBuildpath, name); 
    	} else if (obj instanceof IResource) {
    		String name= escapeSpecialChars(((IResource) obj).getName());
	        if (type == DialogPackageExplorerActionGroup.FOLDER)
	            return Messages.format(NewWizardMessages.PackageExplorerActionGroup_FormText_FolderToBuildpath, name); 
	        if (type == DialogPackageExplorerActionGroup.EXCLUDED_FOLDER)
	            return Messages.format(NewWizardMessages.PackageExplorerActionGroup_FormText_FolderToBuildpath, name);
    	}
         return NewWizardMessages.PackageExplorerActionGroup_FormText_Default_toBuildpath; 
    }
}
