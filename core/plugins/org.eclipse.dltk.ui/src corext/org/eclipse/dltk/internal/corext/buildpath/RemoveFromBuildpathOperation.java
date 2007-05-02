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
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.corext.util.Messages;
import org.eclipse.dltk.internal.ui.scriptview.BuildPathContainer;
import org.eclipse.dltk.internal.ui.wizards.NewWizardMessages;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.DialogPackageExplorerActionGroup;


/**
 * Operation to remove source folders (of type <code>
 * IProjectFragment</code> from the buildpath.
 * 
 */
public class RemoveFromBuildpathOperation extends BuildpathModifierOperation {
    
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
    public RemoveFromBuildpathOperation(IBuildpathModifierListener listener, IBuildpathInformationProvider informationProvider) {
        super(listener, informationProvider, NewWizardMessages.NewSourceContainerWorkbookPage_ToolBar_RemoveFromCP_tooltip, IBuildpathInformationProvider.REMOVE_FROM_BP); 
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
            result= removeFromBuildpath(fInformationProvider.getRemoveLinkedFolderQuery(), getSelectedElements(), fInformationProvider.getDLTKProject(), monitor);
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
        Iterator iterator= elements.iterator();
        while (iterator.hasNext()) {
            Object element= iterator.next();
            if (!(element instanceof IProjectFragment || element instanceof IDLTKProject || element instanceof BuildPathContainer))
                return false;
            if (element instanceof IDLTKProject) {
                if (!isSourceFolder(project))
                    return false;
            } else if (element instanceof IProjectFragment) {
				IBuildpathEntry entry= ((IProjectFragment) element).getRawBuildpathEntry();
				if (entry != null && entry.getEntryKind() == IBuildpathEntry.BPE_CONTAINER) {
					return false;
				}
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
        IModelElement elem= (IModelElement)getSelectedElements().get(0);
        String name= escapeSpecialChars(elem.getElementName());
        if (type == DialogPackageExplorerActionGroup.SCRIPT_PROJECT)
            return Messages.format(NewWizardMessages.PackageExplorerActionGroup_FormText_ProjectFromBuildpath, name); 
        if (type == DialogPackageExplorerActionGroup.PACKAGE_FRAGMENT_ROOT || type == DialogPackageExplorerActionGroup.MODIFIED_FRAGMENT_ROOT)
            return Messages.format(NewWizardMessages.PackageExplorerActionGroup_FormText_fromBuildpath, name); 
        return NewWizardMessages.PackageExplorerActionGroup_FormText_Default_FromBuildpath; 
    }
}
