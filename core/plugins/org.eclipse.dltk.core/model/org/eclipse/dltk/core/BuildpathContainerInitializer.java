/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *     IBM Corporation - added support for requesting updates of a particular
 *                       container for generic container operations.
 * 						 - canUpdateBuildpathContainer(IPath, IDLTKProject)
 * 						 - requestBuildpathContainerUpdate(IPath, IDLTKProject, IBuildpathContainer)
 *     IBM Corporation - allow initializers to provide a readable description
 *                       of a container reference, ahead of actual resolution.
 * 						 - getDescription(IPath, IDLTKProject)
 *******************************************************************************/
package org.eclipse.dltk.core;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;


/**
 * Abstract base implementation of all buildpath container initializer.
 * Buildpath variable containers are used in conjunction with the
 * "org.eclipse.dltk.core.buildpathContainerInitializer" extension point.
 * <p>
 * Clients should subclass this class to implement a specific buildpath
 * container initializer. The subclass must have a public 0-argument
 * constructor and a concrete implementation of <code>initialize</code>.
 * <p>
 * Multiple buildpath containers can be registered, each of them declares
 * the container ID they can handle, so as to narrow the set of containers they
 * can resolve, in other words, a container initializer is guaranteed to only be 
 * activated to resolve containers which match the ID they registered onto.
 * <p>
 * In case multiple container initializers collide on the same container ID, the first
 * registered one will be invoked.
 * 
 * @see IBuildpathEntry
 * @see IBuildpathContainer
	 *
 */
public abstract class BuildpathContainerInitializer {
	
   /**
     * Creates a new buildpath container initializer.
     */
    public BuildpathContainerInitializer() {
    	// a buildpath container initializer must have a public 0-argument constructor
    }

    /**
     * Binds a buildpath container to a <code>IBuildpathContainer</code> for a given project,
     * or silently fails if unable to do so.
     * <p>
     * A container is identified by a container path, which must be formed of two segments.
     * The first segment is used as a unique identifier (which this initializer did register onto), and
     * the second segment can be used as an additional hint when performing the resolution.
     * <p>
     * The initializer is invoked if a container path needs to be resolved for a given project, and no
     * value for it was recorded so far. The implementation of the initializer would typically set the 
     * corresponding container using <code>DLTKCore#setBuildpathContainer</code>.
     * <p>
     * A container initialization can be indirectly performed while attempting to resolve a project
     * buildpath using <code>IDLTKProject#getResolvedBuildpath(</code>; or directly when using
     * <code>DLTKCore#getBuildpathContainer</code>. During the initialization process, any attempt
     * to further obtain the same container will simply return <code>null</code> so as to avoid an
     * infinite regression of initializations.
     * <p>
     * A container initialization may also occur indirectly when setting a project buildpath, as the operation
     * needs to resolve the buildpath for validation purpose. While the operation is in progress, a referenced 
     * container initializer may be invoked. If the initializer further tries to access the referring project buildpath, 
     * it will not see the new assigned buildpath until the operation has completed. Note that once the Script language 
     * change notification occurs (at the end of the operation), the model has been updated, and the project 
     * buildpath can be queried normally.
	 * <p>
	 * This method is called by the Script language model to give the party that defined
	 * this particular kind of buildpath container the chance to install
	 * buildpath container objects that will be used to convert buildpath
	 * container entries into simpler buildpath entries. The method is typically
	 * called exactly once for a given Script language project and buildpath container
	 * entry. This method must not be called by other clients.
	 * <p>
	 * There are a wide variety of conditions under which this method may be
	 * invoked. To ensure that the implementation does not interfere with
	 * correct functioning of the Script language model, the implementation should use
	 * only the following Script language model APIs:
	 * <ul>
	 * <li>{@link DLTKCore#setBuildpathContainer(IPath, IScriptProject[], IBuildpathContainer[], org.eclipse.core.runtime.IProgressMonitor)}</li>
	 * <li>{@link DLTKCore#getBuildpathContainer(IPath, IScriptProject)}</li>
	 * <li>{@link DLTKCore#create(org.eclipse.core.resources.IWorkspaceRoot)}</li>
	 * <li>{@link DLTKCore#create(org.eclipse.core.resources.IProject)}</li>
	 * <li>{@link IScript languageModel#getScript languageProjects()}</li>
	 * <li>Script language element operations marked as "handle-only"</li>
	 * </ul>
	 * The effects of using other Script language model APIs are unspecified.
	 * </p>
	 * 
     * @param containerPath a two-segment path (ID/hint) identifying the container that needs 
     * 	to be resolved
     * @param project the Script language project in which context the container is to be resolved.
     *    This allows generic containers to be bound with project specific values.
     * @throws CoreException if an exception occurs during the initialization
     * 
     * @see DLTKCore#getBuildpathContainer(IPath, IScriptProject)
     * @see DLTKCore#setBuildpathContainer(IPath, IScriptProject[], IBuildpathContainer[], org.eclipse.core.runtime.IProgressMonitor)
     * @see IBuildpathContainer
     */
    public abstract void initialize(IPath containerPath, IScriptProject project) throws CoreException;
    
