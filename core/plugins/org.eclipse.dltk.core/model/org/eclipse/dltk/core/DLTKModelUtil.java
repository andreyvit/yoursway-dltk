/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.core;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.compiler.CharOperation;
import org.eclipse.dltk.core.search.IDLTKSearchConstants;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.SearchEngine;
import org.eclipse.dltk.core.search.SearchParticipant;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.core.search.SearchRequestor;
import org.eclipse.dltk.core.search.TypeNameMatch;
import org.eclipse.dltk.core.search.TypeNameMatchRequestor;
import org.eclipse.dltk.internal.core.ExternalScriptFolder;

public class DLTKModelUtil {
	/**
	 * Force a reconcile of a compilation unit.
	 * 
	 * @param unit
	 */
	public static void reconcile(ISourceModule unit) throws ModelException {
		unit.reconcile(false /* don't force problem detection */,
				null /* use primary owner */, null /* no progress monitor */);
	}

	public static boolean isPrimary(ISourceModule unit) {
		return unit.getOwner() == null;
	}

	public static boolean isExcludedPath(IPath resourcePath,
			IPath[] exclusionPatterns) {
		char[] path = resourcePath.toString().toCharArray();
		for (int i = 0, length = exclusionPatterns.length; i < length; i++) {
			char[] pattern = exclusionPatterns[i].toString().toCharArray();
			if (CharOperation.pathMatch(pattern, path, true, '/')) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the package fragment root of <code>IModelElement</code>. If
	 * the given element is already a package fragment root, the element itself
	 * is returned.
	 */
	public static IProjectFragment getProjectFragment(IModelElement element) {
		return (IProjectFragment) element
				.getAncestor(IModelElement.PROJECT_FRAGMENT);
	}

	public static IModelElement findInSourceModule(ISourceModule cu,
			IModelElement element) {
		if (DLTKCore.DEBUG) {
			System.err.println("TODO: Implement find in source module...");
		}
		return null;
	}

	public static void searchTypeDeclarations(IDLTKProject project,
			String patternString, TypeNameMatchRequestor requestor) {
		IDLTKSearchScope scope = SearchEngine
			.createSearchScope(new IModelElement[] { project });
		try {
			SearchEngine engine = new SearchEngine();
			engine.searchAllTypeNames(null, 0,
					patternString.toCharArray(), SearchPattern.R_EXACT_MATCH | SearchPattern.R_PATTERN_MATCH,
					IDLTKSearchConstants.TYPE, scope, requestor,
					IDLTKSearchConstants.WAIT_UNTIL_READY_TO_SEARCH, null);
		} catch (CoreException e) {
			if (DLTKCore.DEBUG)
				e.printStackTrace();
		}
	}

	public static void searchMethodDeclarations(IDLTKProject project,
			String patternString, SearchRequestor requestor) {
		
		IDLTKSearchScope scope = SearchEngine
					.createSearchScope(new IModelElement[] { project });

		try {
			SearchEngine engine = new SearchEngine();
			SearchPattern pattern = SearchPattern.createPattern(patternString,
					IDLTKSearchConstants.METHOD,
					IDLTKSearchConstants.DECLARATIONS,
					SearchPattern.R_PATTERN_MATCH);
			engine.search(pattern, new SearchParticipant[] { SearchEngine
					.getDefaultSearchParticipant() }, scope, requestor, null);
		} catch (CoreException e) {
			if (DLTKCore.DEBUG)
				e.printStackTrace();
		}
	}
	
	public static IModelElement findType(IModelElement module,
			String qualifiedName, String delimeter) {

		if (module instanceof IType) {
			IType type = (IType) module;
			String tmpFqn = type.getTypeQualifiedName(delimeter);
			if (!tmpFqn.startsWith(delimeter))
				tmpFqn = delimeter + tmpFqn;
			if (tmpFqn.equals(qualifiedName))
				return type;
		}

		if (module instanceof IParent) {
			IModelElement el = null;
			IParent p = (IParent) module;
			IModelElement[] childs;
			try {
				childs = p.getChildren();
				for (int i = 0; i < childs.length; i++) {
					el = findType(childs[i], qualifiedName, delimeter);
					if (el != null)
						return el;
				}
			} catch (ModelException e) {
				if (DLTKCore.DEBUG)
					e.printStackTrace();
				return null;
			}
		}

		return null;
	}

	public static boolean isExceptionToBeLogged(CoreException exception) {
		if (!(exception instanceof ModelException))
			return true;
		ModelException je = (ModelException) exception;
		if (!je.isDoesNotExist())
			return true;
		if (DLTKCore.DEBUG) {
			System.err
					.println("TODO: ModelUtil: isExceptionToBeLogged. Require addition of ModelStatus.getgetElements method...");
		}
		// IModelElement[] elements= je.getModelStatus().getElements();
		// for (int i= 0; i < elements.length; i++) {
		// IModelElement element= elements[i];
		// // if the element is already a compilation unit don't log
		// // does not exist exceptions. See bug
		// // https://bugs.eclipse.org/bugs/show_bug.cgi?id=75894
		// // for more details
		// if (element.getElementType() == IScriptElement.COMPILATION_UNIT)
		// continue;
		// ISourceModule unit=
		// (ISourceModule)element.getAncestor(IScriptElement.COMPILATION_UNIT);
		// if (unit == null)
		// return true;
		// if (!unit.isWorkingCopy())
		// return true;
		// }
		return true;// false
	}

	public static String getFullyQualifiedName(IType type) {
		return type.getFullyQualifiedName();
	}

	public static String getRenamedCUName(ISourceModule cu, String newMainName) {
		String oldName = cu.getElementName();
		int i = oldName.lastIndexOf('.');
		if (i != -1) {
			return newMainName + oldName.substring(i);
		} else {
			return newMainName;
		}
	}

	public static boolean isSuperType(ITypeHierarchy hierarchy,
			IType possibleSuperType, IType type) {
		// filed bug 112635 to add this method to ITypeHierarchy
		IType[] superClass = hierarchy.getSuperclass(type);
		if (superClass != null) {
			for (int q = 0; q < superClass.length; ++q) {
				if ((possibleSuperType.equals(superClass[q]) || isSuperType(
						hierarchy, possibleSuperType, superClass[q]))) {
					return true;
				}
			}
		}
		return false;
	}

}
