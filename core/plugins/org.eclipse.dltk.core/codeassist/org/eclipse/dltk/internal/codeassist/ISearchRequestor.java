/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.codeassist;

import org.eclipse.dltk.internal.compiler.env.AccessRestriction;

/**
 * This is the internal requestor passed to the searchable name environment
 * so as to process the multiple search results as they are discovered.
 *
 * It is used to allow the code assist engine to add some more information
 * to the raw name environment results before answering them to the UI.
 */
public interface ISearchRequestor {
	/**
	 * One result of the search consists of a new type.
	 *
	 * NOTE - All package and type names are presented in their readable form:
	 *    Package names are in the form "a.b.c".
	 *    Nested type names are in the qualified form "A.I".
	 *    The default package is represented by an empty array.
	 */
	public void acceptType(char[] packageName, char[] typeName, char[][] enclosingTypeNames, int modifiers, AccessRestriction accessRestriction);

	/**
	 * One result of the search consists of a new package.
	 *
	 * NOTE - All package names are presented in their readable form:
	 *    Package names are in the form "a.b.c".
	 *    The default package is represented by an empty array.
	 */
	public void acceptPackage(char[] packageName);
}
