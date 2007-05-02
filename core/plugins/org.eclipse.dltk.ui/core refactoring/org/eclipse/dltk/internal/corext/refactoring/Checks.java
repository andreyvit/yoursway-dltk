/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.corext.refactoring;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKModelUtil;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.corext.refactoring.changes.RenameResourceChange;
import org.eclipse.dltk.internal.corext.refactoring.util.ResourceUtil;
import org.eclipse.dltk.internal.corext.util.Messages;
import org.eclipse.dltk.internal.corext.util.Resources;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;



/**
 * This class defines a set of reusable static checks methods.
 */
public class Checks {
	
	/*
	 * no instances
	 */
	private Checks(){
	}
	
	/* Constants returned by checkExpressionIsRValue */
	public static final int IS_RVALUE= 0;
	public static final int NOT_RVALUE_MISC= 1;
	public static final int NOT_RVALUE_VOID= 2;
	
	public static boolean isAvailable(IModelElement modelElement) throws ModelException {
		if (modelElement == null)
			return false;
		if (! modelElement.exists())
			return false;
		if (modelElement.isReadOnly())
			return false;
		// work around for https://bugs.eclipse.org/bugs/show_bug.cgi?id=48422
		// the Script project is now cheating regarding its children so we shouldn't
		// call isStructureKnown if the project isn't open.
		// see bug https://bugs.eclipse.org/bugs/show_bug.cgi?id=52474
		if (!(modelElement instanceof IDLTKProject) && !modelElement.isStructureKnown())
			return false;
		if (DLTKCore.DEBUG) {
			System.err.println("Add binary modules support.");
		}
//		if (modelElement instanceof IMember && ((IMember)modelElement).isBinary())
//			return false;
		return true;
	}
	public static boolean isBuildpathDelete(IProjectFragment pkgRoot) {
		IResource res= pkgRoot.getResource();
		if (res == null)
			return true;
		IProject definingProject= res.getProject();
		if (res.getParent() != null && pkgRoot.isArchive() && !res.getParent().equals(definingProject))
			return true;
		
		IProject occurringProject= pkgRoot.getScriptProject().getProject();
		return !definingProject.equals(occurringProject);
	}
	public static RefactoringStatus checkSourceModuleNewName(ISourceModule cu, String newName) {
		String newCUName= DLTKModelUtil.getRenamedCUName(cu, newName);
		if (resourceExists(RenameResourceChange.renamedResourcePath(ResourceUtil.getResource(cu).getFullPath(), newCUName)))
			return RefactoringStatus.createFatalErrorStatus(Messages.format(RefactoringCoreMessages.Checks_cu_name_used, newName));
		else
			return new RefactoringStatus();
	}
	public static boolean resourceExists(IPath resourcePath){
		return ResourcesPlugin.getWorkspace().getRoot().findMember(resourcePath) != null;
	}
	public static RefactoringStatus validateModifiesFiles(IFile[] filesToModify, Object context) {
		RefactoringStatus result= new RefactoringStatus();
		IStatus status= Resources.checkInSync(filesToModify);
		if (!status.isOK())
			result.merge(RefactoringStatus.create(status));
		status= Resources.makeCommittable(filesToModify, context);
		if (!status.isOK()) {
			result.merge(RefactoringStatus.create(status));
			if (!result.hasFatalError()) {
				result.addFatalError(RefactoringCoreMessages.Checks_validateEdit); 
			}			
		}
		return result;
	}
	public static boolean isAlreadyNamed(IModelElement element, String name){
		return name.equals(element.getElementName());
	}
}
