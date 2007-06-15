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
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.corext.refactoring.RefactoringCoreMessages;
import org.eclipse.dltk.internal.corext.refactoring.reorg.IProjectFragmentManipulationQuery;
import org.eclipse.dltk.internal.corext.refactoring.util.ModelElementUtil;
import org.eclipse.dltk.internal.corext.util.Messages;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;


public class DeleteProjectFragmentChange extends AbstractDeleteChange {
	
	private final String fHandle;
	private final boolean fIsExecuteChange;
	private final IProjectFragmentManipulationQuery fUpdateClasspathQuery;

	public DeleteProjectFragmentChange(IProjectFragment root, boolean isExecuteChange, 
			IProjectFragmentManipulationQuery updateClasspathQuery) {
		Assert.isNotNull(root);
		Assert.isTrue(! root.isExternal());
		fHandle= root.getHandleIdentifier();
		fIsExecuteChange= isExecuteChange;
		fUpdateClasspathQuery= updateClasspathQuery;
	}

	public String getName() {
		String[] keys= {getRoot().getElementName()};
		return Messages.format(RefactoringCoreMessages.DeleteProjectFragmentChange_delete, keys); 
	}

	public Object getModifiedElement() {
		return getRoot();
	}
	
	private IProjectFragment getRoot(){
		return (IProjectFragment)DLTKCore.create(fHandle);
	}
	
	public RefactoringStatus isValid(IProgressMonitor pm) throws CoreException {
		if (fIsExecuteChange) {
			// don't check for read-only resources since we already
			// prompt the user via a dialog to confirm deletion of
			// read only resource. The change is currently not used
			// as 
			return super.isValid(pm, DIRTY);
		} else {
			return super.isValid(pm, READ_ONLY | DIRTY);
		}
	}

	protected void doDelete(IProgressMonitor pm) throws CoreException {
		if (! confirmDeleteIfReferenced())
			return;
		int resourceUpdateFlags= IResource.KEEP_HISTORY;
		int jCoreUpdateFlags= IProjectFragment.ORIGINATING_PROJECT_BUILDPATH | IProjectFragment.OTHER_REFERRING_PROJECTS_BUILDPATH;
		getRoot().delete(resourceUpdateFlags, jCoreUpdateFlags, pm);
	}

	private boolean confirmDeleteIfReferenced() throws ModelException {		
		if (! getRoot().isArchive()) //for source folders, you don't ask, just do it
			return true;
		if (fUpdateClasspathQuery == null)
			return true;
		IScriptProject[] referencingProjects= ModelElementUtil.getReferencingProjects(getRoot());
		if (referencingProjects.length == 0)
			return true;
		return fUpdateClasspathQuery.confirmManipulation(getRoot(), referencingProjects);
	}
}
