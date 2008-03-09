/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.javascript.internal.core;

import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.dltk.core.search.AbstractSearchFactory;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.IMatchLocatorParser;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.core.search.SearchRequestor;
import org.eclipse.dltk.core.search.matching.MatchLocator;
import org.eclipse.dltk.javascript.core.JavaScriptMatchLocatorParser;

public class JavaScriptSearchFactory extends AbstractSearchFactory {

	public IMatchLocatorParser createMatchParser(MatchLocator locator) {
		return new JavaScriptMatchLocatorParser(locator);
	}

	public MatchLocator createMatchLocator(SearchPattern pattern,
			SearchRequestor requestor, IDLTKSearchScope scope,
			SubProgressMonitor monitor) {
		return null;
	}
}
