package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Statement;

public class RubyEnsureExpression extends Expression {

	private final Statement ensure;
	private final Statement body;



	public Statement getEnsure() {
		return ensure;
	}

	public Statement getBody() {
		return body;
	}

	public RubyEnsureExpression(int start, int end, Statement ensure,
			Statement body) {
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
