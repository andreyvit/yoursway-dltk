package org.eclipse.dltk.ast.references;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.utils.CorePrinter;

/**
 * Holds call arguments. Used to ExtendedVariableReference holding.
 * 
 * @author haiodo
 * 
 */
public class CallHolder extends Expression {
	/**
	 * Can be EmptyExpression if no arguments are specified. Overwise it
	 * possible list of expressions, if only one argument too.
	 */
	private Expression fArguments = null;

	/**
	 * Construct from position information and arguments.
	 * 
	 * @param start -
	 *            start
	 * @param end
	 * @param arguments
	 */
	public CallHolder(int start, int end, Expression arguments) {

		super(start, end);
		this.fArguments = arguments;
	}

	/**
	 * Return kind.
	 */
	public int getKind() {
		return Expression.E_CALL;
	}

	/**
	 * Traverse.
	 */
	public void traverse(ASTVisitor pVisitor) throws Exception {
		if (pVisitor.visit(this)) {
			if (this.fArguments != null) {
				this.fArguments.traverse(pVisitor);
			}
			pVisitor.endvisit(this);
		}
	}

	public Expression getArguments() {
		return fArguments;
	}

	/**
	 * Testing purpose only. Used to print "call".
	 */
	public void printNode(CorePrinter output) {
		output.formatPrintLn("( ");
		if (this.fArguments != null) {
			this.fArguments.printNode(output);
		}
		output.formatPrintLn(" )");
	}
}
