/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.filters;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IStorage;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;



/**
 * Filters out all non-Script elements.
 */
public class NonScriptElementFilter  extends ViewerFilter {
	
	/**
	 * Returns the result of this filter, when applied to the
	 * given inputs.
	 *
	 * @return Returns true if element should be included in filtered set
	 */
	public boolean select(Viewer viewer, Object parent, Object element) {
		if (element instanceof IModelElement)
			return true;
		
		if (element instanceof IResource) {
			IProject project= ((IResource)element).getProject(); 
			return project == null || !project.isOpen();
		}

		// Exclude all IStorage elements which are neither Script elements nor resources
		if (element instanceof IStorage)
			return false;
			
		return true;
	}
}
