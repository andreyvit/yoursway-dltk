/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.workingsets;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.IScriptModel;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.ui.StandardModelElementContentProvider;


class ScriptWorkingSetPageContentProvider extends StandardModelElementContentProvider {
	
	public boolean hasChildren(Object element) {

		if (element instanceof IProject && !((IProject)element).isAccessible())
			return false;

		if (element instanceof IScriptFolder) {
			//IScriptFolder pkg= (IScriptFolder)element;
			if (DLTKCore.DEBUG) {
				System.err.println("Add binary source folder support");
			}
//			try {
//				if (pkg.getKind() == IProjectFragment.K_BINARY)
//					return pkg.getChildren().length > 0;
//			} catch (ModelException ex) {
				// use super behavior
//			}
		}
		return super.hasChildren(element);
	}

	public Object[] getChildren(Object parentElement) {
		try {
			if (parentElement instanceof IScriptModel) 
				return concatenate(super.getChildren(parentElement), getForeignProjects((IScriptModel)parentElement));
			
			if (parentElement instanceof IProject) 
				return ((IProject)parentElement).members();

			return super.getChildren(parentElement);
		} catch (CoreException e) {
			return NO_CHILDREN;
		}
	}

	private Object[] getForeignProjects(IScriptModel model) throws ModelException {
		return model.getForeignResources();
	}
}
