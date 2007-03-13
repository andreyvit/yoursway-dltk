package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Statement;

public class IterableBlock extends Expression {

	private final Statement var;
	private final Statement body;

	public IterableBlock(int start, int end, Statement var, Statement body) {
		super(start, end);
		this.var = var;
		this.body = body;
	}

	public Statement getVar() {
		return var;
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
			if (var != null)
				var.traverse(visitor);
			if (body != null)
				body.traverse(visitor);
			visitor.endvisit(this);
		}
	}

}
