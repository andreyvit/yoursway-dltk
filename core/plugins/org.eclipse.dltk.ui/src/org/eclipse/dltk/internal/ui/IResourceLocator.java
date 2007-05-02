/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui;

import org.eclipse.core.resources.IResource;
import org.eclipse.dltk.core.ModelException;


/**
 * This interface allows to locate different
 * resources which are related to an object
 */
public interface IResourceLocator {
	/**
	 * Returns the underlying finest granularity resource that contains
	 * the element, or <code>null</code> if the element is not contained
	 * in a resource (for example, a working copy, or an element contained
	 * in an external archive).
	 *
	 * @param	element	the element for which the resource is located
	 * @return the underlying resource
	 * @exception ModelException if the element does not exist or if an
	 *		exception occurs while accessing its underlying resource
	 */
	IResource getUnderlyingResource(Object element) throws ModelException;
	/**
	 * Returns the resource that corresponds directly to the element,
	 * or <code>null</code> if there is no resource that corresponds to
	 * the element.
	 *
	 * <p>For example, the corresponding resource for an <code>ISourceModule</code>
	 * is its underlying <code>IFile</code>. The corresponding resource for
	 * an <code>IScriptFolder</code> that is not contained in an archive 
	 * is its underlying <code>IFolder</code>. An <code>IScriptFolder</code>
	 * contained in an archive has no corresponding resource. Similarly, there
	 * are no corresponding resources for <code>IMethods</code>,
	 * <code>IFields</code>, etc.
	 *
	 * @param	element	the element for which the resource is located
	 * @return the corresponding resource
	 * @exception ModelException if the element does not exist or if an
	 *		exception occurs while accessing its corresponding resource
	 */
	IResource getCorrespondingResource(Object element) throws ModelException;
	/**
	 * Returns the resource that contains the element. If the element is not
	 * directly contained by a resource then a helper resource or <code>null</code>
	 * is returned. Clients define the helper resource as needed.
	 *
	 * @param	element	the element for which the resource is located
	 * @return the containing resource
	 * @exception ModelException if the element does not exist or if an
	 *		exception occurs while accessing its containing resource
	 */
	IResource getContainingResource(Object element) throws ModelException;
}
