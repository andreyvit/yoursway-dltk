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
package org.eclipse.dltk.internal.corext.refactoring;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.internal.corext.refactoring.base.DLTKChange;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.ChangeDescriptor;
import org.eclipse.ltk.core.refactoring.RefactoringChangeDescriptor;
import org.eclipse.ltk.core.refactoring.RefactoringDescriptor;


public abstract class AbstractModelElementRenameChange extends DLTKChange {

	private final String fNewName;
	private final String fOldName;
	private final IPath fResourcePath;
	private final long fStampToRestore;
	private final String fComment;
	private final RefactoringDescriptor fDescriptor;

	protected AbstractModelElementRenameChange(RefactoringDescriptor descriptor, IPath resourcePath, String oldName, String newName, String comment) {
		this(descriptor, resourcePath, oldName, newName, comment, IResource.NULL_STAMP);
	}

	protected AbstractModelElementRenameChange(RefactoringDescriptor descriptor, IPath resourcePath, String oldName, String newName, String comment, long stampToRestore) {
		Assert.isNotNull(newName, "new name"); //$NON-NLS-1$
		Assert.isNotNull(oldName, "old name"); //$NON-NLS-1$
		fDescriptor= descriptor;
		fResourcePath= resourcePath;
		fOldName= oldName;
		fNewName= newName;
		fComment= comment;
		fStampToRestore= stampToRestore;
	}

	protected final IResource getResource() {
		return ResourcesPlugin.getWorkspace().getRoot().findMember(fResourcePath);
	}

	public Object getModifiedElement() {
		return DLTKCore.create(getResource());
	}

	protected abstract Change createUndoChange(long stampToRestore) throws CoreException;

	protected abstract void doRename(IProgressMonitor pm) throws CoreException;
	
	protected abstract IPath createNewPath();

	public final Change perform(IProgressMonitor pm) throws CoreException {
		try {
			pm.beginTask(RefactoringCoreMessages.AbstractRenameChange_Renaming, 1); 
			IResource resource= getResource();
			IPath newPath= createNewPath();
			Change result= createUndoChange(resource.getModificationStamp());
			doRename(new SubProgressMonitor(pm, 1));
			if (fStampToRestore != IResource.NULL_STAMP) {
				IResource newResource= ResourcesPlugin.getWorkspace().getRoot().findMember(newPath);
				newResource.revertModificationStamp(fStampToRestore);
			}
			return result;
		} finally {
			pm.done();
		}
	}

	public String getNewName() {
		return fNewName;
	}

	public String getComment() {
		return fComment;
	}

	protected IPath getResourcePath() {
		return fResourcePath;
	}

	public String getOldName() {
		return fOldName;
	}

	public final ChangeDescriptor getDescriptor() {
		if (fDescriptor != null)
			return new RefactoringChangeDescriptor(fDescriptor);
		return null;
	}
}
