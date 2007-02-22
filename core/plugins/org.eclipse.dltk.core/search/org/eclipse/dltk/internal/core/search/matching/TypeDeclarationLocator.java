/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.core.search.matching;

import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.compiler.CharOperation;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.search.matching.PatternLocator;

public class TypeDeclarationLocator extends PatternLocator {
	protected TypeDeclarationPattern pattern; // can be a
												// QualifiedTypeDeclarationPattern

	public TypeDeclarationLocator(TypeDeclarationPattern pattern) {
		super(pattern);
		this.pattern = pattern;
	}

	// public int match(ASTNode node, MatchingNodeSet nodeSet) - SKIP IT
	// public int match(ConstructorDeclaration node, MatchingNodeSet nodeSet) -
	// SKIP IT
	// public int match(Expression node, MatchingNodeSet nodeSet) - SKIP IT
	// public int match(FieldDeclaration node, MatchingNodeSet nodeSet) - SKIP
	// IT
	// public int match(MethodDeclaration node, MatchingNodeSet nodeSet) - SKIP
	// IT
	// public int match(MessageSend node, MatchingNodeSet nodeSet) - SKIP IT
	// public int match(Reference node, MatchingNodeSet nodeSet) - SKIP IT
	public int match(TypeDeclaration node, MatchingNodeSet nodeSet) {
		if (this.pattern.simpleName == null || matchesName(this.pattern.simpleName, node.getName().toCharArray())) {
			
			//	fully qualified name
			if (this.pattern instanceof QualifiedTypeDeclarationPattern) {
//				QualifiedTypeDeclarationPattern qualifiedPattern = (QualifiedTypeDeclarationPattern) this.pattern;
//				return resolveLevelForType(qualifiedPattern.simpleName, qualifiedPattern.qualification, node);
			} else {
				char[] enclosingTypeName = this.pattern.enclosingTypeNames == null ? null : CharOperation.concatWith(this.pattern.enclosingTypeNames, '$');
				char[] enclosingNodeTypeName = node.getEnclosingTypeName().toCharArray();
				if( !matchesName(enclosingTypeName, enclosingNodeTypeName)) {
					return IMPOSSIBLE_MATCH;
				}
			}
			if (DLTKCore.DEBUG) {
				System.err.println("TODO: Check here, may be needed POSSIBLE_PATCH...");
			}
			return nodeSet.addMatch(node, ACCURATE_MATCH);
		}
		return IMPOSSIBLE_MATCH;
	}

	public String toString() {
		return "Locator for " + this.pattern.toString(); //$NON-NLS-1$
	}
}
