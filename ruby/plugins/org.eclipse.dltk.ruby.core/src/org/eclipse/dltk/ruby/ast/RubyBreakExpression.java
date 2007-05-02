package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;

public class RubyBreakExpression extends ASTNode {

	private final ASTNode value;

	public ASTNode getValue() {
		return value;
	}

	public RubyBreakExpression(int start, int end, ASTNode value) {
		super(start, end);
		this.value = value;
	}

	public int getKind() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (value != null)
				value.traverse(visitor);
			visitor.endvisit(this);
		}
	}

}
