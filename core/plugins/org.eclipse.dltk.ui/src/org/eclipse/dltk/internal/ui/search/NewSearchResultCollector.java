/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.search;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.search.FieldReferenceMatch;
import org.eclipse.dltk.core.search.SearchMatch;
import org.eclipse.dltk.core.search.SearchParticipant;
import org.eclipse.dltk.core.search.SearchRequestor;


public class NewSearchResultCollector extends SearchRequestor {
	private DLTKSearchResult fSearch;
	private boolean fIgnorePotentials;

	public NewSearchResultCollector(DLTKSearchResult search, boolean ignorePotentials) {
		super();
		fSearch= search;
		fIgnorePotentials= ignorePotentials;
	}
	
	public void acceptSearchMatch(SearchMatch match) throws CoreException {
		IModelElement enclosingElement= (IModelElement) match.getElement();
		if (enclosingElement != null) {
			if (fIgnorePotentials && (match.getAccuracy() == SearchMatch.A_INACCURATE))
				return;
			boolean isWriteAccess= false;
			boolean isReadAccess= false;
			if (match instanceof FieldReferenceMatch) {
				FieldReferenceMatch fieldRef= ((FieldReferenceMatch)match);
				isWriteAccess= fieldRef.isWriteAccess();
				isReadAccess= fieldRef.isReadAccess();
			}
			fSearch.addMatch(new DLTKElementMatch(enclosingElement, match.getRule(), match.getOffset(), match.getLength(), match.getAccuracy(), isReadAccess, isWriteAccess, match.isInsideDocComment()));
		}
	}

	public void beginReporting() {
	}

	public void endReporting() {
	}

	public void enterParticipant(SearchParticipant participant) {
	}

	public void exitParticipant(SearchParticipant participant) {
	}


}
