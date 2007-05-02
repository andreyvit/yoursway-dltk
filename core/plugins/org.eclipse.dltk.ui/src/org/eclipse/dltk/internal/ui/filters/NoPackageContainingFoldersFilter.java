/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.filters;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;


public class NoPackageContainingFoldersFilter extends ViewerFilter {

	/*
	 * @see ViewerFilter
	 */
	public boolean select(Viewer viewer, Object parent, Object element) {
		if (element instanceof IFolder) {
			try {
				if (containsPackage((IFolder)element))
					return true;
				else
					return false;
			} catch (CoreException e) {
				return true;
			}
		}

		return true;
	}
	
	private boolean containsPackage(IFolder folder) throws CoreException {
		IModelElement element= DLTKCore.create(folder);
		if (element instanceof IScriptFolder)
			return true;
		IResource[] resources= folder.members();
		for (int i= 0; i < resources.length; i++) {
			if (resources[i] instanceof IFolder && containsPackage((IFolder)resources[i]))
				return true;
		}
		return false;
	}

}
