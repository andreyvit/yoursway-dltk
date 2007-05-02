package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;

public class RubyEnsureExpression extends ASTNode {

	private final ASTNode ensure;
	private final ASTNode body;



	public ASTNode getEnsure() {
		return ensure;
	}

	public ASTNode getBody() {
		return body;
	}

	public RubyEnsureExpression(int start, int end, ASTNode ensure, ASTNode body) {
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
