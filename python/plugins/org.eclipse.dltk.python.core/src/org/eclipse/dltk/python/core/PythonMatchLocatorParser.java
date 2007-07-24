/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.core;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.ast.declarations.ISourceParser;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.search.IMatchLocatorParser;
import org.eclipse.dltk.core.search.matching.MatchLocator;
import org.eclipse.dltk.core.search.matching.MatchLocatorParser;
import org.eclipse.dltk.core.search.matching.PossibleMatch;

public class PythonMatchLocatorParser extends MatchLocatorParser implements IMatchLocatorParser {
	private ISourceParser parser;
	public PythonMatchLocatorParser(MatchLocator locator) {
		super(locator);
		try {
			parser = DLTKLanguageManager.getSourceParser(PythonNature.NATURE_ID);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	public ModuleDeclaration parse(PossibleMatch possibleMatch) {
		return parser.parse(possibleMatch.getFileName(), possibleMatch.getSourceContents().toCharArray(), null);
	}

	public void parseBodies(ModuleDeclaration unit) {
		// TODO Auto-generated method stub		
	}
}
