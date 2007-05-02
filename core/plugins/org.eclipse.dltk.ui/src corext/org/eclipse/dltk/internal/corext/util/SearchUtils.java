/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.corext.util;

import org.eclipse.dltk.compiler.env.ISourceModule;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.search.SearchEngine;
import org.eclipse.dltk.core.search.SearchMatch;
import org.eclipse.dltk.core.search.SearchParticipant;
import org.eclipse.dltk.core.search.SearchPattern;

public class SearchUtils {

	/**
	 * @param match
	 * @return the enclosing {@link IModelElement}, or null iff none
	 */
	public static IModelElement getEnclosingModelElement(SearchMatch match) {
		Object element = match.getElement();
		if (element instanceof IModelElement)
			return (IModelElement) element;
		else
			return null;
	}
	
	/**
	 * @param match
	 * @return the enclosing {@link ISourceModule} of the given match, or null iff none
	 */
	public static ISourceModule getSourceModule(SearchMatch match) {
		IModelElement enclosingElement = getEnclosingModelElement(match);
		if (enclosingElement != null){
			if (enclosingElement instanceof ISourceModule)
				return (ISourceModule) enclosingElement;
			ISourceModule cu= (ISourceModule) enclosingElement.getAncestor(IModelElement.SOURCE_MODULE);
			if (cu != null)
				return cu;
		}
		
		IModelElement jElement= DLTKCore.create(match.getResource());
		if (jElement != null && jElement.exists() && jElement.getElementType() == IModelElement.SOURCE_MODULE)
			return (ISourceModule) jElement;
		return null;
	}
	
	public static SearchParticipant[] getDefaultSearchParticipants() {
		return new SearchParticipant[] { SearchEngine.getDefaultSearchParticipant() };
	}
	
    /**
     * Constant for use as matchRule in {@link SearchPattern#createPattern(IModelElement, int, int)}
     * to get search behavior as of 3.1M3 (all generic instantiations are found).
     */
    public final static int GENERICS_AGNOSTIC_MATCH_RULE= SearchPattern.R_EXACT_MATCH | SearchPattern.R_CASE_SENSITIVE | SearchPattern.R_ERASURE_MATCH;

    /**
     * Returns whether the given pattern is a camel case pattern or not.
     * 
     * @param pattern the pattern to inspect
     * @return whether it is a camel case pattern or not
     */
	public static boolean isCamelCasePattern(String pattern) {
		return SearchPattern.validateMatchRule(
			pattern, 
			SearchPattern.R_CAMELCASE_MATCH) == SearchPattern.R_CAMELCASE_MATCH;
	}
}
