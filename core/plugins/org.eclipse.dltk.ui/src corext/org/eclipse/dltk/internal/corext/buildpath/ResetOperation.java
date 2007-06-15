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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.ui.wizards.NewWizardMessages;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.DialogPackageExplorerActionGroup;


/**
 * Operation to reset either the inclusion/exclusion filters on a source folder or 
 * the output folder of a source folder (depends on the selected element provided by 
 * the <code>IBuildpathInformationProvider</code>).
 * 
 */
public class ResetOperation extends BuildpathModifierOperation {
    
    /**
     * Constructor
     * 
     * @param listener a <code>IBuildpathModifierListener</code> that is notified about 
     * changes on Buildpath entries or <code>null</code> if no such notification is 
     * necessary.
     * @param informationProvider a provider to offer information to the operation
     * 
     * @see IBuildpathInformationProvider
     * @see BuildpathModifier
     */
    public ResetOperation(IBuildpathModifierListener listener, IBuildpathInformationProvider informationProvider) {
        super(listener, informationProvider, NewWizardMessages.NewSourceContainerWorkbookPage_ToolBar_Reset_tooltip, IBuildpathInformationProvider.RESET); 
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
            List selection= getSelectedElements();
            IScriptProject project= fInformationProvider.getDLTKProject();
            result= reset(selection, project, monitor);
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
        IScriptProject project= fInformationProvider.getDLTKProject();
        boolean hasResetableFragmentRoot= false;
        boolean hasOutputFolder= false;
        boolean hasResetableProject= false;
        /*
         * This computation is special compared to the other ones in operations:
         * As soon as there is at least one element which allows resetting, reseting 
         * is allowed (note: NOT ALL have to allow this!).
         * 
         * Of course, resetting is still not allowed if there is at least one element 
         * which does not support resetting at all!
         */
        for(int i= 0; i < elements.size(); i++) {
            switch(types[i]) {
                case DialogPackageExplorerActionGroup.SCRIPT_PROJECT: hasResetableProject= isValidProject(project); break; // as standalone selection, this is not ok --> check at end
                case DialogPackageExplorerActionGroup.PACKAGE_FRAGMENT_ROOT: break; // as standalone selection, this is not ok --> check at end
                case DialogPackageExplorerActionGroup.MODIFIED_FRAGMENT_ROOT: hasResetableFragmentRoot= true; break; // is ok                                
                default: return false; // all others are not ok
            }
            
        }
        return hasResetableFragmentRoot || hasOutputFolder || hasResetableProject;
    }
    
    /**
     * Find out whether the filters on the project can be reset or not.
     * 
     * @param project the script project
     * @return <code>true</code> if this operation can be executed on the project, 
     * <code>false</code> otherwise
     * @throws ModelException
     */
    private boolean isValidProject(IScriptProject project) throws ModelException {
        if (project.isOnBuildpath(project)) {
            IBuildpathEntry entry= BuildpathModifier.getBuildpathEntryFor(project.getPath(), project, IBuildpathEntry.BPE_SOURCE);
            if (entry.getInclusionPatterns().length != 0 || entry.getExclusionPatterns().length != 0)
                return true;
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
        if (type == DialogPackageExplorerActionGroup.MODIFIED_FRAGMENT_ROOT || 
                type == (DialogPackageExplorerActionGroup.MODIFIED_FRAGMENT_ROOT | DialogPackageExplorerActionGroup.MULTI))
            return NewWizardMessages.PackageExplorerActionGroup_FormText_ResetFilters; 
        return NewWizardMessages.PackageExplorerActionGroup_FormText_Default_Reset; 
    }
}
