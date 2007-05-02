package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.utils.CorePrinter;


/**
 * Assignment expression used to hold a = b expressions.
 */
public class RubyAssignment extends RubyBinaryExpression
{
	
	public static final RubyAssignment[] EMPTY_ARRAY = new RubyAssignment[0];

	/**
	 * Construct from left, right and type expression. Used to construct NotStrictAssignment class.
	 * 
	 * @param left
	 * @param type
	 * @param right
	 */
	protected RubyAssignment( ASTNode left, int type, ASTNode right ) {

		super( left, type, right );
	}

	/**
	 * Construct default strict assignment.
	 * 
	 * @param left
	 * @param right
	 */
	public RubyAssignment( ASTNode left, ASTNode right ) {

		super( left, E_ASSIGN, right );
	}

	/**
	 * Convert to string in pettern: "left = right"
	 */
	public String toString( ) {
		return getLeft().toString( ) + '=' + getRight().toString( );
	}

	/**
	 * Testing purposes only. Used to print expression.
	 */
	public void printNode( CorePrinter output ) {

		if( getLeft() != null ) {
			getLeft().printNode( output );
		}
		output.formatPrintLn( " = " );
		if( getRight() != null ) {
			getRight().printNode( output );
		}
	}
}
