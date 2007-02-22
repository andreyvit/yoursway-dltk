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
package org.eclipse.dltk.internal.corext.refactoring.changes;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.mapping.ResourceMapping;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.corext.refactoring.base.DLTKChange;
import org.eclipse.dltk.internal.corext.refactoring.reorg.INewNameQuery;
import org.eclipse.dltk.internal.corext.refactoring.reorg.IProjectFragmentManipulationQuery;
import org.eclipse.dltk.internal.corext.refactoring.util.ModelElementUtil;
import org.eclipse.dltk.internal.ui.model.DLTKElementResourceMapping;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.ReorgExecutionLog;


abstract class ProjectFragmentReorgChange extends DLTKChange {

	private final String fRootHandle;
	private final IPath fDestinationPath;
	private final INewNameQuery fNewNameQuery;
	private final IProjectFragmentManipulationQuery fUpdateBuildpathQuery;
	
	ProjectFragmentReorgChange(IProjectFragment root, IProject destination, INewNameQuery newNameQuery, 
			IProjectFragmentManipulationQuery updateBuildpathQuery) {
		Assert.isTrue(! root.isExternal());
		fRootHandle= root.getHandleIdentifier();
		fDestinationPath= Utils.getResourcePath(destination);
		fNewNameQuery= newNameQuery;
		fUpdateBuildpathQuery= updateBuildpathQuery;
	}

	public RefactoringStatus isValid(IProgressMonitor pm) throws CoreException {
		// we already ask for confirmation of move read only
		// resources. Furthermore we don't do a validate
		// edit since move source folders doesn't change
		// an content
		return isValid(pm, NONE);
	}
	
	public final Change perform(IProgressMonitor pm) throws CoreException {
		pm.beginTask(getName(), 2);
		try {
			String newName= getNewResourceName();
			IProjectFragment root= getRoot();
			ResourceMapping mapping= DLTKElementResourceMapping.create(root);
			final Change result= doPerformReorg(getDestinationProjectPath().append(newName), new SubProgressMonitor(pm, 1));
			markAsExecuted(root, mapping);
			return result;
		} finally {
			pm.done();
		}
	}

	protected abstract Change doPerformReorg(IPath destinationPath, IProgressMonitor pm) throws ModelException;

	public Object getModifiedElement() {
		return getRoot();
	}
	
	protected IProjectFragment getRoot(){
		return (IProjectFragment)DLTKCore.create(fRootHandle);
	}
	
	protected IPath getDestinationProjectPath(){
		return fDestinationPath;
	}

	protected IProject getDestinationProject(){
		return Utils.getProject(getDestinationProjectPath());
	}
	
	private String getNewResourceName(){
		if (fNewNameQuery == null)
			return getRoot().getElementName();
		String name= fNewNameQuery.getNewName();
		if (name == null)
			return getRoot().getElementName();
		return name;
	}
	
	protected int getUpdateModelFlags(boolean isCopy) throws ModelException{
		final int destination= IProjectFragment.DESTINATION_PROJECT_BUILDPATH;
		final int replace= IProjectFragment.REPLACE;
		final int originating;
		final int otherProjects;
		if (isCopy){
			originating= 0; //ORIGINATING_PROJECT_CLASSPATH does not apply to copy
			otherProjects= 0;//OTHER_REFERRING_PROJECTS_CLASSPATH does not apply to copy
		} else{
			originating= IProjectFragment.ORIGINATING_PROJECT_BUILDPATH;
			otherProjects= IProjectFragment.OTHER_REFERRING_PROJECTS_BUILDPATH;
		}
		
		if (! DLTKCore.create(getDestinationProject()).exists())
			return replace | originating;

		if (fUpdateBuildpathQuery == null)
			return replace | originating | destination;

		IDLTKProject[] referencingProjects= ModelElementUtil.getReferencingProjects(getRoot());
		if (referencingProjects.length == 0)
			return replace | originating | destination;

		boolean updateOtherProjectsToo= fUpdateBuildpathQuery.confirmManipulation(getRoot(), referencingProjects);	
		if (updateOtherProjectsToo)
			return replace | originating | destination | otherProjects;
		else
			return replace | originating | destination;
	}
	
	protected int getResourceUpdateFlags(){
		return IResource.KEEP_HISTORY | IResource.SHALLOW;
	}
	
	private void markAsExecuted(IProjectFragment root, ResourceMapping mapping) {
		ReorgExecutionLog log= (ReorgExecutionLog)getAdapter(ReorgExecutionLog.class);
		if (log != null) {
			log.markAsProcessed(root);
			log.markAsProcessed(mapping);
		}
	}
}
