package org.eclipse.dltk.ast.expressions;

import org.eclipse.dltk.ast.ASTVisitor;

/**
 * Empty Expression node. May be used as void return type.
 */
public class EmptyExpression extends Expression {
	public EmptyExpression() {
	}

	public int getKind() {
		return E_EMPTY;
	}

	public void traverse(ASTVisitor pVisitor) throws Exception {
		if (pVisitor.visit(this)) {
			pVisitor.endvisit(this);
		}
	}
}
