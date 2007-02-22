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

package org.eclipse.dltk.internal.corext.buildpath;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.ui.wizards.NewWizardMessages;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.DialogPackageExplorerActionGroup;



/**
 * Operation to include objects of type  
 * <code>IResource</code> or <code>IModelElement</code>.
 * 
 */
public class IncludeOperation extends BuildpathModifierOperation {
    
    /**
     * Constructor
     * 
     * @param listener a <code>IBuildpathModifierListener</code> that is notified about 
     * changes on buildpath entries or <code>null</code> if no such notification is 
     * necessary.
     * @param informationProvider a provider to offer information to the operation
     * 
     * @see IBuildpathInformationProvider
     * @see BuildpathModifier
     */
    public IncludeOperation(IBuildpathModifierListener listener, IBuildpathInformationProvider informationProvider) {
        super(listener, informationProvider, NewWizardMessages.NewSourceContainerWorkbookPage_ToolBar_Include_tooltip, IBuildpathInformationProvider.INCLUDE); 
    }
    
    /**
     * Method which runs the actions with a progress monitor.<br>
     * 
     * This operation does not require any queries from the provider.
     * 
     * @param monitor a progress monitor, can be <code>null</code>
     */
    public void run(IProgressMonitor monitor) throws InvocationTargetException {
        List result= null;
        fException= null;
        try {
            List elements= getSelectedElements();
            IDLTKProject project= fInformationProvider.getDLTKProject();
            result= include(elements, project, monitor);
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
	    IDLTKProject project= fInformationProvider.getDLTKProject();
	    for(int i= 0; i < elements.size(); i++) {
	        Object element= elements.get(i);
	        switch (types[i]) {
	            case DialogPackageExplorerActionGroup.PACKAGE_FRAGMENT: break; // ok
	            case DialogPackageExplorerActionGroup.FOLDER: if (!isValidFolder((IResource)element, project)) return false; break;
	            case DialogPackageExplorerActionGroup.EXCLUDED_FOLDER: break; // ok
	            case DialogPackageExplorerActionGroup.EXCLUDED_FILE: break; // ok
	            case DialogPackageExplorerActionGroup.SOURCE_MODULE: break; // ok
	            default: return false; // all others are not ok
	        }
	        
	    }
	    return true;
	}
    
    /**
     * Find out whether the folder can be included or not.
     * 
     * @param resource the resource to be checked
     * @param project the script project
     * @return <code>true</code> if the folder can be included, <code>
     * false</code> otherwise
     * @throws ModelException
     */
	private boolean isValidFolder(IResource resource, IDLTKProject project) throws ModelException {
		if (project.isOnBuildpath(project) && resource.getProjectRelativePath().segmentCount() == 1) {
			IProjectFragment root1= BuildpathModifier.getFragmentRoot(resource, project, null);
			IProjectFragment root2= BuildpathModifier.getFragmentRoot(project.getResource(), project, null);
			if (root1 != null && root1.equals(root2)) {
				return true;
			}
		}
		return false;
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
        if (type == DialogPackageExplorerActionGroup.PACKAGE_FRAGMENT)
            return NewWizardMessages.PackageExplorerActionGroup_FormText_Include;
        if (type == DialogPackageExplorerActionGroup.SOURCE_MODULE)
            return NewWizardMessages.PackageExplorerActionGroup_FormText_Include;
        if (type == DialogPackageExplorerActionGroup.FOLDER)
            return NewWizardMessages.PackageExplorerActionGroup_FormText_Include;
        if (type == DialogPackageExplorerActionGroup.EXCLUDED_FOLDER)
            return NewWizardMessages.PackageExplorerActionGroup_FormText_Include;
        if (type == DialogPackageExplorerActionGroup.EXCLUDED_FILE)
            return NewWizardMessages.PackageExplorerActionGroup_FormText_Include;
        return NewWizardMessages.PackageExplorerActionGroup_FormText_Default_Include; 
    }
}
