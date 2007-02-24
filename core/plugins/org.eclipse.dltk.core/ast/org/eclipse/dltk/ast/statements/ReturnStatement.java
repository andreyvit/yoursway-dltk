package org.eclipse.dltk.ast.statements;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.DLTKToken;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.utils.CorePrinter;

/**
 * Return statement.
 */
public class ReturnStatement extends SimpleStatement {
	public ReturnStatement(DLTKToken rt, Expression exp, int end) {
		super(rt.getColumn(), end, exp);
	}

	

	public int getKind() {
		return S_RETURN;
	}

	public void printNode(CorePrinter output) {
		output.formatPrintLn("return ");

		Expression expression = this.getExpression();
		if (expression != null) {
			expression.printNode(output);
		}
	}
}
