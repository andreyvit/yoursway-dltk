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
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;


/**
 * Filters non-java projects
 */
public class NonScriptProjectsFilter extends ViewerFilter {

	/*
	 * @see ViewerFilter
	 */
	public boolean select(Viewer viewer, Object parent, Object element) {
		if (element instanceof IDLTKProject)
			return true;
		else if (element instanceof IProject)
			return !((IProject)element).isOpen();

		return true; 
	}
}
