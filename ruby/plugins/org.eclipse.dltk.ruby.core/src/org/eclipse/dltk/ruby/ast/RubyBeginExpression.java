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

public class RubyBeginExpression extends ASTNode {

	private final ASTNode body;

	public ASTNode getBody() {
		return body;
	}

	public RubyBeginExpression(int start, int end, ASTNode body) {
		super(start, end);
		this.body = body;
	}

	public int getKind() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (body != null)
				body.traverse(visitor);
			visitor.endvisit(this);
		}
	}

}
