/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.parser.ast.expressions;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.DLTKToken;
import org.eclipse.dltk.ast.declarations.Decorator;
import org.eclipse.dltk.ast.expressions.ExpressionList;
import org.eclipse.dltk.utils.CorePrinter;


public class PythonFunctionDecorator extends Decorator
{
	private ExpressionList fArguments;

	public PythonFunctionDecorator( DLTKToken nameDLTKToken, int startPos, int endPos ) {

		super( nameDLTKToken, startPos, endPos );
	}

	public PythonFunctionDecorator( DLTKToken nameDLTKToken, int startPos, int endPos, ExpressionList arguments ) {

		super( nameDLTKToken, startPos, endPos );
		this.fArguments = arguments;
	}

	public PythonFunctionDecorator(DLTKToken dltk, DLTKToken dltk2,
			DLTKToken dltk3, ExpressionList arguments) {
		super(dltk, dltk2.getColumn(), dltk3.getColumn());
	}
	public PythonFunctionDecorator(DLTKToken dltk, DLTKToken dltk2) {
		super(dltk, dltk2.getColumn(), dltk2.getColumn() + dltk2.getText().length());
	}

	public ExpressionList getArguments( ) {

		return this.fArguments;
	}

	
	public void traverse( ASTVisitor pVisitor ) throws Exception {

		if( pVisitor.visit( this ) ) {
			if( this.fArguments != null ) {
				this.fArguments.traverse( pVisitor );
			}
			pVisitor.endvisit( this );
		}
	}

	
	public void printNode( CorePrinter output ) {

		String name = this.getName( );
		if( name != null ) {
			output.formatPrintLn( "@" + this.getName( ) );
		}
		if( this.fArguments != null ) {
			output.formatPrintLn( "( " );
			this.fArguments.printNode( output );
			output.formatPrintLn( " )" );
		}
		output.formatPrint( "" );
	}

}
