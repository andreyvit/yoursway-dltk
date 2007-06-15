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
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.ui.wizards.NewWizardMessages;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.DialogPackageExplorerActionGroup;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.GenerateBuildPathActionGroup.EditFilterAction;
import org.eclipse.jface.viewers.IStructuredSelection;


/**
 * Operation to edit the inclusion / exclusion filters of an
 * <code>IModelElement</code>.
 * 
 */
public class EditFiltersOperation extends BuildpathModifierOperation {
	
	private final IBuildpathInformationProvider fCPInformationProvider;
	private final IBuildpathModifierListener fListener;

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
	public EditFiltersOperation(IBuildpathModifierListener listener, IBuildpathInformationProvider informationProvider) {
		super(listener, informationProvider, NewWizardMessages.NewSourceContainerWorkbookPage_ToolBar_Edit_tooltip, IBuildpathInformationProvider.EDIT_FILTERS);
		fListener= listener;
		fCPInformationProvider= informationProvider; 
		
	}
	
	/**
	 * Method which runs the actions with a progress monitor.<br>
	 * 
	 * This operation requires the following query:
	 * <li>IInclusionExclusionQuery</li>
	 * 
	 * @param monitor a progress monitor, can be <code>null</code>
	 */
	public void run(IProgressMonitor monitor) throws InvocationTargetException {
		EditFilterAction action= new EditFilterAction();
		IStructuredSelection selection= fCPInformationProvider.getSelection();
		Object firstElement= selection.getFirstElement();
		action.selectionChanged(selection);
		action.run();
		List l= new ArrayList();
		l.add(firstElement);
		if (fListener != null) {
			List entries= action.getBPListElements();
			fListener.buildpathEntryChanged(entries);
		}
		fCPInformationProvider.handleResult(l, null, IBuildpathInformationProvider.EDIT_FILTERS);
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
		if (elements.size() != 1)
			return false;
		IScriptProject project= fInformationProvider.getDLTKProject();
		Object element= elements.get(0);
		
		if (element instanceof IScriptProject) {
			if (isSourceFolder(project))
				return true;
		} else if (element instanceof IProjectFragment) {
			return ((IProjectFragment) element).getKind() == IProjectFragment.K_SOURCE;
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
		if (type == DialogPackageExplorerActionGroup.SCRIPT_PROJECT)
			return NewWizardMessages.PackageExplorerActionGroup_FormText_Edit; 
		if (type == DialogPackageExplorerActionGroup.PACKAGE_FRAGMENT_ROOT)
			return NewWizardMessages.PackageExplorerActionGroup_FormText_Edit; 
		if (type == DialogPackageExplorerActionGroup.MODIFIED_FRAGMENT_ROOT)
			return NewWizardMessages.PackageExplorerActionGroup_FormText_Edit; 
		return NewWizardMessages.PackageExplorerActionGroup_FormText_Default_Edit; 
	}
}
