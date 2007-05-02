package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.ASTListNode;

public class RubyParenthesisExpression extends ASTNode {

	private ASTListNode internals;

	public RubyParenthesisExpression(int start, int end,
			ASTListNode internals) {
		super(start, end);
		this.internals = internals;
	}

	public ASTListNode getInternals() {
		return internals;
	}

	public void setInternals(ASTListNode internals) {
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
