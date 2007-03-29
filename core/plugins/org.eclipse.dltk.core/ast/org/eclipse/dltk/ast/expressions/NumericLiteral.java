package org.eclipse.dltk.ast.expressions;

import org.eclipse.dltk.ast.DLTKToken;
import org.eclipse.dltk.utils.CorePrinter;

/**
 * 
 * Numeric literal. Used to hold ints, floats and complex numbers.
 * 
 */
public class NumericLiteral extends Literal {

	private long intValue;
	
	/**
	 * Construct from ANTLR token. With appropriate position information.
	 * 
	 * @param number
	 */
	public NumericLiteral(DLTKToken number) {
		super(number);
	}

	public NumericLiteral(int start, int end, long value) {
		super(start, end);
		this.intValue = value;
	}
	
	public long getIntValue () {
		return intValue;
	}

	
	public String getValue() {
		return String.valueOf(intValue);
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
