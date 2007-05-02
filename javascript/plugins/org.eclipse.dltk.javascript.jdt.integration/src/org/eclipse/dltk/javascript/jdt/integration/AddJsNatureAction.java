/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.javascript.jdt.integration;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.dltk.javascript.core.JavaScriptNature;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class AddJsNatureAction implements IExecutableExtension,
		IObjectActionDelegate {

	ISelection selection;

	public AddJsNatureAction() {
	}

	public void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) throws CoreException {
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	public void run(IAction action) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection) selection;
			Object[] array = ssel.toArray();
			for (int a = 0; a < array.length; a++) {
				Object o = array[a];
				if (o instanceof IAdaptable) {
					IAdaptable adaptable = (IAdaptable) o;
					IProject adapter = (IProject) adaptable
							.getAdapter(IProject.class);
					try {
						IProjectDescription description = adapter
								.getDescription();
						String[] natureIds = description.getNatureIds();
						String[] newNStrings = new String[natureIds.length + 1];
						System.arraycopy(natureIds, 0, newNStrings, 0,
								natureIds.length);
						newNStrings[natureIds.length] = JavaScriptNature.NATURE_ID;
						description.setNatureIds(newNStrings);
						adapter.setDescription(description, null);
						adapter.getFile(".buildpath").create(
								this.getClass().getResourceAsStream(
										"buildpath.snap"), true, null);
					} catch (CoreException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

}
