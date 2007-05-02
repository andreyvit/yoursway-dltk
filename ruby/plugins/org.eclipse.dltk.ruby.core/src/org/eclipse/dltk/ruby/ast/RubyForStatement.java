package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.utils.CorePrinter;

public class RubyForStatement extends ASTNode {

	private ASTNode expression;
	private RubyBlock block;

	public RubyForStatement(int start, int end) {
		super(start, end);
	}
	
	

	public RubyForStatement(int start, int end, ASTNode expression,
			RubyBlock block) {
		super(start, end);
		this.expression = expression;
		this.block = block;
	}



	public ASTNode getExpression() {
		return expression;
	}

	public void setExpression(ASTNode expression) {
		this.expression = expression;
	}

	public RubyBlock getBlock() {
		return block;
	}

	public void setBlock(RubyBlock block) {
		this.block = block;
	}

	public int getKind() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void printNode(CorePrinter output) {
		// TODO Auto-generated method stub

	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (expression != null)
				expression.traverse(visitor);
			if (block != null)
				block.traverse(visitor);
			visitor.endvisit(this);
		}
	}

}
