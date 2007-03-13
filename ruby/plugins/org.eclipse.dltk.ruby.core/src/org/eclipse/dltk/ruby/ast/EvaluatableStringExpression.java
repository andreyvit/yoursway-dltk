package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Statement;

public class EvaluatableStringExpression extends Expression {
	
	private final Statement body;
	
	public EvaluatableStringExpression(int start, int end, Statement body) {
		super(start, end);
		this.body = body;
	}

	public Statement getBody() {
		return body;
	}

	public int getKind() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (body != null)
				body.traverse(visitor);
			visitor.endvisit(this);
		}
	}

}
