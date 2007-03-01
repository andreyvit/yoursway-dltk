/*
 * (c) 2002, 2005 xored software and others all rights reserved. http://www.xored.com
 */
package org.eclipse.dltk.ast.expressions;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.utils.CorePrinter;

/**
 * Base binary class for binary Expressions.
 */
public class BinaryExpression extends Expression {
	private Expression left;
	private Statement right;
	
	protected int kind;

	public BinaryExpression(Expression left, int kind, Statement right) {
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

	public Expression getLeft() {
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
