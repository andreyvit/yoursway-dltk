/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.testing.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.operation.IRunnableWithProgress;

import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.Flags;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IRegion;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ITypeHierarchy;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.search.IDLTKSearchConstants;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.SearchEngine;
import org.eclipse.dltk.core.search.SearchMatch;
import org.eclipse.dltk.core.search.SearchParticipant;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.core.search.SearchRequestor;
import org.eclipse.dltk.internal.testing.launcher.ITestKind;


/**
 * Custom Search engine for suite() methods
 */
public class TestSearchEngine {

//	public static boolean isTestOrTestSuite(IType declaringType) throws CoreException {
//		ITestKind testKind= TestKindRegistry.getContainerTestKind(declaringType);
//		return testKind.getFinder().isTest(declaringType);
//	}


	public static IType[] findTests(IRunnableContext context, final IModelElement element, final ITestKind testKind) throws InvocationTargetException, InterruptedException {
		final Set result= new HashSet();

		IRunnableWithProgress runnable= new IRunnableWithProgress() {
			public void run(IProgressMonitor pm) throws InterruptedException, InvocationTargetException {
				try {
					testKind.getFinder().findTestsInContainer(element, result, pm);
				} catch (CoreException e) {
					throw new InvocationTargetException(e);
				}
			}
		};
		context.run(true, true, runnable);
		return (IType[]) result.toArray(new IType[result.size()]);
	}

	public static boolean isAccessibleClass(IType type) throws ModelException {
		int flags= type.getFlags();
//		if (Flags.isInterface(flags)) {
//			return false;
//		}
		IModelElement parent= type.getParent();
		while (true) {
			if (parent instanceof ISourceModule) {
				return true;
			}
			if (!(parent instanceof IType) || !Flags.isStatic(flags) || !Flags.isPublic(flags)) {
				return false;
			}
			flags= ((IType) parent).getFlags();
			parent= parent.getParent();
		}
	}

	public static IRegion getRegion(IModelElement element) throws ModelException {
		IRegion result= DLTKCore.newRegion();
		if (element.getElementType() == IModelElement.SCRIPT_PROJECT) {
			// for projects only add the contained source folders
			IProjectFragment[] roots= ((IScriptProject) element).getProjectFragments();
			for (int i= 0; i < roots.length; i++) {
				if (!roots[i].isArchive()) {
					result.add(roots[i]);
				}
			}
		} else {
			result.add(element);
		}
		return result;
	}

	public static void findTestImplementorClasses(ITypeHierarchy typeHierarchy, IType testInterface, IRegion region, Set result) throws ModelException {
		IType[] subtypes= typeHierarchy.getAllSubtypes(testInterface);
		for (int i= 0; i < subtypes.length; i++) {
			IType type= subtypes[i];
			int cachedFlags= typeHierarchy.getCachedFlags(type);
			if (region.contains(type) && TestSearchEngine.isAccessibleClass(type)) {
				result.add(type);
			}
		}
	}

	private static class SuiteMethodTypesCollector extends SearchRequestor {

		private Collection fResult;

		public SuiteMethodTypesCollector(Collection result) {
			fResult= result;
		}

		public void acceptSearchMatch(SearchMatch match) throws CoreException {
			Object enclosingElement= match.getElement();
			if (!(enclosingElement instanceof IMethod))
				return;

			IMethod method= (IMethod) enclosingElement;
			if (!Flags.isStatic(method.getFlags()) || !Flags.isPublic(method.getFlags())) {
				return;
			}

			IType declaringType= ((IMethod) enclosingElement).getDeclaringType();
			if (!TestSearchEngine.isAccessibleClass(declaringType)) {
				return;
			}
			fResult.add(declaringType);
		}
	}

	public static void findSuiteMethods(IModelElement element, Set result, IProgressMonitor pm) throws CoreException {
		// fix for bug 36449 JUnit should constrain tests to selected project
		// [JUnit]
		IDLTKSearchScope scope= SearchEngine.createSearchScope(new IModelElement[] { element }, IDLTKSearchScope.SOURCES);

		SearchRequestor requestor= new SuiteMethodTypesCollector(result);
		int matchRule= SearchPattern.R_EXACT_MATCH | SearchPattern.R_CASE_SENSITIVE | SearchPattern.R_ERASURE_MATCH;
		SearchPattern suitePattern= SearchPattern.createPattern("suite() Test", IDLTKSearchConstants.METHOD, IDLTKSearchConstants.DECLARATIONS, matchRule); //$NON-NLS-1$
		SearchParticipant[] participants= new SearchParticipant[] { SearchEngine.getDefaultSearchParticipant() };
		new SearchEngine().search(suitePattern, participants, scope, requestor, pm);
	}

}
