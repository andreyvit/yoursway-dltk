/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.core.builder;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.dltk.core.IScriptProject;

/**
 * Interface called from script builder to build the selected resource.
 * 
 * @author Haiodo
 * 
 */
public interface IScriptBuilder {
	/**
	 * Called for each resource required to build. Only resources with specified
	 * project nature are here.
	 * 
	 * @return
	 */
	IStatus[] buildResources(IScriptProject project, List resources,
			IProgressMonitor monitor);

	/**
	 * Called for each resource required to build. Only resources with
	 * specified project nature are here.
	 * 
	 * @return
	 */
	IStatus[] buildModelElements(IScriptProject project, List elements,
			IProgressMonitor monitor);

	/**
	 * Return all dependencies for selected resource. Should also return all
	 * dependencies of returned elements.
	 * 
	 * For example if c depends on b and b depends on a, and we ask for
	 * dependencies or a, then b and c should be returned.
	 * 
	 * Resources should be checked for type. Because different kind of resource
	 * could be here.
	 * 
	 * @param resource
	 * @return null, if no dependencies are found. Should not return elements
	 *         from resources list.
	 */
	List getDependencies(IScriptProject project, List resources);
}
