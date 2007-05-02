/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.search;

import org.eclipse.dltk.core.search.IDLTKSearchScope;



/**
 * <p>
 * Describes a search query by giving a textual pattern to search for.
 * </p>
 * <p>
 * This class is not intended to be instantiated or subclassed by clients.
 * </p>
 * 
 */
public class PatternQuerySpecification extends QuerySpecification {
	private String fPattern;
	private int fSearchFor;
	private boolean fCaseSensitive;

	/**
	 * @param pattern
	 *            The string that the query should search for.
	 * @param searchFor
	 *            What kind of <code>IModelElement</code> the query should search for.
	 * @param caseSensitive
	 *            Whether the query should be case sensitive.
	 * @param limitTo
	 *            The kind of occurrence the query should search for.
	 * @param scope
	 *            The scope to search in.
	 * @param scopeDescription
	 *            A human readable description of the search scope.
	 * 
	 */
	public PatternQuerySpecification(String pattern, int searchFor, boolean caseSensitive, int limitTo, IDLTKSearchScope scope, String scopeDescription) {
		super(limitTo, scope, scopeDescription);
		fPattern= pattern;
		fSearchFor= searchFor;
		fCaseSensitive= caseSensitive;
	}

	/**
	 * Whether the query should be case sensitive.
	 * @return Whether the query should be case sensitive.
	 */
	public boolean isCaseSensitive() {
		return fCaseSensitive;
	}

	/**
	 * Returns the search pattern the query should search for. 
	 * @return the search pattern
	 */
	public String getPattern() {
		return fPattern;
	}

	/**
	 * Returns what kind of <code>IModelElement</code> the query should search for.
	 * 
	 * @return The kind of <code>IModelElement</code> to search for.
	 * 
	 */
	public int getSearchFor() {
		return fSearchFor;
	}
}
