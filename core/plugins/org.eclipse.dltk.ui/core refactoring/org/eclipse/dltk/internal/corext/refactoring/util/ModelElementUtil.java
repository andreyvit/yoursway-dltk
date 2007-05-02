/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.corext.refactoring.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKModelUtil;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ISourceReference;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.corext.SourceRange;


public class ModelElementUtil {
	// no instances
	private ModelElementUtil() {}

	public static IModelElement[] getElementsOfType(IModelElement[] elements, int type) {
		Set result = new HashSet(elements.length);
		for (int i = 0; i < elements.length; i++) {
			IModelElement element = elements[i];
			if (element.getElementType() == type)
				result.add(element);
		}
		return (IModelElement[]) result.toArray(new IModelElement[result.size()]);
	}
	
	public static boolean isAncestorOf(IModelElement ancestor, IModelElement child) {
		IModelElement parent = child.getParent();
		while (parent != null && !parent.equals(ancestor)) {
			parent = parent.getParent();
		}
		return parent != null;
	}

	public static IMethod[] getAllConstructors(IType type) throws ModelException {
		List result = new ArrayList();
		IMethod[] methods = type.getMethods();
		for (int i = 0; i < methods.length; i++) {
			IMethod iMethod = methods[i];
			if (iMethod.isConstructor())
				result.add(iMethod);
		}
		return (IMethod[]) result.toArray(new IMethod[result.size()]);
	}

	/**
	 * Returns an array of projects that have the specified root on their
	 * buildpaths.
	 */
	public static IDLTKProject[] getReferencingProjects(IProjectFragment root) throws ModelException {
		IBuildpathEntry cpe = root.getRawBuildpathEntry();
		IDLTKProject myProject = root.getScriptProject();
		IDLTKProject[] allScriptProjects = DLTKCore.create(ResourcesPlugin.getWorkspace().getRoot()).getScriptProjects();
		List result = new ArrayList(allScriptProjects.length);
		for (int i = 0; i < allScriptProjects.length; i++) {
			IDLTKProject project = allScriptProjects[i];
			if (project.equals(myProject))
				continue;
			IProjectFragment[] roots = project.findProjectFragments(cpe);
			if (roots.length > 0)
				result.add(project);
		}
		return (IDLTKProject[]) result.toArray(new IDLTKProject[result.size()]);
	}

	public static IMember[] merge(IMember[] a1, IMember[] a2) {
		// Don't use hash sets since ordering is important for some
		// refactorings.
		List result = new ArrayList(a1.length + a2.length);
		for (int i = 0; i < a1.length; i++) {
			IMember member = a1[i];
			if (!result.contains(member))
				result.add(member);
		}
		for (int i = 0; i < a2.length; i++) {
			IMember member = a2[i];
			if (!result.contains(member))
				result.add(member);
		}
		return (IMember[]) result.toArray(new IMember[result.size()]);
	}

	public static boolean isDefaultPackage(Object element) {
		return (element instanceof IScriptFolder) && ((IScriptFolder) element).isRootFolder();
	}

	/**
	 * @param pack
	 *            a package fragment
	 * @return an array containing the given package and all subpackages
	 * @throws ModelException
	 */
	public static IScriptFolder[] getPackageAndSubpackages(IScriptFolder pack) throws ModelException {
		if (pack.isRootFolder())
			return new IScriptFolder[] {
				pack
			};
		IProjectFragment root = (IProjectFragment) pack.getParent();
		IModelElement[] allPackages = root.getChildren();
		ArrayList subpackages = new ArrayList();
		subpackages.add(pack);
		String prefix = pack.getElementName() + '.';
		for (int i = 0; i < allPackages.length; i++) {
			IScriptFolder currentPackage = (IScriptFolder) allPackages[i];
			if (currentPackage.getElementName().startsWith(prefix))
				subpackages.add(currentPackage);
		}
		return (IScriptFolder[]) subpackages.toArray(new IScriptFolder[subpackages.size()]);
	}

	/**
	 * @param pack
	 *            the package fragment; may not be null
	 * @return the parent package fragment, or null if the given package
	 *         fragment is the default package or a top level package
	 */
	public static IScriptFolder getParentSubpackage(IScriptFolder pack) {
		if (pack.isRootFolder())
			return null;
		final int index = pack.getElementName().lastIndexOf('.');
		if (index == -1)
			return null;
		final IProjectFragment root = (IProjectFragment) pack.getParent();
		final String newPackageName = pack.getElementName().substring(0, index);
		final IScriptFolder parent = root.getScriptFolder(newPackageName);
		if (parent.exists())
			return parent;
		else
			return null;
	}

	public static IMember[] sortByOffset(IMember[] members) {
		Comparator comparator = new Comparator() {
			public int compare(Object o1, Object o2) {
				try {
					return ((IMember) o1).getNameRange().getOffset() - ((IMember) o2).getNameRange().getOffset();
				} catch (ModelException e) {
					return 0;
				}
			}
		};
		Arrays.sort(members, comparator);
		return members;
	}

	public static boolean isSourceAvailable(ISourceReference sourceReference) {
		try {
			return SourceRange.isAvailable(sourceReference.getSourceRange());
		} catch (ModelException e) {
			return false;
		}
	}

	public static String createFieldSignature(IField field) {
		return DLTKModelUtil.getFullyQualifiedName(field.getDeclaringType()) + IScriptFolder.PACKAGE_DELIMITER + field.getElementName(); 
	}
}
