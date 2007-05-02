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
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.mapping.IResourceChangeDescriptionFactory;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.internal.corext.refactoring.participants.ResourceModifications;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.IParticipantDescriptorFilter;
import org.eclipse.ltk.core.refactoring.participants.MoveArguments;
import org.eclipse.ltk.core.refactoring.participants.ParticipantManager;
import org.eclipse.ltk.core.refactoring.participants.RefactoringArguments;
import org.eclipse.ltk.core.refactoring.participants.RefactoringParticipant;
import org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor;
import org.eclipse.ltk.core.refactoring.participants.SharableParticipants;
import org.eclipse.ltk.core.refactoring.participants.ValidateEditChecker;


public class MoveModifications extends RefactoringModifications {
	
	private List fMoves;
	private List fMoveArguments;
	private List fParticipantDescriptorFilter;
	
	public MoveModifications() {
		fMoves= new ArrayList();
		fMoveArguments= new ArrayList();
		fParticipantDescriptorFilter= new ArrayList();
	}
	
	public void move(IResource resource, MoveArguments args) {
		add(resource, args, null);
	}

	public void move(IProjectFragment sourceFolder, MoveArguments arguments) {
		add(sourceFolder, arguments, null);
		IResource sourceResource= sourceFolder.getResource();
		if (sourceResource != null) {
			getResourceModifications().addMove(sourceResource, 
				new MoveArguments(getResourceDestination(arguments), arguments.getUpdateReferences()));
			IFile buildpath= getBuildpathFile(sourceResource);
			if (buildpath != null) {
				getResourceModifications().addChanged(buildpath);
			}
			buildpath= getBuildpathFile(getResourceDestination(arguments));
			if (buildpath != null) {
				getResourceModifications().addChanged(buildpath);
			}
		}
	}
	
	public void move(IScriptFolder pack, MoveArguments args) throws CoreException {
		add(pack, args, null);
		if (pack.getResource() == null)
			return;
		IProjectFragment scriptDestination= (IProjectFragment) args.getDestination();
		if (scriptDestination.getResource() == null)
			return;
		IScriptFolder newPack= scriptDestination.getScriptFolder(pack.getElementName());
		if (!pack.hasSubfolders() && !newPack.exists()) {
			// we can do a simple move
			IContainer resourceDestination= newPack.getResource().getParent();
			createIncludingParents(resourceDestination);
			getResourceModifications().addMove(
				pack.getResource(), 
				new MoveArguments(resourceDestination, args.getUpdateReferences()));
		} else {
			IContainer resourceSource= (IContainer)pack.getResource();
			IContainer resourceDestination= (IContainer) newPack.getResource();
			createIncludingParents(resourceDestination);
			MoveArguments arguments= new MoveArguments(resourceDestination, args.getUpdateReferences());
			IResource[] resourcesToMove= collectResourcesOfInterest(pack);
			Set allMembers= new HashSet(Arrays.asList(resourceSource.members()));
			for (int i= 0; i < resourcesToMove.length; i++) {
				IResource toMove= resourcesToMove[i];
				getResourceModifications().addMove(toMove, arguments);
				allMembers.remove(toMove);
			}
			for (Iterator iter= allMembers.iterator(); iter.hasNext();) {
				IResource element= (IResource) iter.next();
				if (element instanceof IFile) {
					getResourceModifications().addDelete(element);
					iter.remove();
				}
			}
			if (allMembers.isEmpty()) {
				getResourceModifications().addDelete(resourceSource);
			}
		}
	}

	public void move(ISourceModule unit, MoveArguments args) throws CoreException {
		add(unit, args, null);
		IType[] types= unit.getTypes();
		for (int tt= 0; tt < types.length; tt++) {
			add(types[tt], args, null);
		}
		IResource resourceDestination= getResourceDestination(args);
		if (resourceDestination != null && unit.getResource() != null) {
			getResourceModifications().addMove(unit.getResource(), new MoveArguments(resourceDestination, args.getUpdateReferences()));
		}
	}

	public void buildDelta(IResourceChangeDescriptionFactory builder) {
		for (int i= 0; i < fMoves.size(); i++) {
			Object element= fMoves.get(i);
			if (element instanceof IResource) {
				ResourceModifications.buildMoveDelta(builder, (IResource) element, (MoveArguments) fMoveArguments.get(i));
			}
		}
		getResourceModifications().buildDelta(builder);
	}
	
	public void buildValidateEdits(ValidateEditChecker checker) {
		for (Iterator iter= fMoves.iterator(); iter.hasNext();) {
			Object element= iter.next();
			if (element instanceof ISourceModule) {
				ISourceModule unit= (ISourceModule)element;
				IResource resource= unit.getResource();
				if (resource != null && resource.getType() == IResource.FILE) {
					checker.addFile((IFile)resource);
				}
			}
		}
	}

	public RefactoringParticipant[] loadParticipants(RefactoringStatus status, RefactoringProcessor owner, String[] natures, SharableParticipants shared) {
		List result= new ArrayList();
		for (int i= 0; i < fMoves.size(); i++) {
			result.addAll(Arrays.asList(ParticipantManager.loadMoveParticipants(status, 
				owner, fMoves.get(i), 
				(MoveArguments) fMoveArguments.get(i), 
				(IParticipantDescriptorFilter) fParticipantDescriptorFilter.get(i), 
				natures, shared)));
		}
		result.addAll(Arrays.asList(getResourceModifications().getParticipants(status, owner, natures, shared)));
		return (RefactoringParticipant[]) result.toArray(new RefactoringParticipant[result.size()]);
	}
	
	private void add(Object element, RefactoringArguments args, IParticipantDescriptorFilter filter) {
		Assert.isNotNull(element);
		Assert.isNotNull(args);
		fMoves.add(element);
		fMoveArguments.add(args);
		fParticipantDescriptorFilter.add(filter);
	}
	
	private IResource getResourceDestination(MoveArguments args) {
		Object genericDestination= args.getDestination();
		IResource resourceDestination= null;
		if (genericDestination instanceof IModelElement) {
			resourceDestination= ((IModelElement)genericDestination).getResource();
		} else if (genericDestination instanceof IResource) {
			resourceDestination= (IResource)genericDestination;
		}
		return resourceDestination;
	}
} 
