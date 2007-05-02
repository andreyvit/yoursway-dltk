/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.corext.refactoring.reorg;

import java.util.ArrayList;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.mapping.IResourceChangeDescriptionFactory;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.internal.corext.refactoring.participants.ResourceModifications;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.RefactoringParticipant;
import org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor;
import org.eclipse.ltk.core.refactoring.participants.SharableParticipants;
import org.eclipse.ltk.core.refactoring.participants.ValidateEditChecker;


public abstract class RefactoringModifications {

	private ResourceModifications fResourceModifications;

	public RefactoringModifications() {
		fResourceModifications= new ResourceModifications();
	}
	
	public ResourceModifications getResourceModifications() {
		return fResourceModifications;
	}
	
	public abstract RefactoringParticipant[] loadParticipants(RefactoringStatus status, RefactoringProcessor owner, String[] natures, SharableParticipants shared);

	public abstract void buildDelta(IResourceChangeDescriptionFactory builder);
	
	public void buildValidateEdits(ValidateEditChecker checker) {
		// Default implementation does nothing.
	}

	protected void createIncludingParents(IContainer container) {
		while (container != null && !(container.exists() || getResourceModifications().willExist(container))) {
			getResourceModifications().addCreate(container);
			container= container.getParent();
		}
	}

	protected IResource[] collectResourcesOfInterest(IScriptFolder source) throws CoreException {
		IModelElement[] children = source.getChildren();
		int childOfInterest = IModelElement.SOURCE_MODULE;		
		ArrayList result = new ArrayList(children.length);
		for (int i = 0; i < children.length; i++) {
			IModelElement child = children[i];
			if (child.getElementType() == childOfInterest && child.getResource() != null) {
				result.add(child.getResource());
			}
		}
		// Gather non-java resources
		Object[] nonScriptResources = source.getForeignResources();
		for (int i= 0; i < nonScriptResources.length; i++) {
			Object element= nonScriptResources[i];
			if (element instanceof IResource) {
				result.add(element);
			}
		}
		return (IResource[]) result.toArray(new IResource[result.size()]);
	}
	
	protected IFile getBuildpathFile(IResource resource) {
		IProject project= resource.getProject();
		if (project == null)
			return null;
		IResource result= project.findMember(".classpath"); //$NON-NLS-1$
		if (result instanceof IFile)
			return (IFile)result;
		return null;
	}
}
