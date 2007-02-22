package org.eclipse.dltk.ast.statements;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.DLTKToken;
import org.eclipse.dltk.utils.CorePrinter;

/**
 * Try Finally statement.
 */
public class TryFinallyStatement extends Statement {
	private Block fBody;

	public TryFinallyStatement(DLTKToken token, Block body) {
		super(token);
		this.fBody = body;
	}

	public int getKind() {
		return Statement.S_TRY_FINALLY;
	}

	public void traverse(ASTVisitor pVisitor) throws Exception {
		if (pVisitor.visit(this)) {
			if (this.fBody != null) {
				this.fBody.traverse(pVisitor);
			}
			pVisitor.endvisit(this);
		}

	}

	public void printNode(CorePrinter output) {
		output.formatPrintLn("finally:");
		if (this.fBody != null) {
			this.fBody.printNode(output);
		}
	}
}
