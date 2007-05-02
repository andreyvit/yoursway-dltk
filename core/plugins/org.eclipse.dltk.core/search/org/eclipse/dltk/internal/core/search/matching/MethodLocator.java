/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.core.search.matching;

import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.expressions.CallExpression;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.search.SearchMatch;
import org.eclipse.dltk.core.search.matching.MatchLocator;
import org.eclipse.dltk.core.search.matching.PatternLocator;


public class MethodLocator extends PatternLocator {
	protected MethodPattern pattern;
	protected boolean isDeclarationOfReferencedMethodsPattern;

	public MethodLocator(MethodPattern pattern) {
		super(pattern);
		this.pattern = pattern;
		this.isDeclarationOfReferencedMethodsPattern = this.pattern instanceof DeclarationOfReferencedMethodsPattern;
	}

	/*
	 * Clear caches
	 */
	protected void clear() {
	}

	public void initializePolymorphicSearch(MatchLocator locator) {
	}

	public int match(ASTNode node, MatchingNodeSet nodeSet) {
		int declarationsLevel = IMPOSSIBLE_MATCH;
		if (this.pattern.findReferences) {
//			if (node instanceof ImportReference) {
//				// With static import, we can have static method reference in
//				// import reference
//				ImportReference importRef = (ImportReference) node;
//				int length = importRef.tokens.length - 1;
//				if (importRef.isStatic() && !importRef.onDemand && matchesName(this.pattern.selector, importRef.tokens[length])) {
//					char[][] compoundName = new char[length][];
//					System.arraycopy(importRef.tokens, 0, compoundName, 0, length);
//					char[] declaringType = CharOperation.concat(pattern.declaringQualification, pattern.declaringSimpleName, '.');
//					if (matchesName(declaringType, CharOperation.concatWith(compoundName, '.'))) {
//						declarationsLevel = ((InternalSearchPattern) this.pattern).mustResolve ? POSSIBLE_MATCH : ACCURATE_MATCH;
//					}
//				}
//			}
		}
		return nodeSet.addMatch(node, declarationsLevel);
	}

	// public int match(ConstructorDeclaration node, MatchingNodeSet nodeSet) -
	// SKIP IT
	// public int match(Expression node, MatchingNodeSet nodeSet) - SKIP IT
	// public int match(FieldDeclaration node, MatchingNodeSet nodeSet) - SKIP
	// IT
	public int match(MethodDeclaration node, MatchingNodeSet nodeSet) {
		if (!this.pattern.findDeclarations)
			return IMPOSSIBLE_MATCH;
		// Verify method name
		if (!matchesName(this.pattern.selector, node.getName().toCharArray()))
			return IMPOSSIBLE_MATCH;
		// Verify parameters types
		if (this.pattern.parameterSimpleNames != null) {
			int length = this.pattern.parameterSimpleNames.length;
			List args = node.getArguments();
			int argsLength = args == null ? 0 : args.size();
			if (length != argsLength)
				return IMPOSSIBLE_MATCH;
		}
		// Verify type arguments (do not reject if pattern has no argument as it
		// can be an erasure match)
		if (this.pattern.hasMethodArguments()) {
//			if (node.typeParameters == null || node.typeParameters.length != this.pattern.methodArguments.length)
//				return IMPOSSIBLE_MATCH;
		}
		// Method declaration may match pattern
		return nodeSet.addMatch(node, ACCURATE_MATCH);
	}	
	
	// public int match(TypeDeclaration node, MatchingNodeSet nodeSet) - SKIP IT
	// public int match(TypeReference node, MatchingNodeSet nodeSet) - SKIP IT
	public int matchContainer() {
		if (this.pattern.findReferences) {
			// need to look almost everywhere to find in javadocs and static
			// import
			return ALL_CONTAINER;
		}
		return COMPILATION_UNIT_CONTAINER | CLASS_CONTAINER | METHOD_CONTAINER;
	}

	public SearchMatch newDeclarationMatch(ASTNode reference, IModelElement element, int accuracy, int length,
			MatchLocator locator) {		
		return super.newDeclarationMatch(reference, element, accuracy, length, locator);
	}

	protected int referenceType() {
		return IModelElement.METHOD;
	}

	public String toString() {
		return "Locator for " + this.pattern.toString(); //$NON-NLS-1$
	}

	public int match(CallExpression node, MatchingNodeSet nodeSet) { // interested in NameReference & its subtypes
		if (!this.pattern.findReferences) return IMPOSSIBLE_MATCH;
		
		if (this.pattern.selector == null)
			return nodeSet.addMatch(node, POSSIBLE_MATCH);
		
		if (matchesName(this.pattern.selector, ((CallExpression) node).getName().toCharArray()))
			return nodeSet.addMatch(node, ACCURATE_MATCH);
		
		
		return IMPOSSIBLE_MATCH;
	}
}
