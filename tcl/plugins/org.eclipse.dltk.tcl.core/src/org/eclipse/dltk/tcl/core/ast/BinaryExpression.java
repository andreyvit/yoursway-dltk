/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *

 *******************************************************************************/
/*
 * (c) 2002, 2005 xored software and others all rights reserved. http://www.xored.com
 */
package org.eclipse.dltk.tcl.core.ast;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.utils.CorePrinter;

/**
 * Base binary class for binary Expressions.
 */
public class BinaryExpression extends Expression {
	private ASTNode left;
	private ASTNode right;

	protected int kind;

	public BinaryExpression(ASTNode left, int kind, ASTNode right) {
		if (left != null) {
			this.setStart(left.sourceStart());
		}

		if (right != null) {
			this.setEnd(right.sourceEnd());
		}

		this.kind = kind;
		this.left = left;
		this.right = right;
	}

	public ASTNode getLeft() {
		return this.left;
	}

	public ASTNode getRight() {
		return this.right;
	}

	public void traverse(ASTVisitor pVisitor) throws Exception {
		if (pVisitor.visit(this)) {
			if (this.left != null) {
				this.left.traverse(pVisitor);
			}

			if (this.right != null) {
				this.right.traverse(pVisitor);
			}

			pVisitor.endvisit(this);
		}
	}

	public String toString() {
		String out = "";
		if (this.left != null) {
			out += this.left.toString();
		}

		out += this.getOperator();
		if (this.right != null) {
			out += this.right.toString();
		}

		return out;
	}

	public int getKind() {
		return this.kind;
	}

	public void printNode(CorePrinter output) {
		if (this.left != null) {
			this.left.printNode(output);
		}

		output.formatPrintLn(this.getOperator());

		if (this.right != null) {
			this.right.printNode(output);
		}
	}
}
