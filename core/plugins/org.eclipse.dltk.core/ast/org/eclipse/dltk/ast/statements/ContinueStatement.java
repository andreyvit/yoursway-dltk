package org.eclipse.dltk.ast.statements;

import org.eclipse.dltk.ast.DLTKToken;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.utils.CorePrinter;

public class ContinueStatement extends SimpleStatement {

	public ContinueStatement(DLTKToken continueToken, DLTKToken labelToken,
			int end) {
		super(continueToken.getColumn(), end, null);
		if (labelToken != null) {
			this.fExpression = new SimpleReference(labelToken);
			this.setEnd(fExpression.sourceEnd());
		}
	}

	public int getKind() {
		return S_CONTINUE;
	}

	public void printNode(CorePrinter output) {
		output.formatPrintLn("continue ");

		Expression expression = this.getExpression();
		if (expression != null) {
			expression.printNode(output);
		}
	}
}