    /**
     * Returns <code>true</code> if this container initializer can be requested to perform updates 
     * on its own container values. If so, then an update request will be performed using
     * <code>BuildpathContainerInitializer#requestBuildpathContainerUpdate</code>/
     * <p>
     * @param containerPath the path of the container which requires to be updated
     * @param project the project for which the container is to be updated
     * @return returns <code>true</code> if the container can be updated
	 *
     */
    public boolean canUpdateBuildpathContainer(IPath containerPath, IScriptProject project) {
    	
		// By default, buildpath container initializers do not accept updating containers
    	return false; 
    }

	/**
	 * Request a registered container definition to be updated according to a container suggestion. The container suggestion 
	 * only acts as a place-holder to pass along the information to update the matching container definition(s) held by the 
	 * container initializer. In particular, it is not expected to store the container suggestion as is, but rather adjust 
	 * the actual container definition based on suggested changes.
	 * <p>
	 * IMPORTANT: In reaction to receiving an update request, a container initializer will update the corresponding
	 * container definition (after reconciling changes) at its earliest convenience, using 
	 * <code>DLTKCore#setBuildpathContainer(IPath, IDLTKProject[], IBuildpathContainer[], IProgressMonitor)</code>. 
	 * Until it does so, the update will not be reflected in the Script language Model.
	 * <p>
	 * In order to anticipate whether the container initializer allows to update its containers, the predicate
	 * <code>DLTKCore#canUpdateBuildpathContainer</code> should be used.
	 * <p>
	 * @param containerPath the path of the container which requires to be updated
     * @param project the project for which the container is to be updated
	 * @param containerSuggestion a suggestion to update the corresponding container definition
	 * @throws CoreException when <code>DLTKCore#setBuildpathContainer</code> would throw any.
	 * @see DLTKCore#setBuildpathContainer(IPath, IScriptProject[], IBuildpathContainer[], org.eclipse.core.runtime.IProgressMonitor)
	 * @see BuildpathContainerInitializer#canUpdateBuildpathContainer(IPath, IScriptProject)
	 *
	 */
    public void requestBuildpathContainerUpdate(IPath containerPath, IScriptProject project, IBuildpathContainer containerSuggestion) throws CoreException {

		// By default, buildpath container initializers do not accept updating containers
    }

	/**
	 * Returns a readable description for a container path. A readable description for a container path can be
	 * used for improving the display of references to container, without actually needing to resolve them.
	 * A good implementation should answer a description consistent with the description of the associated 
	 * target container (see <code>IBuildpathContainer.getDescription()</code>).
	 * 
	 * @param containerPath the path of the container which requires a readable description
	 * @param project the project from which the container is referenced
	 * @return a string description of the container
	 *
	 */    
    public String getDescription(IPath containerPath, IScriptProject project) {
    	
    	// By default, a container path is the only available description
    	return containerPath.makeRelative().toString();
    }

	/**
	 * Returns an object which identifies a container for comparison purpose. This allows
	 * to eliminate redundant containers when accumulating buildpath entries (e.g. 
	 * runtime buildpath computation). When requesting a container comparison ID, one
	 * should ensure using its corresponding container initializer. Indeed, a random container
	 * initializer cannot be held responsible for determining comparison IDs for arbitrary 
	 * containers.
	 * <p>
	 * @param containerPath the path of the container which is being checked
	 * @param project the project for which the container is to being checked
	 * @return returns an Object identifying the container for comparison
	 *
	 */
	public Object getComparisonID(IPath containerPath, IScriptProject project) {

		// By default, containers are identical if they have the same containerPath first segment,
		// but this may be refined by other container initializer implementations.
		if (containerPath == null) {
			return null;
		} else {
			return containerPath.segment(0);
		}
	}
}

