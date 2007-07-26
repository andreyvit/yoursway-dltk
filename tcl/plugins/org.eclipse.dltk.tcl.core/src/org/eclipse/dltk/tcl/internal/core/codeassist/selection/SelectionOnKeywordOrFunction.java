/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.core.codeassist.selection;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.references.SimpleReference;

public class SelectionOnKeywordOrFunction extends SimpleReference {
	private ASTNode originalNode;
	public SelectionOnKeywordOrFunction(String completionToken,
			ASTNode completionNode, ASTNode node) {
		super(completionNode.sourceStart(), completionNode.sourceEnd(),
				completionToken);
		this.originalNode = node;
	}
	public ASTNode getOriginalNode() {
		return this.originalNode;
	}
}
