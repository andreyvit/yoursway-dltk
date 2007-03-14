package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;

public class RubyDVarExpression extends Expression {

	private final String name;

	public String getName() {
		return name;
	}

	public RubyDVarExpression(int start, int end, String name) {
		super(start, end);
		this.name = name;
	}

	public int getKind() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			visitor.endvisit(this);
		}
	}

}
