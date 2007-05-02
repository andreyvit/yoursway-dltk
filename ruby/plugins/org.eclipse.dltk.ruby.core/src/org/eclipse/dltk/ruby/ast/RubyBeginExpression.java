package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;

public class RubyBeginExpression extends ASTNode {

	private final ASTNode body;

	public ASTNode getBody() {
		return body;
	}

	public RubyBeginExpression(int start, int end, ASTNode body) {
		super(start, end);
		this.body = body;
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
