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
package org.eclipse.dltk.internal.ui;

import org.eclipse.core.resources.IResource;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ModelException;


/**
 * This class locates different resources
 * which are related to an object
 */
public class ResourceLocator implements IResourceLocator {
	
	public IResource getUnderlyingResource(Object element) throws ModelException {
		if (element instanceof IModelElement)
			return ((IModelElement) element).getUnderlyingResource();
		else
			return null;
	}

	public IResource getCorrespondingResource(Object element) throws ModelException {
		if (element instanceof IModelElement)
			return ((IModelElement) element).getCorrespondingResource();
		else
			return null;
	}

	public IResource getContainingResource(Object element) throws ModelException {
		IResource resource= null;
		if (element instanceof IResource)
			resource= (IResource) element;
		if (element instanceof IModelElement) {
			resource= ((IModelElement) element).getResource();
			if (resource == null)
				resource= ((IModelElement) element).getScriptProject().getProject();
		}
		return resource;
	}
}
