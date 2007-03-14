package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Statement;

public class RubyBlockPassExpression extends Expression {

	private final Statement args;
	private final Statement body;

	public Statement getArgs() {
		return args;
	}

	public Statement getBody() {
		return body;
	}

	public RubyBlockPassExpression(int start, int end, Statement args,
			Statement body) {
		super(start, end);
		this.args = args;
		this.body = body;
	}

	public int getKind() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (args != null)
				args.traverse(visitor);
			if (body != null)
				body.traverse(visitor);
			visitor.endvisit(this);
		}
	}

}
