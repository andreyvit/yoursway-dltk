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
