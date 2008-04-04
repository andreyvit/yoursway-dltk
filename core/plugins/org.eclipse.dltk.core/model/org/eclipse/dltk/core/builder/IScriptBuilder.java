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
import java.util.Set;

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
	public static final int INCREMENTAL_BUILD = 0;
	public static final int FULL_BUILD = 1;

	/**
	 * Estimate number of elements will be build from given set.
	 * 
	 * @return
	 */
	int estimateElementsToBuild(List elements);

	/**
	 * Called for each resource required to build. Only resources with specified
	 * project nature are here.
	 * 
	 * @return
	 */
	IStatus buildResources(IScriptProject project, List resources,
			IProgressMonitor monitor, int status);

	/**
	 * Called for each resource required to build. Only resources with specified
	 * project nature are here.
	 * 
	 * @return
	 */
	IStatus buildModelElements(IScriptProject project, List elements,
			IProgressMonitor monitor, int status);

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
	Set getDependencies(IScriptProject project, Set resources,
			Set allResources, Set oldExternalFolders, Set externalFolders);
}
