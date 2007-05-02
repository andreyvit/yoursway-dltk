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

public class RubyArrayAccessExpression extends ASTNode {

	private ASTNode array;
	private RubyCallArgumentsList args;

	public RubyArrayAccessExpression(int start, int end) {
		super(start, end);
	}

	public RubyArrayAccessExpression(int start, int end, ASTNode array,
			RubyCallArgumentsList args) {
		super(start, end);
		this.array = array;
		this.args = args;
	}

	public ASTNode getArray() {
		return array;
	}

	public void setArray(ASTNode array) {
		this.array = array;
	}

	public RubyCallArgumentsList getArgs() {
		return args;
	}

	public void setArgs(RubyCallArgumentsList args) {
		this.args = args;
	}

	public int getKind() {
		return 0;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (array != null)
				array.traverse(visitor);
			if (args != null)
				args.traverse(visitor);
			visitor.endvisit(this);
		}
	}

}
