package org.eclipse.dltk.tcl.core.ast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.ast.statements.Statement;

public class TclForeachStatement extends Statement {
	List arguments = null;
	Block block = null;

	public TclForeachStatement(int start, int end) {
		super(start, end);
	}

	public int getKind() {
		return S_FOREACH;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (this.arguments != null) {
				for (Iterator iterator = this.arguments.iterator(); iterator
						.hasNext();) {
					ASTNode node = (ASTNode) iterator.next();
					node.traverse(visitor);
				}
			}
			if (this.block != null) {
				block.traverse(visitor);
			}
			visitor.endvisit(this);
		}
	}

	public List getArguments() {
		if( arguments == null ) {
			arguments = new ArrayList();
		}
		return arguments;
	}

	public Block getBlock() {
		return block;
	}

	public void acceptBlock(Block block) {
		this.block = block;
	}
}
