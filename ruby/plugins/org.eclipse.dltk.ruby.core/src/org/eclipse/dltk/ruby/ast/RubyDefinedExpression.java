/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;

public class RubyDefinedExpression extends ASTNode {

	private final ASTNode value;

	public ASTNode getValue() {
		return value;
	}

	public RubyDefinedExpression(int start, int end, ASTNode value) {
		super(start, end);
		this.value = value;
	}

	public int getKind() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (value != null)
				value.traverse(visitor);
			visitor.endvisit(this);
		}
	}

}
