package org.eclipse.dltk.ast.expressions;

import org.eclipse.dltk.ast.DLTKToken;
import org.eclipse.dltk.utils.CorePrinter;

/**
 * 
 * Numeric literal. Used to hold ints, floats and complex numbers.
 * 
 */
public class NumericLiteral extends Literal {

	/**
	 * Construct from ANTLR token. With appropriate position information.
	 * 
	 * @param number
	 */
	public NumericLiteral(DLTKToken number) {
		super(number);
	}

	/**
	 * Return kind.
	 */

	public int getKind() {
		return NUMBER_LITERAL;
	}

	/**
	 * Testing purposes only. Used to print number.
	 */

	public void printNode(CorePrinter output) {
		output.formatPrintLn(this.getValue());
	}
}
