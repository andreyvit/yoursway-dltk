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
package org.eclipse.dltk.python.parser.ast.expressions;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.utils.CorePrinter;

/**
 * Base binary class for binary Expressions.
 */
public class BinaryExpression extends Expression {
	private Statement left;
	private Statement right;
	
	protected int kind;

	public BinaryExpression(Statement left, int kind, Statement right) {
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

	public Statement getLeft() {
		return left;
	}

	public Statement getRight() {
		return right;
	}

	public void traverse(ASTVisitor pVisitor) throws Exception {
		if (pVisitor.visit(this)) {
			if (left != null) {
				left.traverse(pVisitor);
			}

			if (right != null) {
				right.traverse(pVisitor);
			}

			pVisitor.endvisit(this);
		}
	}

	public String toString() {
		String out = "";
		if (left != null) {
			out += left.toString();
		}

		out += this.getOperator();
		if (right != null) {
			out += this.right.toString();
		}

		return out;
	}

	public int getKind() {
		return kind;
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
