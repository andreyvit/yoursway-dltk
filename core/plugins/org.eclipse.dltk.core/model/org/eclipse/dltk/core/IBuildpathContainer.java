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
package org.eclipse.dltk.core;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.internal.adaptor.IModel;

/** 
 * Interface of a buildpath container.
 * A buildpath container provides a way to indirectly reference a set of buildpath entries through
 * a buildpath entry of kind <code>BPE_CONTAINER</code>. Typically, a buildpath container can
 * be used to describe a complex library composed of multiple archives or projects, considering also 
 * that containers can map to different set of entries on each project, in other words, several 
 * projects can reference the same generic container path, but have each of them actually bound 
 * to a different container object.
 * <p>
 * The set of entries associated with a buildpath container may contain any of the following:
 * <ul>
 * <li> library entries (<code>BPE_LIBRARY</code>) </li>
 * <li> project entries (<code>BPE_PROJECT</code>) </li>
 * </ul>
 * In particular, a buildpath container can neither reference further buildpath containers or buildpath variables.
 * <p>
 * buildpath container values are persisted locally to the workspace, but are not preserved from a 
 * session to another. It is thus highly recommended to register a <code>buildpathContainerInitializer</code> 
 * for each referenced container (through the extension point "org.eclipse.dltk.core.buildpathContainerInitializer").
 * <p>
 * @see IbuildpathEntry
	 *
 */

public interface IBuildpathContainer {
	
	/**
	 * Kind for a container mapping to an application library
	 */
	int K_APPLICATION = 1;

	/**
	 * Kind for a container mapping to a system library
	 */
	int K_SYSTEM = 2;

	/**
	 * Kind for a container mapping to a default system library, implicitly contributed by the runtime
	 */
	int K_DEFAULT_SYSTEM = 3;

	/**
	 * Answers the set of buildpath entries this container is mapping to.
	 * <p>
	 * The set of entries associated with a buildpath container may contain any of the following:
	 * <ul>
	 * <li> library entries (<code>CPE_LIBRARY</code>) </li>
	 * <li> project entries (<code>CPE_PROJECT</code>) </li>
	 * </ul>
	 * A buildpath container can neither reference further buildpath containers
	 * or buildpath variables.
	 * </p>
	 * <p>
	 * This method is called by the script model when it needs to resolve this
	 * buildpath container entry into a list of library and project entries.
	 * The method is typically called exactly once for a given script project,
	 * and the resulting list of entries cached internally by the script model.
	 * This method must not be called by other clients.
	 * <p>
	 * There are a wide variety of conditions under which this method may be
	 * invoked. To ensure that the implementation does not interfere with
	 * correct functioning of the script model, the implementation should use
	 * only the following script model APIs:
	 * <ul>
	 * <li>{@link DLTKCore#newLibraryEntry(IPath, IPath, IPath, boolean, boolean)} and variants</li>
	 * <li>{@link DLTKCore#newProjectEntry(IPath, boolean)} and variants</li>
	 * <li>{@link DLTKCore#create(org.eclipse.core.resources.IWorkspaceRoot)}</li>
	 * <li>{@link DLTKCore#create(org.eclipse.core.resources.IProject)}</li>
	 * <li>{@link IModel#getDLTKProjects()}</li>
	 * <li>{@link IDLTKProject#getRawbuildpath()}</li>
	 * <li>{@link IDLTKProject#readRawbuildpath()}</li>
	 * <li>{@link IDLTKProject#getOutputLocation()}</li>
	 * <li>{@link IDLTKProject#readOutputLocation()}</li>
	 * <li>script element operations marked as "handle-only"</li>
	 * </ul>
	 * The effects of using other script model APIs are unspecified.
	 * </p>
	 * 
	 * @return IBuildpathEntry[] - the buildpath entries this container represents
	 * @see IBuildpathEntry
	 */	
    IBuildpathEntry[] getBuildpathEntries();

	/**
	 * Answers a readable description of this container
	 * 
	 * @return String - a string description of the container
	 */	
    String getDescription();

	/**
	 * Answers the kind of this container. Can be either:
	 * <ul>
	 * <li><code>K_APPLICATION</code> if this container maps to an application library</li>
	 * <li><code>K_SYSTEM</code> if this container maps to a system library</li>
	 * <li><code>K_DEFAULT_SYSTEM</code> if this container maps to a default system library (library
	 * 	implicitly contributed by the runtime).</li>
	 * </ul>
	 * Typically, system containers should be placed first on a build path.
	 * @return the kind of this container
	 */	
    int getKind();

	/**
	 * Answers the container path identifying this container.
	 * A container path is formed by a first ID segment followed with extra segments, which 
	 * can be used as additional hints for resolving to this container.
	 * <p>
	 * The container ID is also used to identify a<code>buildpathContainerInitializer</code>
	 * registered on the extension point "org.eclipse.dltk.core.buildpathContainerInitializer", which can
	 * be invoked if needing to resolve the container before it is explicitly set.
	 * <p>
	 * @return IPath - the container path that is associated with this container
	 */	
    IPath getPath();
}

