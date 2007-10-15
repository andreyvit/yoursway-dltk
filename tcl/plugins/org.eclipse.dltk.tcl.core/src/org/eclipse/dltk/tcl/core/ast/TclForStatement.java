package org.eclipse.dltk.tcl.core.ast;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.ast.statements.Statement;

public class TclForStatement extends Statement {

	private Block initial;
	private ASTNode condition;
	private Block incremental;
	private Block body;

	public TclForStatement(Block start, ASTNode test, Block next, Block body,
			int startPos, int endPos) {
		Assert.isNotNull(start);
		Assert.isNotNull(test);
		Assert.isNotNull(next);
		Assert.isNotNull(body);
		this.initial = start;
		this.condition = test;
		this.incremental = next;
		this.body = body;
		this.setEnd(endPos);
		this.setStart(startPos);
	}

	public TclForStatement(int startPos, int endPos) {
		this.setEnd(endPos);
		this.setStart(startPos);
		this.initial = null;
		this.condition = null;
		this.incremental = null;
		this.body = null;
	}

	public int getKind() {
		return S_FOR;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (null != initial) {
				initial.traverse(visitor);
			}
			if (null != condition) {
				condition.traverse(visitor);
			}
			if (null != incremental) {
				incremental.traverse(visitor);
			}
			if (null != body) {
				body.traverse(visitor);
			}
			visitor.endvisit(this);
		}
	}

	public Block getInitial() {
		return initial;
	}

	public ASTNode getCondition() {
		return condition;
	}

	public Block getIncremental() {
		return incremental;
	}

	public Block getBlock() {
		return body;
	}

	public void setInitial(Block start) {
		this.initial = start;
	}

	public void setCondition(ASTNode test) {
		this.condition = test;
	}

	public void setIncremental(Block next) {
		this.incremental = next;
	}

	public void acceptBlock(Block body) {
		this.body = body;
	}

}
