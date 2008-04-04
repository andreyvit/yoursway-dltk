package org.eclipse.dltk.tcl.core.ast;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.ast.statements.Statement;

public class TclSwitchStatement extends Statement {

	private ASTNode fPattern;
	private Block fAlternatives = new Block();

	public TclSwitchStatement(int startPos, int endPos) {
		this.setStart(startPos);
		this.setEnd(endPos);
	}

	public TclSwitchStatement(ASTNode string, int startPos, int endPos) {
		this.fPattern = string;
		this.setStart(startPos);
		this.setEnd(endPos);
	}

	public void setString(ASTNode string) {
		this.fPattern = string;
	}

	public int getKind() {
		return this.S_SWITCH;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (null != fPattern) {
				fPattern.traverse(visitor);
			}
			if (null != fAlternatives) {
				fAlternatives.traverse(visitor);
			}
			visitor.endvisit(this);
		}
	}

	public ASTNode getString() {
		return this.fPattern;
	}

	public Block getAlternatives() {
		return this.fAlternatives;
	}
	public void acceptBlock(Block bl) {
		this.fAlternatives = bl;
	}

	public void addChild(Block block) {
		this.fAlternatives.getStatements().add(block);
	}
}
