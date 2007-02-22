/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.filters;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;



/**
 * The LibraryFilter is a filter used to determine whether
 * a Script internal library is shown
 */
public class ContainedLibraryFilter extends ViewerFilter {
	
	/* (non-Javadoc)
	 * Method declared on ViewerFilter.
	 */
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (element instanceof IProjectFragment) {
			IProjectFragment root= (IProjectFragment)element;
			if (root.isArchive()) {
				// don't filter out archives contained in the project itself
				IResource resource= root.getResource();
				if (resource != null) {
					IProject archiveProject= resource.getProject();
					IProject container= root.getScriptProject().getProject();
					return !container.equals(archiveProject);
				}
			}
		}
		return true;
	}
}
