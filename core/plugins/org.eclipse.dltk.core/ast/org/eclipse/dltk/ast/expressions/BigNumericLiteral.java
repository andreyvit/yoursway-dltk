package org.eclipse.dltk.ast.expressions;

import java.math.BigInteger;

import org.eclipse.dltk.utils.CorePrinter;


public class BigNumericLiteral extends Literal {

	private BigInteger bigValue;
	

	public BigNumericLiteral(int start, int end, BigInteger value) {
		super(start, end);
		this.bigValue = value;
	}
	
	public BigInteger getDoubleValue () {
		return bigValue;
	}

	
	public String getValue() {
		return bigValue.toString();
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
