package org.eclipse.dltk.xotcl.core.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.ast.statements.Statement;

public class TclCatchStatement extends Statement {
	private Block block;
	public TclCatchStatement(Block block, int start, int end) {
		super( start, end );
		this.block = block;
	}
	public int getKind() {
		return 0;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if( visitor.visit(this) ) {
			if( this.block != null ) {
				this.block.traverse(visitor);
			}
			visitor.endvisit(this);
		}
	}
}
