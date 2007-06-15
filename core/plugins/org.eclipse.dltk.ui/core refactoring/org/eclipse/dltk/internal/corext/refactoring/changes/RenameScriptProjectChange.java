/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.corext.refactoring.changes;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.corext.refactoring.AbstractModelElementRenameChange;
import org.eclipse.dltk.internal.corext.refactoring.RefactoringCoreMessages;
import org.eclipse.dltk.internal.corext.util.Messages;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringDescriptor;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;


public final class RenameScriptProjectChange extends AbstractModelElementRenameChange {

	private boolean fUpdateReferences;

	public RenameScriptProjectChange(RefactoringDescriptor descriptor, IScriptProject project, String newName, String comment, boolean updateReferences) {
		this(descriptor, project.getPath(), project.getElementName(), newName, comment, IResource.NULL_STAMP, updateReferences);
		Assert.isTrue(!project.isReadOnly(), "should not be read only"); //$NON-NLS-1$
	}

	private RenameScriptProjectChange(RefactoringDescriptor descriptor, IPath resourcePath, String oldName, String newName, String comment, long stampToRestore, boolean updateReferences) {
		super(descriptor, resourcePath, oldName, newName, comment);
		fUpdateReferences= updateReferences;
	}

	private IBuildpathEntry createModifiedEntry(IBuildpathEntry oldEntry) {
		return DLTKCore.newProjectEntry(createNewPath(), oldEntry.getAccessRules(), oldEntry.combineAccessRules(), oldEntry.getExtraAttributes(), oldEntry.isExported());
	}

	protected IPath createNewPath() {
		return getResourcePath().removeLastSegments(1).append(getNewName());
	}

	protected Change createUndoChange(long stampToRestore) throws ModelException {
		return new RenameScriptProjectChange(null, createNewPath(), getNewName(), getOldName(), getComment(), stampToRestore, fUpdateReferences);
	}

	protected void doRename(IProgressMonitor pm) throws CoreException {
		try {
			pm.beginTask(getName(), 2);
			if (fUpdateReferences)
				modifyBuildpaths(new SubProgressMonitor(pm, 1));
			IProject project= getProject();
			if (project != null) {
				IProjectDescription description= project.getDescription();
				description.setName(getNewName());
				project.move(description, IResource.FORCE | IResource.SHALLOW, new SubProgressMonitor(pm, 1));
			}
		} finally {
			pm.done();
		}
	}

	private IScriptProject getScriptProject() {
		return (IScriptProject) getModifiedElement();
	}

	public String getName() {
		return Messages.format(RefactoringCoreMessages.RenameScriptProjectChange_rename, new String[] { getOldName(), getNewName()});
	}

	private IProject getProject() {
		IScriptProject jp= getScriptProject();
		if (jp == null)
			return null;
		return jp.getProject();
	}

	private boolean isOurEntry(IBuildpathEntry cpe) {
		if (cpe.getEntryKind() != IBuildpathEntry.BPE_PROJECT)
			return false;
		if (!cpe.getPath().equals(getResourcePath()))
			return false;
		return true;
	}

	public RefactoringStatus isValid(IProgressMonitor pm) throws CoreException {
		return isValid(pm, DIRTY);
	}

	private void modifyBuildpath(IScriptProject referencingProject, IProgressMonitor pm) throws ModelException {
		pm.beginTask("", 1); //$NON-NLS-1$
		IBuildpathEntry[] oldEntries= referencingProject.getRawBuildpath();
		IBuildpathEntry[] newEntries= new IBuildpathEntry[oldEntries.length];
		for (int i= 0; i < newEntries.length; i++) {
			if (isOurEntry(oldEntries[i]))
				newEntries[i]= createModifiedEntry(oldEntries[i]);
			else
				newEntries[i]= oldEntries[i];
		}
		referencingProject.setRawBuildpath(newEntries, pm);
		pm.done();
	}

	private void modifyBuildpaths(IProgressMonitor pm) throws ModelException {
		IProject[] referencing= getProject().getReferencingProjects();
		pm.beginTask(RefactoringCoreMessages.RenameScriptProjectChange_update, referencing.length);
		for (int i= 0; i < referencing.length; i++) {
			IScriptProject jp= DLTKCore.create(referencing[i]);
			if (jp != null && jp.exists()) {
				modifyBuildpath(jp, new SubProgressMonitor(pm, 1));
			} else {
				pm.worked(1);
			}
		}
		pm.done();
	}
}
