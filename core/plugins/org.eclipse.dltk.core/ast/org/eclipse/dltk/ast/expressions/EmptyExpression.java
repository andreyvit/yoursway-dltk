/*
 * (c) 2002, 2005 xored software and others all rights reserved. http://www.xored.com
 */
package org.eclipse.dltk.ast.expressions;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.utils.CorePrinter;

/**
 * Empty Expression node. May be used as viod return type.
 */
public class EmptyExpression extends Expression {
	/**
	 * Construct empty expression.
	 */
	public EmptyExpression() {
	}

	/**
	 * Return kind.
	 */
	public int getKind() {
		return E_EMPTY;
	}

	/**
	 * Traverse.
	 */
	public void traverse(ASTVisitor pVisitor) throws Exception {
		if (pVisitor.visit(this)) {
			pVisitor.endvisit(this);
		}
	}

	/**
	 * Testing purposes only. No needed printing.
	 */
	public void printNode(CorePrinter output) {
	}
}
