/*
 * (c) 2002, 2005 xored software and others all rights reserved. http://www.xored.com
 */
package org.eclipse.dltk.ast.expressions;

import org.eclipse.dltk.ast.DLTKToken;
import org.eclipse.dltk.utils.CorePrinter;

/**
 * Print expression.
 */
public class PrintExpression extends UnaryExpression {

	/**
	 * Construct from ANTLR token and right expression.
	 * 
	 * @param p
	 * @param right
	 */
	public PrintExpression(DLTKToken p, Expression right) {
		super(p, E_PRINT, right);

		if (right != null) {
			this.setEnd(right.sourceEnd());
		}
	}

	/**
	 * Testing purposes only. Print "print" expression.
	 */
	public void printNode(CorePrinter output) {
		output.formatPrintLn("print ");
		Expression expr = this.getExpression();
		if (expr != null) {
			expr.printNode(output);
		}
	}

}
