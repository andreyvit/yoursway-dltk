/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.core;

import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.dltk.core.search.AbstractSearchFactory;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.IMatchLocatorParser;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.core.search.SearchRequestor;
import org.eclipse.dltk.core.search.indexing.SourceIndexerRequestor;
import org.eclipse.dltk.core.search.matching.MatchLocator;
import org.eclipse.dltk.tcl.core.TclMatchLocatorParser;
import org.eclipse.dltk.tcl.internal.core.search.TclMatchLocator;

/**
 * Tcl search factory
 */
public class TclSearchFactory extends AbstractSearchFactory {

	/*
	 * @see org.eclipse.dltk.core.ISearchFactory#createMatchParser(org.eclipse.dltk.core.search.matching.MatchLocator)
	 */
	public IMatchLocatorParser createMatchParser(MatchLocator locator) {
		return new TclMatchLocatorParser(locator);
	}

	/*
	 * @see org.eclipse.dltk.core.ISearchFactory#createMatchLocator(org.eclipse.dltk.core.search.SearchPattern,
	 *      org.eclipse.dltk.core.search.SearchRequestor,
	 *      org.eclipse.dltk.core.search.IDLTKSearchScope,
	 *      org.eclipse.core.runtime.SubProgressMonitor)
	 */
	public MatchLocator createMatchLocator(SearchPattern pattern,
			SearchRequestor requestor, IDLTKSearchScope scope,
			SubProgressMonitor monitor) {
		return new TclMatchLocator(pattern, requestor, scope, monitor);
	}
	
	/*
	 * @see org.eclipse.dltk.core.search.AbstractSearchFactory#createSourceRequestor()
	 */
	public SourceIndexerRequestor createSourceRequestor() {
		return new TclSourceIndexerRequestor();
	}
}
