/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.filters;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * Filters out all compilation units and class files elements.
 */
public class DotFileFilter extends ViewerFilter {

	/**
	 * Returns the result of this filter, when applied to the given inputs.
	 * 
	 * @return Returns true if element should be included in filtered set
	 */
	public boolean select(Viewer viewer, Object parent, Object element) {
		if (element instanceof IScriptFolder) {
			String name = ((IScriptFolder) element).getElementName();
			Path path = new Path(name);
			for (int i = 0; i < path.segmentCount(); i++) {
				String segment = path.segment(i);
				if (segment.startsWith(".")) { //$NON-NLS-1$
					return false;
				}
			}
		} else if (element instanceof ISourceModule) {
			if (((ISourceModule) element).getElementName().startsWith(".")) { //$NON-NLS-1$
				return false;
			}
		} else if (element instanceof IResource) {
			String lastSegment = ((IResource) element).getFullPath()
					.lastSegment();
			if (lastSegment.startsWith(".")) { //$NON-NLS-1$
				return false;
			}
		}
		return true;
	}
}
