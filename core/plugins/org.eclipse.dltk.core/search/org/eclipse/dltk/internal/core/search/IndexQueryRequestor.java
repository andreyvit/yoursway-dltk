/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.core.search;

import org.eclipse.dltk.core.search.SearchParticipant;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.internal.compiler.env.AccessRuleSet;

/**
 * TODO add spec
 */
public abstract class IndexQueryRequestor {
	
	// answer false if requesting cancel
	public abstract boolean acceptIndexMatch(String documentPath, SearchPattern indexRecord, SearchParticipant participant, AccessRuleSet access);
	
}
