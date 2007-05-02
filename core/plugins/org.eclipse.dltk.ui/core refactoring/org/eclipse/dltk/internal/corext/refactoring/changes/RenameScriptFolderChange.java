/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.corext.refactoring.changes;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.corext.refactoring.AbstractModelElementRenameChange;
import org.eclipse.dltk.internal.corext.refactoring.RefactoringCoreMessages;
import org.eclipse.dltk.internal.corext.refactoring.util.ModelElementUtil;
import org.eclipse.dltk.internal.corext.util.Messages;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.NullChange;
import org.eclipse.ltk.core.refactoring.RefactoringDescriptor;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;


public class RenameScriptFolderChange extends AbstractModelElementRenameChange {

	private Map fSourceModuleStamps;
	private final boolean fRenameSubpackages;

	public RenameScriptFolderChange(RefactoringDescriptor descriptor, IScriptFolder pack, String newName, String comment, boolean renameSubpackages) {
		this(descriptor, pack.getPath(), pack.getElementName(), newName, comment, IResource.NULL_STAMP, null, renameSubpackages);
		Assert.isTrue(!pack.isReadOnly(), "package must not be read only"); //$NON-NLS-1$
	}

	private RenameScriptFolderChange(RefactoringDescriptor descriptor, IPath resourcePath, String oldName, String newName, String comment, long stampToRestore,
		Map compilationUnitStamps, boolean renameSubpackages) {
		super(descriptor, resourcePath, oldName, newName, comment, stampToRestore);
		fSourceModuleStamps= compilationUnitStamps;
		fRenameSubpackages= renameSubpackages;
	}
	
	public RefactoringStatus isValid(IProgressMonitor pm) throws CoreException {
		RefactoringStatus result= new RefactoringStatus();
		IModelElement element= (IModelElement)getModifiedElement();
		// don't check for read-only since we don't go through 
		// validate edit.
		result.merge(isValid(DIRTY));
		if (result.hasFatalError())
			return result;
		if (element != null && element.exists() && element instanceof IScriptFolder) {
			IScriptFolder pack= (IScriptFolder)element;
			if (fRenameSubpackages) {
				IScriptFolder[] allPackages= ModelElementUtil.getPackageAndSubpackages(pack);
				pm.beginTask("", allPackages.length); //$NON-NLS-1$
				for (int i= 0; i < allPackages.length; i++) {
					// don't check for read-only since we don't go through 
					// validate edit.
					checkIfModifiable(result, allPackages[i], DIRTY);
					if (result.hasFatalError())
						return result;
					isValid(result, allPackages[i], new SubProgressMonitor(pm, 1));
				}
				pm.done();
			} else {
				isValid(result, pack, pm);
			}
		}
		return result;
	}

	private void isValid(RefactoringStatus result, IScriptFolder pack, IProgressMonitor pm) throws ModelException {
		ISourceModule[] units= pack.getSourceModules();
		pm.beginTask("", units.length); //$NON-NLS-1$
		for (int i= 0; i < units.length; i++) {
			pm.subTask(Messages.format(
				RefactoringCoreMessages.RenamePackageChange_checking_change, pack.getElementName())); 
			checkIfModifiable(result, units[i], READ_ONLY | DIRTY);
			pm.worked(1);
		}
		pm.done();
	}

	protected IPath createNewPath() {
		IScriptFolder oldPackage= getPackage();
		IPath oldPackageName= createPath(oldPackage.getElementName());
		IPath newPackageName= createPath(getNewName());
		return getResourcePath().removeLastSegments(oldPackageName.segmentCount()).append(newPackageName);
	}

	private static IPath createPath(String packageName) {
		return new Path(packageName.replace('.', IPath.SEPARATOR));
	}

	protected IPath createNewPath(IScriptFolder oldPackage) {
		IPath oldPackagePath= createPath(oldPackage.getElementName());
		IPath newPackagePath= createPath(getNewName(oldPackage));
		return oldPackage.getPath().removeLastSegments(oldPackagePath.segmentCount()).append(newPackagePath);
	}
	
	private String getNewName(IScriptFolder subpackage) {
		return getNewName() + subpackage.getElementName().substring(getOldName().length());
	}

	public String getName() {
		String msg= fRenameSubpackages
				? RefactoringCoreMessages.RenamePackageChange_name_with_subpackages
				: RefactoringCoreMessages.RenamePackageChange_name;
		return Messages.format(msg, new String[]{getOldName(), getNewName()});
	}

	protected Change createUndoChange(long stampToRestore) throws CoreException {
		IScriptFolder pack= getPackage();
		if (pack == null)
			return new NullChange();
		Map stamps= new HashMap();
		if (! fRenameSubpackages) {
			addStamps(stamps, pack.getSourceModules());
		} else {
			IScriptFolder[] allPackages= ModelElementUtil.getPackageAndSubpackages(pack);
			for (int i= 0; i < allPackages.length; i++) {
				IScriptFolder currentPackage= allPackages[i];
				addStamps(stamps, currentPackage.getSourceModules());
			}
		}
		return new RenameScriptFolderChange(null, createNewPath(), getNewName(), getOldName(), getComment(), stampToRestore, stamps, fRenameSubpackages);
			// Note: This reverse change only works if the renamePackage change did not merge the source package into an existing target.
	}

	private void addStamps(Map stamps, ISourceModule[] units) {
		for (int i= 0; i < units.length; i++) {
			IResource resource= units[i].getResource();
			long stamp= IResource.NULL_STAMP;
			if (resource != null && (stamp= resource.getModificationStamp()) != IResource.NULL_STAMP) {
				stamps.put(resource, new Long(stamp));
			}
		}
	}

	protected void doRename(IProgressMonitor pm) throws CoreException {
		IScriptFolder pack= getPackage();
		if (pack == null)
			return;
		
		if (! fRenameSubpackages) {
			renamePackage(pack, pm, createNewPath(), getNewName());
		} else {
			IScriptFolder[] allPackages= ModelElementUtil.getPackageAndSubpackages(pack);
			
			pm.beginTask("", allPackages.length); //$NON-NLS-1$
			try {
				for (int i= 0; i < allPackages.length; i++) {
					IScriptFolder currentPackage= allPackages[i];
					renamePackage(currentPackage, new SubProgressMonitor(pm, 1), createNewPath(currentPackage), getNewName(currentPackage));
				}
			} finally {
				pm.done();
			}
		}
	}

	private void renamePackage(IScriptFolder pack, IProgressMonitor pm, IPath newPath, String newName) throws ModelException, CoreException {
		pack.rename(newName, false, pm);
		if (fSourceModuleStamps != null) {
			IScriptFolder newPack= (IScriptFolder)DLTKCore.create(
				ResourcesPlugin.getWorkspace().getRoot().getFolder(newPath));
			if (newPack.exists()) {
				ISourceModule[] units= newPack.getSourceModules();
				for (int i= 0; i < units.length; i++) {
					IResource resource= units[i].getResource();
					if (resource != null) {
						Long stamp= (Long)fSourceModuleStamps.get(resource);
						if (stamp != null) {
							resource.revertModificationStamp(stamp.longValue());
						}
					}
				}
			}
		}
	}

	private IScriptFolder getPackage() {
		return (IScriptFolder)getModifiedElement();
	}
}
