package org.eclipse.dltk.tcl.core.ast;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.ast.statements.Statement;

public class TclForStatement extends Statement {

	private Block start;
	private ASTNode test;
	private Block next;
	private Block body;
	
	public TclForStatement(Block start, ASTNode test, Block next, Block body, int startPos, int endPos) {
		Assert.isNotNull(start);
		Assert.isNotNull(test);
		Assert.isNotNull(next);
		Assert.isNotNull(body);
		this.start = start;
		this.test = test;
		this.next = next;
		this.body = body;
		this.setEnd(endPos);
		this.setStart(startPos);
	}
	
	public TclForStatement(int startPos, int endPos) {
		this.setEnd(endPos);
		this.setStart(startPos);
		this.start = null;
		this.test = null;
		this.next = null;
		this.body = null;
	}
	
	public int getKind() {
		return S_FOR;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (null != start) start.traverse(visitor);
			if (null != test)  test.traverse(visitor);
			if (null != next) next.traverse(visitor);
			if (null != body) body.traverse(visitor);
			visitor.endvisit(this);
		}
	}

	public Block getStart() {
		return start;
	}

	public ASTNode getTest() {
		return test;
	}

	public Block getNext() {
		return next;
	}

	public Block getBody() {
		return body;
	}

	public void setStart(Block start) {
		Assert.isNotNull(start);
		this.start = start;
	}

	public void setTest(ASTNode test) {
		Assert.isNotNull(test);
		this.test = test;
	}

	public void setNext(Block next) {
		Assert.isNotNull(next);
		this.next = next;
	}

	public void setBody(Block body) {
		Assert.isNotNull(body);
		this.body = body;
	}

}
