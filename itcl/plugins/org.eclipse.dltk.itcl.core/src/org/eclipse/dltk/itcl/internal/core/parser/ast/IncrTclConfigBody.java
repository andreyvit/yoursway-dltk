package org.eclipse.dltk.itcl.internal.core.parser.ast;

import java.util.List;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.Declaration;
import org.eclipse.dltk.ast.statements.Block;

public class IncrTclConfigBody extends Declaration {
	private Block body;

	public IncrTclConfigBody(int start, int end) {
		super(start, end);
	}

	public void acceptBody(Block block) {
		this.acceptBody(block, true);
	}

	public void acceptBody(Block block, boolean replace) {
		this.body = block;

		if (block != null) {
			if (replace) {
				this.setEnd(block.sourceEnd());
			}
		}
	}

	public List getStatements() {
		if (this.body == null) {
			this.body = new Block(this.sourceStart(), this.sourceEnd());
		}
		return this.body.getStatements();
	}

	public Block getBody() {
		return this.body;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			// Body
			if (this.body != null) {
				this.body.traverse(visitor);
			}
			visitor.endvisit(this);
		}
	}
}
