package org.eclipse.dltk.ast.statements;

import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.DLTKToken;
import org.eclipse.dltk.utils.CorePrinter;

/**
 * Try statement.
 */
public class TryStatement extends Statement {
	private Block body;

	private List catchFinallyStatements;

	protected TryStatement(int start, int end) {
		super(start, end);
	}

	public TryStatement(DLTKToken tryToken, Block body, List catchFin) {

		super(tryToken);
		this.body = body;
		this.catchFinallyStatements = catchFin;
	}

	public void traverse(ASTVisitor pVisitor) throws Exception {
		if (pVisitor.visit(this)) {
			if (this.body != null) {
				this.body.traverse(pVisitor);
			}
			if (this.catchFinallyStatements != null) {
				Iterator i = this.catchFinallyStatements.iterator();
				while (i.hasNext()) {
					Statement st = (Statement) i.next();
					st.traverse(pVisitor);
				}
			}
			pVisitor.endvisit(this);
		}
	}

	public int getKind() {
		return S_TRY;
	}

	public Block getBody() {
		return body;
	}

	public List getCatchFinallyStatements() {
		return catchFinallyStatements;
	}

	public void printNode(CorePrinter output) {
		output.formatPrint("try:");
		if (this.body != null) {
			this.body.printNode(output);
		}
		if (this.catchFinallyStatements != null) {
			Iterator i = this.catchFinallyStatements.iterator();
			while (i.hasNext()) {
				Statement st = (Statement) i.next();
				st.printNode(output);
				output.println("");
			}
		}
	}
}
