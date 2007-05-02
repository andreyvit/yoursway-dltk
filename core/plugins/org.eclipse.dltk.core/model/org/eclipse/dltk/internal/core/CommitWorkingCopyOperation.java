/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.core;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.dltk.core.IBuffer;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelStatus;
import org.eclipse.dltk.core.IModelStatusConstants;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.core.util.Messages;
import org.eclipse.dltk.internal.core.util.Util;


/**
 * Commits the contents of a working copy compilation unit to its original
 * element and resource, bringing the script Model up-to-date with the current
 * contents of the working copy.
 * 
 * <p>
 * It is possible that the contents of the original resource have changed since
 * the working copy was created, in which case there is an update conflict. This
 * operation allows for two settings to resolve conflict set by the
 * <code>fForce</code> flag:
 * <ul>
 * <li>force flag is <code>false</code> - in this case an
 * <code>ModelException</code> is thrown</li>
 * <li>force flag is <code>true</code> - in this case the contents of the
 * working copy are applied to the underlying resource even though the working
 * copy was created before a subsequent change in the resource</li>
 * </ul>
 * 
 * <p>
 * The default conflict resolution setting is the force flag is
 * <code>false</code>
 * 
 * A ModelOperation exception is thrown either if the commit could not be
 * performed or if the new content of the compilation unit violates some script
 * Model constraint (e.g. if the new package declaration doesn't match the name
 * of the folder containing the compilation unit).
 */
public class CommitWorkingCopyOperation extends ModelOperation {
	/**
	 * Constructs an operation to commit the contents of a working copy to its
	 * original compilation unit.
	 */
	public CommitWorkingCopyOperation(final ISourceModule element, boolean force) {
		super(new IModelElement[] { element }, force);
	}

	/**
	 * @exception ModelException
	 *                if setting the source of the original compilation unit
	 *                fails
	 */
	protected void executeOperation() throws ModelException {
		try {
			beginTask(Messages.workingCopy_commit, 2); 
			SourceModule workingCopy = getSourceModule();
			
			if (ExternalDLTKProject.EXTERNAL_PROJECT_NAME.equals(workingCopy.getScriptProject().getElementName())) {
				// case of a working copy without a resource
				workingCopy.getBuffer().save(this.progressMonitor, this.force);
				return;
			}
			
			ISourceModule primary = workingCopy.getPrimary();
			boolean isPrimary = workingCopy.isPrimary();

			ModelElementDeltaBuilder deltaBuilder = null;
			ProjectFragment root = (ProjectFragment) workingCopy.getAncestor(IModelElement.PROJECT_FRAGMENT);
			boolean isIncluded = !Util.isExcluded(workingCopy);
			IFile resource = (IFile)workingCopy.getResource();
			if (isPrimary || (root.validateOnBuildpath().isOK() && 
				isIncluded && resource.isAccessible() &&  
				Util.isValidSourceModule(workingCopy, workingCopy.getResource()))) {
				
				// force opening so that the delta builder can get the old info
				if (!isPrimary && !primary.isOpen()) {
					primary.open(null);
				}

				// creates the delta builder (this remembers the content of the cu) if:
				// - it is not excluded
				// - and it is not a primary or it is a non-consistent primary
				if (isIncluded && (!isPrimary || !workingCopy.isConsistent())) {
					deltaBuilder = new ModelElementDeltaBuilder(primary);
				}

				// save the cu
				IBuffer primaryBuffer = primary.getBuffer();
				if (!isPrimary) {
					if (primaryBuffer == null) return;
					char[] primaryContents = primaryBuffer.getCharacters();
					boolean hasSaved = false;
					try {
						IBuffer workingCopyBuffer = workingCopy.getBuffer();
						if (workingCopyBuffer == null) return;
						primaryBuffer.setContents(workingCopyBuffer.getCharacters());
						primaryBuffer.save(this.progressMonitor, this.force);
						primary.makeConsistent(this);
						hasSaved = true;
					} finally {
						if (!hasSaved){
							// restore original buffer contents since something went wrong
							primaryBuffer.setContents(primaryContents);
						}
					}
				} else {
					// for a primary working copy no need to set the content of the buffer again
					primaryBuffer.save(this.progressMonitor, this.force);
					primary.makeConsistent(this);
				}
			} else {
				// working copy on cu outside buildpath OR resource doesn't exist yet
				String encoding = null;
				try {
					encoding = resource.getCharset();
				}
				catch (CoreException ce) {
					// use no encoding
				}
				String contents = workingCopy.getSource();
				if (contents == null) return;
				try {
					byte[] bytes = encoding == null 
						? contents.getBytes() 
						: contents.getBytes(encoding);
					ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
					if (resource.exists()) {
						resource.setContents(
							stream, 
							this.force ? IResource.FORCE | IResource.KEEP_HISTORY : IResource.KEEP_HISTORY, 
							null);
					} else {
						resource.create(stream, this.force, this.progressMonitor);
					}
				} catch (CoreException e) {
					throw new ModelException(e);
				} catch (UnsupportedEncodingException e) {
					throw new ModelException(e, IModelStatusConstants.IO_EXCEPTION);
				}

			}

			setAttribute(HAS_MODIFIED_RESOURCE_ATTR, TRUE);

			// make sure working copy is in sync
			workingCopy.updateTimeStamp((SourceModule) primary);
			workingCopy.makeConsistent(this);
			worked(1);

			// build the deltas
			if (deltaBuilder != null) {
				deltaBuilder.buildDeltas();

				// add the deltas to the list of deltas created during this
				// operation
				if (deltaBuilder.delta != null) {
					addDelta(deltaBuilder.delta);
				}
			}
			worked(1);
		} finally {
			done();
		}
	}

	/**
	 * Returns the source module this operation is working on.
	 */
	protected SourceModule getSourceModule() {
		return (SourceModule) getElementToProcess();
	}
	protected ISchedulingRule getSchedulingRule() {
		IResource resource = getElementToProcess().getResource();
		if (resource == null) return null;
		IWorkspace workspace = resource.getWorkspace();
		if (resource.exists()) {
			return workspace.getRuleFactory().modifyRule(resource);
		} else {
			return workspace.getRuleFactory().createRule(resource);
		}
	}

	/**
	 * Possible failures:
	 * <ul>
	 * <li>INVALID_ELEMENT_TYPES - the compilation unit supplied to this
	 * operation is not a working copy
	 * <li>ELEMENT_NOT_PRESENT - the compilation unit the working copy is based
	 * on no longer exists.
	 * <li>UPDATE_CONFLICT - the original compilation unit has changed since
	 * the working copy was created and the operation specifies no force
	 * <li>READ_ONLY - the original compilation unit is in read-only mode
	 * </ul>
	 */
	public IModelStatus verify() {
		SourceModule cu = getSourceModule();
		if (!cu.isWorkingCopy()) {
			return new ModelStatus(IModelStatusConstants.INVALID_ELEMENT_TYPES, cu);
		}
		if (cu.hasResourceChanged() && !this.force) {
			return new ModelStatus(IModelStatusConstants.UPDATE_CONFLICT);
		}
		// no read-only check, since some repository adapters can change the
		// flag on save
		// operation.
		return ModelStatus.VERIFIED_OK;
	}
}
