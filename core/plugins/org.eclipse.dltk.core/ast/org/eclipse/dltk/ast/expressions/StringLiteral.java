/*
 * (c) 2002, 2005 xored software and others all rights reserved. http://www.xored.com
 */
package org.eclipse.dltk.ast.expressions;

import org.eclipse.dltk.ast.DLTKToken;
import org.eclipse.dltk.utils.CorePrinter;

/**
 * String representation.
 */
public class StringLiteral extends Literal {

	/**
	 * Construct from ATRL token.
	 * 
	 * @param t
	 */
	public StringLiteral(DLTKToken t) {
		super(t);
	}

	/**
	 * Construct from position information and value.
	 * 
	 * @param start -
	 *            start position.
	 * @param end -
	 *            end position.
	 * @param value -
	 *            value.
	 */
	public StringLiteral(int start, int end, String value) {
		super(start, end);
		this.fLiteralValue = value;
	}

	/**
	 * Return kind.
	 */
	public int getKind() {
		return STRING_LITERAL;
	}

	/**
	 * Testing purposes only. Used to print value.
	 */
	public void printNode(CorePrinter output) {
		output.formatPrintLn(this.getValue());
	}
}
