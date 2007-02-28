package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.utils.CorePrinter;

public class ReturnStatement extends Statement {

	
	private final ASTNode value;
	private final int startOffset;
	private final int endOffset;

	public ReturnStatement(ASTNode value, int startOffset, int endOffset) {
		this.value = value;
		this.startOffset = startOffset;
		this.endOffset = endOffset;
	}

	public int getKind() {
		return 0;
	}

	public void printNode(CorePrinter output) {
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (value != null) {
				value.traverse(visitor);
			}
			visitor.endvisit(this);
		}
	}

	public ASTNode getValue() {
		return value;
	}

	public int getStartOffset() {
		return startOffset;
	}

	public int getEndOffset() {
		return endOffset;
	}

}
