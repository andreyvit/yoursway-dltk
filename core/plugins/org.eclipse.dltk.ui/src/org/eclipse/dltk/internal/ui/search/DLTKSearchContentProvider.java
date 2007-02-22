/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.search;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public abstract class DLTKSearchContentProvider implements IStructuredContentProvider {
	protected final Object[] EMPTY_ARR= new Object[0];
	protected DLTKSearchResult fResult;
	private DLTKSearchResultPage fPage;

	DLTKSearchContentProvider(DLTKSearchResultPage page) {
		fPage= page;
	}
	
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		initialize((DLTKSearchResult) newInput);
	}
	
	protected void initialize(DLTKSearchResult result) {
		fResult= result;
	}
	
	public abstract void elementsChanged(Object[] updatedElements);
	public abstract void clear();

	public void filtersChanged(MatchFilter[] filters) {
	}
	
	
	public void dispose() {
		// nothing to do
	}

	DLTKSearchResultPage getPage() {
		return fPage;
	}

}
