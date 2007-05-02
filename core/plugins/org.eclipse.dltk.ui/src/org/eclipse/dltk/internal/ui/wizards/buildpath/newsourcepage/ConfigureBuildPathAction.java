/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.dltk.core.DLTKModelUtil;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.internal.ui.scriptview.BuildPathContainer;
import org.eclipse.dltk.internal.ui.wizards.NewWizardMessages;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.dialogs.PreferencesUtil;


/**
 * 
 */
public class ConfigureBuildPathAction extends Action implements ISelectionChangedListener {

	private final IWorkbenchSite fSite;
	private IProject fProject;

	public ConfigureBuildPathAction(IWorkbenchSite site) {
		super(NewWizardMessages.NewSourceContainerWorkbookPage_ToolBar_ConfigureBP_label, DLTKPluginImages.DESC_ELCL_CONFIGURE_BUILDPATH);
		setToolTipText(NewWizardMessages.NewSourceContainerWorkbookPage_ToolBar_ConfigureBP_tooltip);
		setDisabledImageDescriptor(DLTKPluginImages.DESC_DLCL_CONFIGURE_BUILDPATH);		
		fSite= site;
	}
	
	private Shell getShell() {
		return fSite.getShell();
	}
	
	public void run() {
		if (fProject != null) {
			PreferenceDialog dialog = PreferencesUtil.createPropertyDialogOn(getShell(), fProject, null, null, null);			
			List elements = dialog.getPreferenceManager().getElements(0);
			String id = null;
			if(elements != null ) {
				Iterator i = elements.iterator();
				while(i.hasNext()) {
					IPreferenceNode node =  (IPreferenceNode)i.next();
					if( node.getId().endsWith("BuildpathProperties")) {
						id = node.getId();
						break;
					}
				}
			}
			PreferencesUtil.createPropertyDialogOn(getShell(), fProject, id, null, null).open();
		}
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	public void selectionChanged(final SelectionChangedEvent event) {
		final ISelection selection = event.getSelection();
		if (selection instanceof IStructuredSelection) {
			setEnabled(canHandle((IStructuredSelection) selection));
		} else {
			setEnabled(canHandle(StructuredSelection.EMPTY));
		}
	}

	private boolean canHandle(IStructuredSelection elements) {
		if (elements.size() != 1)
			return false;
	
		Object firstElement= elements.getFirstElement();
		fProject= getProjectFromSelectedElement(firstElement);
		return fProject != null;
	}


	private IProject getProjectFromSelectedElement(Object firstElement) {
		if (firstElement instanceof IModelElement) {
			IModelElement element= (IModelElement) firstElement;
			IProjectFragment root= DLTKModelUtil.getProjectFragment(element);
						
			if (root != null && root != element && root.isArchive()) {
				return null;
			}
			IDLTKProject project= element.getScriptProject();
			if (project != null) {
				return project.getProject();
			}
			return null;
		} else if (firstElement instanceof BuildPathContainer) {
			return ((BuildPathContainer) firstElement).getScriptProject().getProject();
		} else if (firstElement instanceof IAdaptable) {
			IResource res= (IResource) ((IAdaptable) firstElement).getAdapter(IResource.class);
			if (res != null) {
				return res.getProject();
			}
		}
		return null;
	}

}
