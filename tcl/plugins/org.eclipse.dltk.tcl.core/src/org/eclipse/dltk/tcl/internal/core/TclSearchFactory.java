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
import org.eclipse.dltk.core.ISearchFactory;
import org.eclipse.dltk.core.search.DLTKSearchParticipant;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.IMatchLocatorParser;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.core.search.SearchRequestor;
import org.eclipse.dltk.core.search.indexing.SourceIndexerRequestor;
import org.eclipse.dltk.core.search.matching.MatchLocator;
import org.eclipse.dltk.tcl.internal.core.search.TclMatchLocator;

public class TclSearchFactory implements ISearchFactory {

	public IMatchLocatorParser createMatchParser(MatchLocator locator) {
//		return new TclMatchLocatorParser(locator);
		return new ExtTclMatchLocatorParser(locator);
	}
	public MatchLocator createMatchLocator(SearchPattern pattern, SearchRequestor requestor, IDLTKSearchScope scope, SubProgressMonitor monitor) {
		return new TclMatchLocator(pattern, requestor, scope, monitor);
	}
	
	public DLTKSearchParticipant createSearchParticipant() {
		return null;
	}
	public SourceIndexerRequestor createSourceRequestor() {
		return new TclSourceIndexerRequestor();
	}


}
