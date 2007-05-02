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

public class RubyEnsureExpression extends ASTNode {

	private final ASTNode ensure;
	private final ASTNode body;



	public ASTNode getEnsure() {
		return ensure;
	}

	public ASTNode getBody() {
		return body;
	}

	public RubyEnsureExpression(int start, int end, ASTNode ensure, ASTNode body) {
		super(start, end);
		this.ensure = ensure;
		this.body = body;
	}

	public int getKind() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (ensure != null)
				ensure.traverse(visitor);
			if (body != null)
				body.traverse(visitor);
			visitor.endvisit(this);
		}
	}

}
