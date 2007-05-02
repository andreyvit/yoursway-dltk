package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.CallArgumentsList;

public class RubySuperExpression extends ASTNode {

	private ASTNode block;
	private CallArgumentsList args;

	public RubySuperExpression(int start, int end, CallArgumentsList args, ASTNode b) {
		super(start, end);
		this.args = args;
		this.block = b;
	}

	public ASTNode getBlock() {
		return block;
	}

	public void setBlock(ASTNode block) {
		this.block = block;
	}
	
	public CallArgumentsList getArgs() {
		return args;
	}

	public void setArgs(CallArgumentsList args) {
		this.args = args;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (getArgs() != null) {
				getArgs().traverse(visitor);
			}

			if (block != null) {
				block.traverse(visitor);
			}

			visitor.endvisit(this);
		}
	}

}
