/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.core;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.internal.core.BufferManager;
import org.eclipse.dltk.internal.core.DefaultWorkingCopyOwner;
import org.eclipse.dltk.internal.core.ExternalDLTKProject;
import org.eclipse.dltk.internal.core.ScriptFolder;
import org.eclipse.dltk.internal.core.SourceModule;


/**
 * The owner of an <code>ISourceModule</code> handle in working copy mode. 
 * An owner is used to identify a working copy and to create its buffer.
 * <p>
 * Clients should subclass this class to instantiate a working copy owner that is specific to their need and that
 * they can pass in to various APIs (e.g. <code>IType.resolveType(String, WorkingCopyOwner)</code>.
 * Clients can also override the default implementation of <code>createBuffer(ISourceModule)</code>.
 * </p><p>
 * Note: even though this class has no abstract method, which means that it provides functional default behavior,
 * it is still an abstract class, as clients are intended to own their owner implementation.
 * </p>
 * @see ISourceModule#becomeWorkingCopy(IProblemRequestor, org.eclipse.core.runtime.IProgressMonitor)
 * @see ISourceModule#discardWorkingCopy()
 * @see ISourceModule#getWorkingCopy(org.eclipse.core.runtime.IProgressMonitor)
	 *
 */
public abstract class WorkingCopyOwner {
	
	/**
	 * Sets the buffer provider of the primary working copy owner. Note that even if the
	 * buffer provider is a working copy owner, only its <code>createBuffer(ISourceModule)</code>
	 * method is used by the primary working copy owner. It doesn't replace the internal primary 
	 * working owner.
 	 * <p>
	 * This method is for internal use by the dltk-related plug-ins.
	 * Clients outside of the dltk should not reference this method.
	 * </p>
	 * 
	 * @param primaryBufferProvider the primary buffer provider
	 */
	public static void setPrimaryBufferProvider(WorkingCopyOwner primaryBufferProvider) {
		DefaultWorkingCopyOwner.PRIMARY.primaryBufferProvider = primaryBufferProvider;
	}
	
	/**
	 * Creates a buffer for the given working copy.
	 * The new buffer will be initialized with the contents of the underlying file
	 * if and only if it was not already initialized by the compilation owner (a buffer is 
	 * uninitialized if its content is <code>null</code>).
	 * <p>
	 * Note: This buffer will be associated to the working copy for its entire life-cycle. Another
	 * working copy on same unit but owned by a different owner would not share the same buffer
	 * unless its owner decided to implement such a sharing behaviour.
	 * </p>
	 * 
	 * @param workingCopy the working copy of the buffer
	 * @return IBuffer the created buffer for the given working copy
	 * @see IBuffer
	 */
	public IBuffer createBuffer(ISourceModule workingCopy) {

		return BufferManager.getDefaultBufferManager().createBuffer(workingCopy);
	}
	/**
	 * Returns a new working copy with the given name using this working copy owner to 
	 * create its buffer. 
	 * <p>
	 * This working copy always belongs to the default package in a package
	 * fragment root that corresponds to its DLTK project, and this DLTK project never exists.
	 * However this DLTK project has the given buildpath that is used when resolving names
	 * in this working copy.
	 * </p><p>
	 * A DOM AST created using this working copy will have bindings resolved using the given
	 * buildpath, and problem are reported to the given problem requestor.
	 * <p></p>
	 * <code>DLTKCore#getOptions()</code> is used to create the DOM AST as it is not
	 * possible to set the options on the non-existing DLTK project.
	 * </p><p>
	 * When the working copy instance is created, an {@link IModelElementDelta#ADDED added delta} is 
	 * reported on this working copy.
	 * </p><p>
	 * Once done with the working copy, users of this method must discard it using 
	 * {@link ISourceModule#discardWorkingCopy()}.
	 * </p><p>
	 * Note that when such working copy is committed, only its buffer is saved (see 
	 * {@link IBuffer#save(IProgressMonitor, boolean)}) but no resource is created.
	 * </p><p>
	 * This method is not intended to be overriden by clients.
	 * </p>
	 * 
	 * @param name the name of the working copy (e.g. "X.java")
	 * @param buildpath the buildpath used to resolve names in this working copy
	 * @param problemRequestor a requestor which will get notified of problems detected during
	 * 	reconciling as they are discovered. The requestor can be set to <code>null</code> indicating
	 * 	that the client is not interested in problems.
	 * @param monitor a progress monitor used to report progress while opening the working copy
	 * 	or <code>null</code> if no progress should be reported 
	 * @throws ModelException if the contents of this working copy can
	 *   not be determined. 
	 * @return a new working copy
	 * @see ISourceModule#becomeWorkingCopy(IProblemRequestor, IProgressMonitor)
	 *
	 */
	public final ISourceModule newWorkingCopy(String name, IBuildpathEntry[] buildpath, IProblemRequestor problemRequestor, IProgressMonitor monitor) throws ModelException {
		ExternalDLTKProject project = new ExternalDLTKProject(buildpath);
		IProjectFragment fragment = project.getProjectFragment(Path.EMPTY);
		IScriptFolder parent = fragment.getScriptFolder(IProjectFragment.DEFAULT_SCRIPT_FOLDER_NAME);
		SourceModule result = new SourceModule((ScriptFolder) parent, name, this);
		result.becomeWorkingCopy(problemRequestor, monitor);
		return result;
	}

}
