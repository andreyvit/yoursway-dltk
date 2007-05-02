/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.corext.refactoring.changes;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.internal.corext.refactoring.AbstractModelElementRenameChange;
import org.eclipse.dltk.internal.corext.refactoring.RefactoringCoreMessages;
import org.eclipse.dltk.internal.corext.util.Messages;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringDescriptor;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;


public final class RenameSourceFolderChange extends AbstractModelElementRenameChange {

	private static RefactoringStatus checkIfModifiable(IProjectFragment root, IProgressMonitor pm) throws CoreException {
		RefactoringStatus result= new RefactoringStatus();
		checkExistence(result, root);
		if (result.hasFatalError())
			return result;

		if (root.isArchive()) {
			result.addFatalError(Messages.format(RefactoringCoreMessages.RenameSourceFolderChange_rename_archive, root.getElementName()));
			return result;
		}

		if (root.isExternal()) {
			result.addFatalError(Messages.format(RefactoringCoreMessages.RenameSourceFolderChange_rename_external, root.getElementName()));
			return result;
		}

		checkExistence(result, root.getCorrespondingResource());
		if (result.hasFatalError())
			return result;

		if (root.getCorrespondingResource().isLinked()) {
			result.addFatalError(Messages.format(RefactoringCoreMessages.RenameSourceFolderChange_rename_linked, root.getElementName()));
			return result;
		}

		return result;
	}

	public RenameSourceFolderChange(RefactoringDescriptor descriptor, IProjectFragment sourceFolder, String newName, String comment) {
		this(descriptor, sourceFolder.getPath(), sourceFolder.getElementName(), newName, comment, IResource.NULL_STAMP);
		Assert.isTrue(!sourceFolder.isReadOnly(), "should not be read only"); //$NON-NLS-1$
		Assert.isTrue(!sourceFolder.isArchive(), "should not be an archive"); //$NON-NLS-1$
	}

	private RenameSourceFolderChange(RefactoringDescriptor descriptor, IPath resourcePath, String oldName, String newName, String comment, long stampToRestore) {
		super(descriptor, resourcePath, oldName, newName, comment);
	}

	protected IPath createNewPath() {
		return getResourcePath().removeLastSegments(1).append(getNewName());
	}

	protected Change createUndoChange(long stampToRestore) {
		return new RenameSourceFolderChange(null, createNewPath(), getNewName(), getOldName(), getComment(), stampToRestore);
	}

	protected void doRename(IProgressMonitor pm) throws CoreException {
		IProjectFragment sourceFolder= getSourceFolder();
		if (sourceFolder != null)
			sourceFolder.move(getNewPath(), getCoreMoveFlags(), getScriptModelUpdateFlags(), null, pm);
	}

	private int getCoreMoveFlags() {
		if (getResource().isLinked())
			return IResource.SHALLOW;
		else
			return IResource.NONE;
	}

	private int getScriptModelUpdateFlags() {
		return IProjectFragment.DESTINATION_PROJECT_BUILDPATH | IProjectFragment.ORIGINATING_PROJECT_BUILDPATH | IProjectFragment.OTHER_REFERRING_PROJECTS_BUILDPATH | IProjectFragment.REPLACE;
	}

	public String getName() {
		return Messages.format(RefactoringCoreMessages.RenameSourceFolderChange_rename, new String[] { getOldName(), getNewName()});
	}

	private IPath getNewPath() {
		return getResource().getFullPath().removeLastSegments(1).append(getNewName());
	}

	private IProjectFragment getSourceFolder() {
		return (IProjectFragment) getModifiedElement();
	}

	public RefactoringStatus isValid(IProgressMonitor pm) throws CoreException {
		RefactoringStatus result= new RefactoringStatus();
		pm.beginTask("", 2); //$NON-NLS-1$
		result.merge(isValid(new SubProgressMonitor(pm, 1), DIRTY));
		if (result.hasFatalError())
			return result;
		IProjectFragment sourceFolder= getSourceFolder();
		result.merge(checkIfModifiable(sourceFolder, new SubProgressMonitor(pm, 1)));

		return result;
	}
}
