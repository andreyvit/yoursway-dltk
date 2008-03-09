package org.eclipse.dltk.core.search;

import org.eclipse.dltk.core.ISearchFactory;
import org.eclipse.dltk.core.search.indexing.SourceIndexerRequestor;

public abstract class AbstractSearchFactory implements ISearchFactory {

	/*
	 * @see org.eclipse.dltk.core.ISearchFactory#createSourceRequestor()
	 */
	public SourceIndexerRequestor createSourceRequestor() {
		return new SourceIndexerRequestor();
	}

	/*
	 * @see org.eclipse.dltk.core.ISearchFactory#createSearchParticipant()
	 */
	public DLTKSearchParticipant createSearchParticipant() {
		return null;
	}
	
}
