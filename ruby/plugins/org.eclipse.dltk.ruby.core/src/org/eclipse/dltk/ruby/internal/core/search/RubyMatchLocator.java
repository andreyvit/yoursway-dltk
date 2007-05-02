/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.core.search;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.core.search.SearchRequestor;
import org.eclipse.dltk.core.search.matching.MatchLocator;

public class RubyMatchLocator extends MatchLocator {

	public RubyMatchLocator(SearchPattern pattern, SearchRequestor requestor,
			IDLTKSearchScope scope, IProgressMonitor progressMonitor) {
		super(pattern, requestor, scope, progressMonitor);
	}
}
