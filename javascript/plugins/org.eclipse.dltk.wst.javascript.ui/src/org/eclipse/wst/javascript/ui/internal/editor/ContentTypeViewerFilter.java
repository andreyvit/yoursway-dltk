/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.javascript.ui.internal.editor;

import java.util.Hashtable;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;


public class ContentTypeViewerFilter extends ViewerFilter {
	private IContentType[] fContentTypes;
	private Hashtable visitedElements;

	public ContentTypeViewerFilter(IContentType[] contentTypes) {
		fContentTypes = contentTypes;
		visitedElements = new Hashtable();
	}

	public boolean isFilterProperty(Object element, Object property) {
		return false;
	}

	public boolean isValid(Object element) {
		if (IFile.class.isInstance(element))
			return isValidFile((IFile) element);
		if (IContainer.class.isInstance(element))
			return isValidContainer((IContainer) element);
		return false;
	}

	/*
	 * A valid container is one that contains at least one valid member.
	 */
	public boolean isValidContainer(IContainer container) {
		IResource[] members;
		Object valid = visitedElements.get(container);
		if (valid != null)
			return ((Boolean) valid).booleanValue();
		try {
			members = container.members();
			for (int i = 0; i < members.length; i++) {
				if (isValid(members[i])) {
					visitedElements.put(container, Boolean.TRUE);
					return true;
				}
			}
		}
		catch (CoreException e) {
		}
		visitedElements.put(container, Boolean.FALSE);
		return false;
	}

	public boolean isValidFile(IFile file) {
		try {
			IContentDescription fileContentDescription = file.getContentDescription();
			if (fileContentDescription == null)
				return false;
			
			IContentType fileContentType = fileContentDescription.getContentType();
			for (int i = 0; i < fContentTypes.length; i++) {
				if (fileContentType.isKindOf(fContentTypes[i])) {
					return true;
				}
			}
		}
		catch (CoreException e) {
			Logger.logException(e);
		}
		return false;
	}

	public boolean select(Viewer viewer, Object parentElement, Object element) {
		return isValid(element);
	}
}
