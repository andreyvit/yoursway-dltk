/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.filters;


import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;



/**
 * Filters empty non-leaf package fragments
 */
public class EmptyInnerPackageFilter extends ViewerFilter {

	/*
	 * @see ViewerFilter
	 */
	public boolean select(Viewer viewer, Object parent, Object element) {
		if (element instanceof IScriptFolder) {
			IScriptFolder pkg= (IScriptFolder)element;
			try {
				if (pkg.isRootFolder())
					return pkg.hasChildren();
				return !pkg.hasSubfolders() || pkg.hasChildren() || (pkg.getForeignResources().length > 0);
			} catch (ModelException e) {
				return false;
			}
		}

		return true;
	}
}
