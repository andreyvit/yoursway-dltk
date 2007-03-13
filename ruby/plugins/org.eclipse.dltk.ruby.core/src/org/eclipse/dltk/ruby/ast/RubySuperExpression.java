package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.CallArgumentsList;
import org.eclipse.dltk.ast.expressions.SuperExpression;
import org.eclipse.dltk.ast.statements.Statement;

public class RubySuperExpression extends SuperExpression {

	private Statement block;

	public RubySuperExpression(int start, int end, CallArgumentsList args,
			Statement b) {
		super(start, end, args);
		this.block = b;
	}

	public Statement getBlock() {
		return block;
	}

	public void setBlock(Statement block) {
		this.block = block;
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
