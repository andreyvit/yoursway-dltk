package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Statement;

public class RubyArrayAccessExpression extends Expression {

	private Statement array;
	private RubyCallArgumentsList args;

	public RubyArrayAccessExpression(int start, int end) {
		super(start, end);
	}

	public RubyArrayAccessExpression(int start, int end, Statement array,
			RubyCallArgumentsList args) {
		super(start, end);
		this.array = array;
		this.args = args;
	}

	public Statement getArray() {
		return array;
	}

	public void setArray(Statement array) {
		this.array = array;
	}

	public RubyCallArgumentsList getArgs() {
		return args;
	}

	public void setArgs(RubyCallArgumentsList args) {
		this.args = args;
	}

	public int getKind() {
		return 0;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (array != null)
				array.traverse(visitor);
			if (args != null)
				args.traverse(visitor);
			visitor.endvisit(this);
		}
	}

}
