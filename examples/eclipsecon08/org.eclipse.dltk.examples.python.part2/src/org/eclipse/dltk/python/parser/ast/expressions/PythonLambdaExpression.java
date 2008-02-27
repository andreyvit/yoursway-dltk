/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.parser.ast.expressions;

import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.DLTKToken;
import org.eclipse.dltk.ast.declarations.Argument;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.python.parser.ast.PythonConstants;
import org.eclipse.dltk.utils.CorePrinter;


public class PythonLambdaExpression extends Expression
{

	private List/*< Argument >*/ fArguments;

	private Expression fBodyExpressions;

	public PythonLambdaExpression( DLTKToken t, List/*< Argument >*/ arguments, Expression body ) {

		super( t );
		if( body != null ) {
			this.setEnd( body.sourceEnd( ) );
		}
		this.fArguments = arguments;
		this.fBodyExpressions = body;
	}

	public List/*< Argument >*/ getArguments( ) {

		return this.fArguments;
	}

	public Expression getBodyExpression( ) {

		return this.fBodyExpressions;
	}

	
	public int getKind( ) {

		return PythonConstants.E_LAMBDA;
	}

	
	public void traverse( ASTVisitor pVisitor ) throws Exception {

		if( pVisitor.visit( this ) ) {
			if( this.fArguments != null ) {
				Iterator i = this.fArguments.iterator();
				while( i.hasNext()) {
					Argument arg = (Argument)i.next();
					arg.traverse( pVisitor );
				}
			}
			if( this.fBodyExpressions != null ) {
				this.fBodyExpressions.traverse( pVisitor );
			}
			pVisitor.endvisit( this );
		}

	}

	public void printNode( CorePrinter output ) {

		output.formatPrintLn( "lambda " );
		if( this.fArguments != null ) {
			boolean first = true;
			Iterator i = this.fArguments.iterator();
			while( i.hasNext()) {
				Argument arg = (Argument)i.next();
				if( first ) {
					first = false;
				}
				else {
					output.formatPrintLn( ", " );
				}
				arg.printNode( output );
			}
		}
		output.formatPrintLn( ":" );
		if( this.fBodyExpressions != null ) {
			this.fBodyExpressions.printNode( output );
		}
	}
}
