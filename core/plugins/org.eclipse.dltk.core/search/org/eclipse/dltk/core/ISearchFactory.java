/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.core;

import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.dltk.core.search.DLTKSearchParticipant;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.IMatchLocatorParser;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.core.search.SearchRequestor;
import org.eclipse.dltk.core.search.indexing.SourceIndexerRequestor;
import org.eclipse.dltk.core.search.matching.MatchLocator;

public interface ISearchFactory {
	
	IMatchLocatorParser createMatchParser(MatchLocator locator); //to ext point


	SourceIndexerRequestor createSourceRequestor(); //to ext point
	
	// Is this method really need?
	DLTKSearchParticipant createSearchParticipant(); // to ext point

	MatchLocator createMatchLocator(SearchPattern pattern, SearchRequestor requestor, IDLTKSearchScope scope, SubProgressMonitor monitor); // to ext point


}
