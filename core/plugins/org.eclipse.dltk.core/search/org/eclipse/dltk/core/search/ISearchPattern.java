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
package org.eclipse.dltk.core.search;

import org.eclipse.dltk.core.IModelElement;

/**
 * A search pattern defines how search results are found. Use <code>SearchEngine.createSearchPattern</code>
 * to create a search pattern.
 *
 * @see SearchEngine#createSearchPattern(IModelElement, int)
 * @see SearchEngine#createSearchPattern(String, int, int, boolean)
 * @deprecated the class
 * {@link org.eclipse.dltk.core.search.SearchPattern} replaces this interface.
 */
public interface ISearchPattern {
	// used as a marker interface: contains no methods
}
