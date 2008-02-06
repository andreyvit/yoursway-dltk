package org.eclipse.dltk.tcl.core.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.ast.statements.Statement;

public class TclWhileStatement extends Statement {
	private Expression test = null;
	private Block block = null;

	public TclWhileStatement(int start, int end) {
		super(start, end);
	}

	public int getKind() {
		return S_FOREACH;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (this.test != null) {
				this.test.traverse(visitor);
			}
			if (this.block != null) {
				block.traverse(visitor);
			}
			visitor.endvisit(this);
		}
	}

	public Expression getTest() {
		return this.test;
	}

	public Block getBlock() {
		return block;
	}

	public void acceptBlock(Block block) {
		this.block = block;
	}

	public void setTest(Expression test) {
		this.test = test;		
	}
}
