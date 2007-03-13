package org.eclipse.dltk.ast.expressions;

import org.eclipse.dltk.utils.CorePrinter;


public class FloatNumericLiteral extends Literal {

	private double doubleValue;
	

	public FloatNumericLiteral(int start, int end, double value) {
		super(start, end);
		this.doubleValue = value;
	}
	
	public double getDoubleValue () {
		return doubleValue;
	}

	
	public String getValue() {
		return String.valueOf(doubleValue);
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
