package org.eclipse.dltk.ast.statements;

import org.eclipse.dltk.ast.DLTKToken;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.utils.CorePrinter;

/**
 * For each statement.
 * 
 */
public class ForEachStatement extends ForStatement {

	/**
	 * Construct foreach from ANTLR token, initialization, condition, increment
	 * expressions and action statement. foreach( A, C, I ) { Action. }
	 * 
	 * @param forToken -
	 *            ANTLR token.
	 * @param initialization -
	 *            initalization expression.
	 * @param condition -
	 *            condition expression.
	 * @param increment -
	 *            increment expression.
	 * @param action -
	 *            action statement.
	 */
	public ForEachStatement(DLTKToken forToken, Expression initialization,
			Expression condition, Expression increment, Statement action) {
		super(forToken, initialization, condition, increment, action);
	}

	/**
	 * Testing purposes only. Print foreach statement.
	 */
	public void printNode(CorePrinter output) {
		output.formatPrintLn("foreach: ");

		Expression condition = this.getCondition();
		Expression increment = this.getIncrement();
		Expression intialization = this.getInitialization();
		Statement action = this.getAction();

		if (condition != null) {
			output.formatPrintLn("condition:");
			condition.printNode(output);
		}
		if (increment != null) {
			output.formatPrintLn("increment:");
			increment.printNode(output);
		}
		if (intialization != null) {
			output.formatPrintLn("initialization:");
			intialization.printNode(output);
		}
		if (action != null) {
			output.indent();
			action.printNode(output);
			output.dedent();
		}
	}
}
