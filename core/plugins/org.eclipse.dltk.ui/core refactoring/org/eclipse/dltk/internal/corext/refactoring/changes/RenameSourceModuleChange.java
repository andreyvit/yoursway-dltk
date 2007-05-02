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
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.corext.refactoring.AbstractModelElementRenameChange;
import org.eclipse.dltk.internal.corext.refactoring.RefactoringCoreMessages;
import org.eclipse.dltk.internal.corext.refactoring.util.ResourceUtil;
import org.eclipse.dltk.internal.corext.util.Messages;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringDescriptor;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;


public final class RenameSourceModuleChange extends AbstractModelElementRenameChange {

	public RenameSourceModuleChange(RefactoringDescriptor descriptor, ISourceModule unit, String newName, String comment) {
		this(descriptor, ResourceUtil.getResource(unit).getFullPath(), unit.getElementName(), newName, comment, IResource.NULL_STAMP);
		Assert.isTrue(!unit.isReadOnly(), "compilation unit must not be read-only"); //$NON-NLS-1$
	}

	private RenameSourceModuleChange(RefactoringDescriptor descriptor, IPath resourcePath, String oldName, String newName, String comment, long stampToRestore) {
		super(descriptor, resourcePath, oldName, newName, comment, stampToRestore);
	}

	protected IPath createNewPath() {
		if (getResourcePath().getFileExtension() != null)
			return getResourcePath().removeFileExtension().removeLastSegments(1).append(getNewName());
		else
			return getResourcePath().removeLastSegments(1).append(getNewName());
	}

	protected Change createUndoChange(long stampToRestore) throws ModelException {
		return new RenameSourceModuleChange(null, createNewPath(), getNewName(), getOldName(), getComment(), stampToRestore);
	}

	protected void doRename(IProgressMonitor pm) throws CoreException {
		ISourceModule cu= (ISourceModule) getModifiedElement();
		if (cu != null)
			cu.rename(getNewName(), false, pm);
	}

	public String getName() {
		return Messages.format(RefactoringCoreMessages.RenameSourceModuleChange_name, new String[] { getOldName(), getNewName()});
	}

	public RefactoringStatus isValid(IProgressMonitor pm) throws CoreException {
		return super.isValid(pm, READ_ONLY | SAVE_IF_DIRTY);
	}
}
