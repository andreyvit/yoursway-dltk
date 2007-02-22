package org.eclipse.dltk.ast.statements;

import org.eclipse.dltk.ast.DLTKToken;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.utils.CorePrinter;

/**
 * Throw statement. Exception throwing.
 */
public class ThrowStatement extends SimpleStatement {
	public ThrowStatement(DLTKToken throwToken, Expression expression) {
		super(throwToken.getColumn(), -1, expression);
		if (expression != null) {
			this.setEnd(expression.sourceEnd());
		}
	}

	public int getKind() {
		return S_THROW;
	}

	public void printNode(CorePrinter output) {
		output.formatPrintLn("throw: ");
		Expression expression = this.getExpression();
		if (expression != null) {
			expression.printNode(output);
		}
	}
}
