/*
 * (c) 2002, 2005 xored software and others all rights reserved. http://www.xored.com
 */
package org.eclipse.dltk.ast.expressions;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.utils.CorePrinter;

/**
 * Assignment expression used to hold a = b expressions.
 */
public class Assignment extends BinaryExpression
{

	/**
	 * Construct from left, right and type expression. Used to construct NotStrictAssignment class.
	 * 
	 * @param left
	 * @param type
	 * @param right
	 */
	protected Assignment( Expression left, int type, Statement right ) {

		super( left, type, right );
	}

	/**
	 * Construct default strict assignment.
	 * 
	 * @param left
	 * @param right
	 */
	public Assignment( Expression left, Statement right ) {

		super( left, E_ASSIGN, right );
	}

	/**
	 * Traverse on childs. Order: left, right
	 */
	public void traverse( ASTVisitor visitor ) throws Exception {

		if( visitor.visit( this ) ) {
			if( left != null ) {
				left.traverse( visitor );
			}
			if( right != null ) {
				right.traverse( visitor );
			}
			visitor.endvisit( this );
		}
	}

	/**
	 * Convert to string in pettern: "left = right"
	 */
	public String toString( ) {

		return this.left.toString( ) + '=' + this.right.toString( );
	}

	/**
	 * Testing purposes only. Used to print expression.
	 */
	public void printNode( CorePrinter output ) {

		if( this.left != null ) {
			this.left.printNode( output );
		}
		output.formatPrintLn( " = " );
		if( this.right != null ) {
			right.printNode( output );
		}
	}
}
