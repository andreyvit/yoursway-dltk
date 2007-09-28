package org.eclipse.dltk.tcl.core.ast;

import java.util.List;

import org.eclipse.dltk.ast.ASTListNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.ast.statements.Statement;

public class TclForeachStatement extends Statement {
	
	ASTListNode arguments = null; //contains binary expressions (<varList>, <value list>)
	Block block = null;
	
	public TclForeachStatement(int startPos, int endPos) {
		this.setStart(startPos);
		this.setEnd(endPos);
	}

	public int getKind() {
		return S_FOREACH;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (null != this.arguments) arguments.traverse(visitor);
			if (null != this.block) block.traverse(visitor);
			visitor.endvisit(this);
		}
	}
	
	/**
	 * @param list - the first and the last nodes must be as in source 
	 * */
	public void setArguments(List /*<BinaryExpression>*/ list) {
		if (!list.isEmpty()) {
			int start = ((BinaryExpression)list.get(0)).sourceStart();
			int end = ((BinaryExpression)list.get(list.size()-1)).sourceEnd();
			arguments = new ASTListNode(start, end, list);
		}
	}
	
	public ASTListNode getArguments() {
		return arguments;
	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}
}
