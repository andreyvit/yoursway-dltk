package org.eclipse.dltk.ast.references;

import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.UnaryExpression;
import org.eclipse.dltk.utils.CorePrinter;

/**
 * Curly expression.
 */
public class Curly extends UnaryExpression {
	/**
	 * Construct curly.
	 * 
	 * @param start
	 * @param end
	 * @param e
	 */
	public Curly(int start, int end, Expression e) {
		super(start, end, E_CURLY, e);
	}

	/**
	 * Testing purposes only. Print curly with expression.
	 */
	public void printNode(CorePrinter output) {
		output.formatPrintLn(" { ");
		if (this.expression != null) {
			this.expression.printNode(output);
		}
		output.formatPrintLn(" } ");
	}
}
