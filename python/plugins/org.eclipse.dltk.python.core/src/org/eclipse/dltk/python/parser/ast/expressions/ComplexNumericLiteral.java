/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.parser.ast.expressions;

import org.eclipse.dltk.ast.DLTKToken;
import org.eclipse.dltk.ast.expressions.NumericLiteral;
import org.eclipse.dltk.utils.CorePrinter;


public class ComplexNumericLiteral extends NumericLiteral
{

	public ComplexNumericLiteral( DLTKToken number ) {

		super( number );
		char c = this.fLiteralValue.charAt( this.fLiteralValue.length() - 1 );
		if(  c == 'j' || c == 'J' ) {					
			String value = number.getText();
			this.fLiteralValue = value.substring(0, value.length() - 1 );
			this.setEnd( this.sourceStart() + this.fLiteralValue.length() );
		}
	}
	
	public void printNode( CorePrinter output ) {
		output.formatPrintLn( this.getValue() + "j" );		
	}
}
