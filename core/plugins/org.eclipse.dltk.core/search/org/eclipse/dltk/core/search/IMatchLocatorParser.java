/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.core.search;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.search.matching.PossibleMatch;
import org.eclipse.dltk.internal.core.search.matching.MatchingNodeSet;

public interface IMatchLocatorParser {

	void setNodeSet(MatchingNodeSet nodeSet);

	ModuleDeclaration parse(PossibleMatch possibleMatch);

	void parseBodies(ModuleDeclaration unit);
}
