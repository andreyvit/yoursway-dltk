/*
 * (c) 2002, 2005 xored software and others all rights reserved. http://www.xored.com
 */
package org.eclipse.dltk.ast.expressions;

/**
 * ( x ) expression.
 */
public class ParenExpression extends UnaryExpression {

	/**
	 * Construct from position bindings.
	 * 
	 * @param start -
	 *            start position in associated file.
	 * @param end -
	 *            end position in associated file.
	 * @param parenExpression
	 */
	public ParenExpression(int start, int end, Expression parenExpression) {
		super(start, end, E_PAREN, parenExpression);
	}
}
