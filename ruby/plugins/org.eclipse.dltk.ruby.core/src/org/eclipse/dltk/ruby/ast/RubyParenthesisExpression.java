package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.CompoundStatement;

public class RubyParenthesisExpression extends Expression {

	private CompoundStatement internals;

	public RubyParenthesisExpression(int start, int end,
			CompoundStatement internals) {
		super(start, end);
		this.internals = internals;
	}

	public CompoundStatement getInternals() {
		return internals;
	}

	public void setInternals(CompoundStatement internals) {
		this.internals = internals;
	}

	public int getKind() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (internals != null)
				internals.traverse(visitor);
			visitor.endvisit(this);
		}
	}

}
