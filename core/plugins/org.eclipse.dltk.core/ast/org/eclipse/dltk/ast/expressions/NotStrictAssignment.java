/*
 * (c) 2002, 2005 xored software and others all rights reserved. http://www.xored.com
 */
package org.eclipse.dltk.ast.expressions;

import org.eclipse.dltk.utils.CorePrinter;

/**
 * NonStrict Assignment representation. Used to hold assignments such as +=, -=
 * etc.
 * 
 */
public class NotStrictAssignment extends Assignment {

	/**
	 * Construct from Expression and expression type.
	 * 
	 * @param left -
	 *            left expression.
	 * @param type -
	 *            expression type.
	 * @param right -
	 *            right expression.
	 */
	public NotStrictAssignment(Expression left, int type, Expression right) {
		super(left, type, right);
	}

	/**
	 * Testing purposes only. Used to print expression.
	 */

	public void printNode(CorePrinter output) {
		if (getLeft() != null) {
			getLeft().printNode(output);
		}
		output.formatPrintLn(this.getOperator());
		if (getRight() != null) {
			getRight().printNode(output);
		}
	}
}
