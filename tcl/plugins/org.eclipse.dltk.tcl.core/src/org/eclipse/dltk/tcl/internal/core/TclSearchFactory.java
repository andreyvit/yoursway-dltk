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
import org.eclipse.dltk.tcl.core.TclMatchLocatorParser;
import org.eclipse.dltk.tcl.internal.core.search.TclMatchLocator;

public class TclSearchFactory implements ISearchFactory {

	public IMatchLocatorParser createMatchParser(MatchLocator locator) {
		return new TclMatchLocatorParser(locator);
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
