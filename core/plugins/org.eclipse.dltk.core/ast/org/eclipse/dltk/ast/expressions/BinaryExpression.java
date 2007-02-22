/*
 * (c) 2002, 2005 xored software and others all rights reserved. http://www.xored.com
 */
package org.eclipse.dltk.ast.expressions;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.utils.CorePrinter;

/**
 * Base binary class for binary Expressions.
 */
public class BinaryExpression extends Expression
{

	/**
	 * Left part of binary expression.
	 */

	protected Expression left;

	/**
	 * Right part of binary expression.
	 */
	protected Statement right;

	/**
	 * Expression kind.
	 * 
	 * @see Expressions for kind constants.
	 */
	protected int kind;

	/**
	 * Construct binary expression from left, right and type.
	 * 
	 * @param left -
	 *                left expression.
	 * @param type -
	 *                expression kind.
	 * @param right -
	 *                right expression.
	 */
	public BinaryExpression( Expression left, int type, Statement right ) {

		super( );
		if( left != null ) {
			this.setStart( left.sourceStart( ) );
		}
		if( right != null ) {
			this.setEnd( right.sourceEnd( ) );
		}
		this.kind = type;
		this.left = left;
		this.right = right;
	}

	/**
	 * Return Left Expression.
	 * 
	 * @return
	 */
	public Expression getLeft( ) {

		return left;
	}

	/**
	 * Return right expression.
	 * 
	 * @return
	 */
	public Statement getRight( ) {

		return right;
	}

	/**
	 * Traverse to childs.
	 */
	public void traverse( ASTVisitor pVisitor ) throws Exception {

		if( pVisitor.visit( this ) ) {
			if( left != null ) {
				left.traverse( pVisitor );
			}
			if( right != null ) {
				right.traverse( pVisitor );
			}
			pVisitor.endvisit( this );
		}
	}

	/**
	 * Convert this expression into string using Expression.getOperator to get operation presentation.
	 */
	public String toString( ) {

		String out = "";
		if( left != null ) {
			out += left.toString( );
		}
		out += this.getOperator( );
		if( right != null ) {
			out += this.right.toString( );
		}
		return out;
	}

	/**
	 * Return Binary Expression Kind.
	 */
	public int getKind( ) {

		return kind;
	}

	/**
	 * Testing purpose only.
	 */
	public void printNode( CorePrinter output ) {

		if( this.left != null ) {
			this.left.printNode( output );
		}
		output.formatPrintLn( this.getOperator( ) );

		if( this.right != null ) {
			this.right.printNode( output );
		}
	}
}
