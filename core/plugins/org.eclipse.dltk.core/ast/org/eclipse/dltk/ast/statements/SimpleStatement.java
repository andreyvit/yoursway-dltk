package org.eclipse.dltk.ast.statements;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.DLTKToken;
import org.eclipse.dltk.ast.expressions.Expression;

/**
 * Abstract superclass for all simple statement;
 */
public abstract class SimpleStatement extends Statement {

	protected Expression fExpression;

	protected SimpleStatement(int start, int end, Expression expression) {
		super(start, end);
		this.fExpression = expression;
	}

	public SimpleStatement(DLTKToken token, Expression expression) {
		super(token);
		this.fExpression = expression;
	}

	public void traverse(ASTVisitor pVisitor) throws Exception {
		if (pVisitor.visit(this)) {
			if (fExpression != null) {
				fExpression.traverse(pVisitor);
			}
			pVisitor.endvisit(this);
		}
	}

	public Expression getExpression() {
		return fExpression;
	}
}
