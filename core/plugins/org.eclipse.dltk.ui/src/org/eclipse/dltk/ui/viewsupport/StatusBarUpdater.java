/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.viewsupport;


import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.internal.corext.util.Messages;
import org.eclipse.dltk.internal.ui.DLTKUIMessages;
import org.eclipse.dltk.internal.ui.scriptview.BuildPathContainer;
import org.eclipse.dltk.ui.ScriptElementLabels;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.model.IWorkbenchAdapter;


/**
 * Add the <code>StatusBarUpdater</code> to your ViewPart to have the statusbar
 * describing the selected elements.
 */
public class StatusBarUpdater implements ISelectionChangedListener {
	
	private final long LABEL_FLAGS= ScriptElementLabels.DEFAULT_QUALIFIED | ScriptElementLabels.ROOT_POST_QUALIFIED | ScriptElementLabels.APPEND_ROOT_PATH |
			ScriptElementLabels.M_PARAMETER_TYPES | ScriptElementLabels.M_PARAMETER_NAMES | ScriptElementLabels.M_APP_RETURNTYPE | ScriptElementLabels.M_EXCEPTIONS | 
		 	ScriptElementLabels.F_APP_TYPE_SIGNATURE | ScriptElementLabels.T_TYPE_PARAMETERS;
		 	
	private IStatusLineManager fStatusLineManager;
	
	public StatusBarUpdater(IStatusLineManager statusLineManager) {
		fStatusLineManager= statusLineManager;
	}
		
	/*
	 * @see ISelectionChangedListener#selectionChanged
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		String statusBarMessage= formatMessage(event.getSelection());
		fStatusLineManager.setMessage(statusBarMessage);
	}
	
	
	protected String formatMessage(ISelection sel) {
		if (sel instanceof IStructuredSelection && !sel.isEmpty()) {
			IStructuredSelection selection= (IStructuredSelection) sel;
			
			int nElements= selection.size();
			if (nElements > 1) {
				return Messages.format(DLTKUIMessages.StatusBarUpdater_num_elements_selected, String.valueOf(nElements)); 
			} else { 
				Object elem= selection.getFirstElement();
				if (elem instanceof IModelElement) {
					return formatModelElementMessage((IModelElement) elem);
				} else if (elem instanceof IResource) {
					return formatResourceMessage((IResource) elem);
				} else if (elem instanceof BuildPathContainer) {
					BuildPathContainer container= (BuildPathContainer) elem;
					return container.getLabel(container) + ScriptElementLabels.CONCAT_STRING + container.getScriptProject().getElementName();
				} else if (elem instanceof IAdaptable) {
					IWorkbenchAdapter wbadapter= (IWorkbenchAdapter) ((IAdaptable)elem).getAdapter(IWorkbenchAdapter.class);
					if (wbadapter != null) {
						return wbadapter.getLabel(elem);
					}
				}
			}
		}
		return "";  //$NON-NLS-1$
	}
		
	private String formatModelElementMessage(IModelElement element) {
		return ScriptElementLabels.getDefault().getElementLabel(element, LABEL_FLAGS);
	}
		
	private String formatResourceMessage(IResource element) {
		IContainer parent= element.getParent();
		if (parent != null && parent.getType() != IResource.ROOT)
			return element.getName() + ScriptElementLabels.CONCAT_STRING + parent.getFullPath().makeRelative().toString();
		else
			return element.getName();
	}	

}
