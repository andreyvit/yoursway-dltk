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
package org.eclipse.dltk.internal.corext.refactoring.rename;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.mapping.IResourceChangeDescriptionFactory;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.internal.corext.refactoring.participants.ResourceModifications;
import org.eclipse.dltk.internal.corext.refactoring.reorg.RefactoringModifications;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.IParticipantDescriptorFilter;
import org.eclipse.ltk.core.refactoring.participants.MoveArguments;
import org.eclipse.ltk.core.refactoring.participants.ParticipantManager;
import org.eclipse.ltk.core.refactoring.participants.RefactoringArguments;
import org.eclipse.ltk.core.refactoring.participants.RefactoringParticipant;
import org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor;
import org.eclipse.ltk.core.refactoring.participants.RenameArguments;
import org.eclipse.ltk.core.refactoring.participants.SharableParticipants;
import org.eclipse.ltk.core.refactoring.participants.ValidateEditChecker;


public class RenameModifications extends RefactoringModifications {
	
	private List fRename;
	private List fRenameArguments;
	private List fParticipantDescriptorFilter;
	
	public RenameModifications() {
		fRename= new ArrayList();
		fRenameArguments= new ArrayList();
		fParticipantDescriptorFilter= new ArrayList();
	}
	
	public void rename(IResource resource, RenameArguments args) {
		add(resource, args, null);
	}

	public void rename(IDLTKProject project, RenameArguments args) {
		add(project, args, null);
		IProject rProject= project.getProject();
		if (rProject != null) {
			getResourceModifications().addRename(rProject, args);
			IProject[] referencingProjects= rProject.getReferencingProjects();
			for (int i= 0; i < referencingProjects.length; i++) {
				IFile buildpath= getBuildpathFile(referencingProjects[i]);
				if (buildpath != null) {
					getResourceModifications().addChanged(buildpath);
				}
			}
		}
	}

	public void rename(IProjectFragment sourceFolder, RenameArguments arguments) {
		add(sourceFolder, arguments, null);
		if (sourceFolder.getResource() != null) {
			getResourceModifications().addRename(sourceFolder.getResource(), arguments);
		}
	}
	
	public void rename(IScriptFolder rootPackage, RenameArguments args, boolean renameSubPackages) throws CoreException {
		add(rootPackage, args, null);
		IScriptFolder[] allSubPackages= null;
		if (renameSubPackages) {
			allSubPackages= getSubpackages(rootPackage);
			for (int i= 0; i < allSubPackages.length; i++) {
				IScriptFolder pack= allSubPackages[i];
				RenameArguments subArgs= new RenameArguments(
					getNewPackageName(rootPackage, args.getNewName(), pack.getElementName()), 
					args.getUpdateReferences());
				add(pack, subArgs, null);
			}
		}
		IContainer container= (IContainer)rootPackage.getResource();
		if (container == null)
			return;
		IContainer target= (IContainer) ((IProjectFragment)rootPackage.getParent()).
			getScriptFolder(args.getNewName()).getResource();
		if ((!rootPackage.hasSubfolders() || renameSubPackages) && canMove(container, target)) {
			createIncludingParents(target.getParent());
			if (container.getParent().equals(target.getParent())) {
				getResourceModifications().addRename(container, new RenameArguments(target.getName(), args.getUpdateReferences()));
			} else {
				// This is a little tricky. The problem is that the refactoring participants
				// don't support a generic move like the resource API does. So for the delta
				// we generate one move, however for the participants we have to generate single
				// moves and deletes.
				try {
					getResourceModifications().ignoreForDelta();
					addAllResourceModifications(rootPackage, args, renameSubPackages, allSubPackages);
				} finally {
					getResourceModifications().trackForDelta();
				}
				getResourceModifications().addDelta(new ResourceModifications.MoveDescription(container, target.getFullPath()));
			}
		} else {
			addAllResourceModifications(rootPackage, args, renameSubPackages, allSubPackages);
		}
	}

	public void rename(ISourceModule unit, RenameArguments args) {
		add(unit, args, null);
		if (unit.getResource() != null) {
			getResourceModifications().addRename(unit.getResource(), new RenameArguments(args.getNewName(), args.getUpdateReferences()));
		}
	}	
	
	public void rename(IField field, RenameArguments args) {
		add(field, args, null);
	}
	
