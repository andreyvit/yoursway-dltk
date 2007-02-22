/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.corext.refactoring;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.DLTKModelUtil;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.IScriptModel;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.corext.refactoring.reorg.ReorgPolicyFactory;
import org.eclipse.dltk.internal.corext.refactoring.reorg.ReorgUtils;
import org.eclipse.dltk.internal.ui.editor.ModelTextSelection;
import org.eclipse.jface.viewers.IStructuredSelection;


/**
 * Helper class to detect whether a certain refactoring can be enabled on a
 * selection.
 * <p>
 * This class has been introduced to decouple actions from the refactoring code,
 * in order not to eagerly load refactoring classes during action
 * initialization.
 * </p>
 * 
	 *
 */
public final class RefactoringAvailabilityTester {

	public static IType getDeclaringType(IModelElement element) {
		if (element == null)
			return null;
		if (!(element instanceof IType))
			element= element.getAncestor(IModelElement.TYPE);
		return (IType) element;
	}

	public static IModelElement[] getScriptElements(final Object[] elements) {
		List result= new ArrayList();
		for (int index= 0; index < elements.length; index++) {
			if (elements[index] instanceof IModelElement)
				result.add(elements[index]);
		}
		return (IModelElement[]) result.toArray(new IModelElement[result.size()]);
	}

	public static IResource[] getResources(final Object[] elements) {
		List result= new ArrayList();
		for (int index= 0; index < elements.length; index++) {
			if (elements[index] instanceof IResource)
				result.add(elements[index]);
		}
		return (IResource[]) result.toArray(new IResource[result.size()]);
	}

	public static boolean isRenameAvailable(final ISourceModule unit) {
		if (unit == null)
			return false;
		if (!unit.exists())
			return false;
		if (!DLTKModelUtil.isPrimary(unit))
			return false;
		if (unit.isReadOnly())
			return false;
		return true;
	}

	public static boolean isRenameAvailable(final IDLTKProject project) throws ModelException {
		if (project == null)
			return false;
		if (!Checks.isAvailable(project))
			return false;
		if (!project.isConsistent())
			return false;
		return true;
	}

	public static boolean isRenameAvailable(final IMethod method) throws CoreException {
		if (method == null)
			return false;
		if (!Checks.isAvailable(method))
			return false;
		if (method.isConstructor())
			return false;		
		return true;
	}

	public static boolean isRenameAvailable(final IScriptFolder fragment) throws ModelException {
		if (fragment == null)
			return false;
		if (!Checks.isAvailable(fragment))
			return false;
		if (fragment.isRootFolder())
			return false;
		return true;
	}

	public static boolean isRenameAvailable(final IProjectFragment root) throws ModelException {
		if (root == null)
			return false;
		if (!Checks.isAvailable(root))
			return false;
		if (root.isArchive())
			return false;
		if (root.isExternal())
			return false;
		if (!root.isConsistent())
			return false;
		if (root.getResource() instanceof IProject)
			return false;
		return true;
	}

	public static boolean isRenameAvailable(final IResource resource) {
		if (resource == null)
			return false;
		if (!resource.exists())
			return false;
		if (!resource.isAccessible())
			return false;
		return true;
	}

	public static boolean isRenameAvailable(final IType type) throws ModelException {
		if (type == null)
			return false;		
		if (!Checks.isAvailable(type))
			return false;
		return true;
	}

	public static boolean isRenameFieldAvailable(final IField field) throws ModelException {
		return Checks.isAvailable(field);
	}
	public static boolean isReplaceInvocationsAvailable(IMethod method) throws ModelException {
		if (method == null)
			return false;
		if (!method.exists())
			return false;
		if (method.isConstructor())
			return false;
		return true;
	}
	public static boolean isDeleteAvailable(final IModelElement element) throws ModelException {
		if (!element.exists())
			return false;
		if (element instanceof IScriptModel || element instanceof IDLTKProject)
			return false;
		if (element.getParent() != null && element.getParent().isReadOnly())
			return false;
		if (element instanceof IProjectFragment) {
			IProjectFragment root= (IProjectFragment) element;
			if (root.isExternal() || Checks.isBuildpathDelete(root)) // TODO
				// rename
				// isClasspathDelete
				return false;
		}
		if (element.getResource() == null && !RefactoringAvailabilityTester.isWorkingCopyElement(element))
			return false;
//		if (element instanceof IMember && ((IMember) element).isBinary())
//			return false;
		if (ReorgUtils.isDeletedFromEditor(element))
			return false;
		return true;
	}

	public static boolean isDeleteAvailable(final IResource resource) {
		if (!resource.exists() || resource.isPhantom())
			return false;
		if (resource.getType() == IResource.ROOT || resource.getType() == IResource.PROJECT)
			return false;
		return true;
	}

	public static boolean isDeleteAvailable(final IStructuredSelection selection) throws ModelException {
		if (!selection.isEmpty())
			return isDeleteAvailable(selection.toArray());
		return false;
	}

	public static boolean isDeleteAvailable(final Object[] objects) throws ModelException {
		if (objects.length != 0) {
			final IResource[] resources= RefactoringAvailabilityTester.getResources(objects);
			final IModelElement[] elements= RefactoringAvailabilityTester.getScriptElements(objects);
			if (objects.length != resources.length + elements.length)
				return false;
			for (int index= 0; index < resources.length; index++) {
				if (!isDeleteAvailable(resources[index]))
					return false;
			}
			for (int index= 0; index < elements.length; index++) {
				if (!isDeleteAvailable(elements[index]))
					return false;
			}
			return true;
		}
		return false;
	}
	
	public static boolean isWorkingCopyElement(final IModelElement element) {
		if (element instanceof ISourceModule)
			return ((ISourceModule) element).isWorkingCopy();
		if (ReorgUtils.isInsideSourceModule(element))
			return ReorgUtils.getSourceModule(element).isWorkingCopy();
		return false;
	}

	private RefactoringAvailabilityTester() {
		// Not for instantiation
	}
	public static boolean isMoveAvailable(final IResource[] resources, final IModelElement[] elements) throws ModelException {
		if (elements != null) {
			for (int index= 0; index < elements.length; index++) {
				IModelElement element= elements[index];
				if ((element instanceof IType) ) {
					return false;
				}
//				if ((element instanceof IPackageDeclaration))
//					return false;
				if (element instanceof IField ) {//&& DLTKFlags.isEnum((IMember) element))
					return false;
				}
			}
		}
		return ReorgPolicyFactory.createMovePolicy(resources, elements).canEnable();
	}

	public static boolean isMoveAvailable(final ModelTextSelection selection) throws ModelException {
		final IModelElement element= selection.resolveEnclosingElement();
		if (element == null)
			return false;
		return isMoveAvailable(new IResource[0], new IModelElement[] { element});
	}
}