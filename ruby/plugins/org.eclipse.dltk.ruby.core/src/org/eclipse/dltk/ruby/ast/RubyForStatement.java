package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.utils.CorePrinter;

public class RubyForStatement extends Statement {

	private Statement expression;
	private RubyBlock block;

	public RubyForStatement(int start, int end) {
		super(start, end);
	}
	
	

	public RubyForStatement(int start, int end, Statement expression,
			RubyBlock block) {
		super(start, end);
		this.expression = expression;
		this.block = block;
	}



	public Statement getExpression() {
		return expression;
	}

	public void setExpression(Statement expression) {
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
		// TODO Auto-generated method stub

	}

}
