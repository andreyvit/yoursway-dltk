package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;

public class RubyDotExpression extends ASTNode {

	private final ASTNode begin;
	private final ASTNode end;
	private boolean exclusive;

	public ASTNode getBegin() {
		return begin;
	}

	public ASTNode getEnd() {
		return end;
	}

	public RubyDotExpression(int start, int end, ASTNode begin, ASTNode end2) {
		super(start, end);
		this.begin = begin;
		this.end = end2;
		exclusive = false;
	}
	
	

	public RubyDotExpression(ASTNode begin, ASTNode end2, boolean exclusive) {
		super(begin.sourceStart(), end2.sourceEnd());
		this.begin = begin;
		this.end = end2;
		this.exclusive = exclusive;
	}
	
	

	public boolean isExclusive() {
		return exclusive;
	}

	public void setExclusive(boolean exclusive) {
		this.exclusive = exclusive;
	}

	public int getKind() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (begin != null)
				begin.traverse(visitor);
			if (end != null)
				end.traverse(visitor);
			visitor.endvisit(this);
		}
	}

}
