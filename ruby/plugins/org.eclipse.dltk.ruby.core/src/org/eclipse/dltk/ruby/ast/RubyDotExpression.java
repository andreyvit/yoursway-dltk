package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Statement;

public class RubyDotExpression extends Expression {

	private final Statement begin;
	private final Statement end;

	public Statement getBegin() {
		return begin;
	}

	public Statement getEnd() {
		return end;
	}

	public RubyDotExpression(int start, int end, Statement begin, Statement end2) {
		super(start, end);
		this.begin = begin;
		this.end = end2;
	}

	public int getKind() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (begin != null)
				begin.traverse(visitor);
			if (end != null)
				end.traverse(visitor);
			visitor.endvisit(this);
		}
	}

}
