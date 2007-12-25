/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.codeassist.select;

import org.eclipse.dltk.ast.ASTNode;

public class SelectionNodeFound extends RuntimeException {
	
	private static final long serialVersionUID = -4242506971248812583L;
	private transient ASTNode node;	
	private boolean isDeclaration;

	public SelectionNodeFound(ASTNode node) {
		this.node = node;
	}
	
	public SelectionNodeFound( boolean isDeclaration) {
		this.isDeclaration = isDeclaration;
	}

	public ASTNode getNode() {
		return node;
	}
	
	public boolean isDeclaration () {
		return this.isDeclaration;
	}
}