	public void rename(IMethod method, RenameArguments args) {
		add(method, args, null);
	} 		
	
	public void buildDelta(IResourceChangeDescriptionFactory builder) {
		for (int i= 0; i < fRename.size(); i++) {
			Object element= fRename.get(i);
			if (element instanceof IResource) {
				ResourceModifications.buildMoveDelta(builder, (IResource) element, (RenameArguments) fRenameArguments.get(i));
			}
		}
		getResourceModifications().buildDelta(builder);
	}
	
	public void buildValidateEdits(ValidateEditChecker checker) {
		for (Iterator iter= fRename.iterator(); iter.hasNext();) {
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
		for (int i= 0; i < fRename.size(); i++) {
			result.addAll(Arrays.asList(ParticipantManager.loadRenameParticipants(status, 
				owner, fRename.get(i), 
				(RenameArguments) fRenameArguments.get(i), 
				(IParticipantDescriptorFilter) fParticipantDescriptorFilter.get(i), 
				natures, shared)));
		}
		result.addAll(Arrays.asList(getResourceModifications().getParticipants(status, owner, natures, shared)));
		return (RefactoringParticipant[]) result.toArray(new RefactoringParticipant[result.size()]);
	}
	
	private void add(Object element, RefactoringArguments args, IParticipantDescriptorFilter filter) {
		Assert.isNotNull(element);
		Assert.isNotNull(args);
		fRename.add(element);
		fRenameArguments.add(args);
		fParticipantDescriptorFilter.add(filter);
	}
	
	private void addAllResourceModifications(IScriptFolder rootPackage, RenameArguments args, boolean renameSubPackages, IScriptFolder[] allSubPackages) throws CoreException {
		addResourceModifications(rootPackage, args, rootPackage, renameSubPackages);
		if (renameSubPackages) {
			for (int i= 0; i < allSubPackages.length; i++) {
				IScriptFolder pack= allSubPackages[i];
				addResourceModifications(rootPackage, args, pack, renameSubPackages);
			}
		}
	}
	
	private void addResourceModifications(IScriptFolder rootPackage, RenameArguments args, IScriptFolder pack, boolean renameSubPackages) throws CoreException {
		IContainer container= (IContainer)pack.getResource();
		if (container == null)
			return;
		IFolder target= computeTargetFolder(rootPackage, args, pack);
		createIncludingParents(target);
		MoveArguments arguments= new MoveArguments(target, args.getUpdateReferences());
		IResource[] resourcesToMove= collectResourcesOfInterest(pack);
		Set allMembers= new HashSet(Arrays.asList(container.members()));
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
		if (renameSubPackages && rootPackage.equals(pack)
				|| ! renameSubPackages && allMembers.isEmpty()) {
			getResourceModifications().addDelete(container);
		}
	}

	private boolean canMove(IContainer source, IContainer target) {
		return !target.exists() && !source.getFullPath().isPrefixOf(target.getFullPath());
	}

	private IScriptFolder[] getSubpackages(IScriptFolder pack) throws CoreException {
		IProjectFragment root= (IProjectFragment) pack.getParent();
		IModelElement[] allPackages= root.getChildren();
		if (pack.isRootFolder())
			return new IScriptFolder[0];
		ArrayList result= new ArrayList();
		String prefix= pack.getElementName() + IScriptFolder.PACKAGE_DELIMITER;
		for (int i= 0; i < allPackages.length; i++) {
			IScriptFolder currentPackage= (IScriptFolder) allPackages[i];
			if (currentPackage.getElementName().startsWith(prefix))
				result.add(currentPackage);
		}
		return (IScriptFolder[]) result.toArray(new IScriptFolder[result.size()]);
	}
	
	private IFolder computeTargetFolder(IScriptFolder rootPackage, RenameArguments args, IScriptFolder pack) {
		IPath path= pack.getParent().getPath();
		path= path.append(getNewPackageName(rootPackage, args.getNewName(),  pack.getElementName()).replace('.', IPath.SEPARATOR));
		IFolder target= ResourcesPlugin.getWorkspace().getRoot().getFolder(path);
		return target;
	}
	
	private String getNewPackageName(IScriptFolder rootPackage, String newPackageName, String oldSubPackageName) {
		String oldPackageName= rootPackage.getElementName();
		return newPackageName + oldSubPackageName.substring(oldPackageName.length());
	}
} 
