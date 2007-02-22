/*
 * (c) 2002, 2005 xored software and others all rights reserved. http://www.xored.com
 */
package org.eclipse.dltk.ast.expressions;

/**
 * Instanceof expression.
 */
public class InstanceOfExpression extends BinaryExpression {

	/**
	 * Constructs from expression of variable and expression of type.
	 * 
	 * @param variable
	 * @param type
	 */
	public InstanceOfExpression(Expression variable, Expression type) {
		super(variable, E_INSTANSEOF, type);
	}
}
