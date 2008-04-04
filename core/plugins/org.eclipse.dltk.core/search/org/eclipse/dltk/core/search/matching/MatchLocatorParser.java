/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
*******************************************************************************/
package org.eclipse.dltk.core.search.matching;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.core.search.IMatchLocatorParser;
import org.eclipse.dltk.internal.core.search.matching.MatchingNodeSet;

public abstract class MatchLocatorParser implements IMatchLocatorParser {
	private MatchLocator matchLocator;
	private PatternLocator patternLocator;
	
	private MatchingNodeSet nodeSet;	
		
	public ModuleDeclaration parse(PossibleMatch possibleMatch) {
		ModuleDeclaration module = SourceParserUtil.getModuleDeclaration(
				(org.eclipse.dltk.core.ISourceModule) possibleMatch
						.getModelElement(), null);
		return module;
	}
	
	public void parseBodies(ModuleDeclaration unit) {
		try {
			unit.traverse(getMatchVisitor());
		} catch (Exception e) {
			if (DLTKCore.DEBUG) {			
				e.printStackTrace();
			}
		}
	}
	
	public void setNodeSet(MatchingNodeSet nodeSet) {
		this.nodeSet = nodeSet;
	}
	
	protected MatchingNodeSet getNodeSet(){
		return nodeSet;
	}
		
	protected MatchLocatorParser(MatchLocator locator) {
		this.matchLocator = locator;
		this.patternLocator = locator.patternLocator;
	}
	
	protected MatchLocator getMatchLocator(){
		return matchLocator;
	}
	
	protected PatternLocator getPatternLocator(){
		return patternLocator; 
	}	
	
	protected MethodDeclaration processMethod(MethodDeclaration m) {
		return m;
	}

	protected TypeDeclaration processType(TypeDeclaration t) {
		return t;
	}
	
	protected void processStatement(ASTNode node, PatternLocator locator) {
		// empty implementation
	}
	
	protected MatchVisitor getMatchVisitor() {
		return new MatchVisitor();
	}
	
	protected class MatchVisitor extends ASTVisitor {
		public boolean visitGeneral(ASTNode node) throws Exception {
			processStatement(node, getPatternLocator());
			return true;
		}

		public boolean visit(MethodDeclaration m) throws Exception {
			getPatternLocator().match(processMethod(m), getNodeSet());
			return true;
		}

		public boolean visit(TypeDeclaration t) throws Exception {
			getPatternLocator().match(processType(t), getNodeSet());
			return true;
		}
	}
}
