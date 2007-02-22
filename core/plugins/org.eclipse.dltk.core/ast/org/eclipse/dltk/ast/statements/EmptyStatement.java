package org.eclipse.dltk.ast.statements;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.DLTKToken;
import org.eclipse.dltk.utils.CorePrinter;

/**
 * Empty Statement.
 * 
 */
public class EmptyStatement extends Statement {

	public EmptyStatement(int start, int end) {
		super(start, end);
	}

	public EmptyStatement(DLTKToken token) {
		super(token);
	}

	public int getKind() {
		return S_EMPTY;
	}

	public void traverse(ASTVisitor pVisitor) throws Exception {
		if (pVisitor.visit(this)) {
			pVisitor.endvisit(this);
		}
	}

	public void printNode(CorePrinter output) {
	}
}
