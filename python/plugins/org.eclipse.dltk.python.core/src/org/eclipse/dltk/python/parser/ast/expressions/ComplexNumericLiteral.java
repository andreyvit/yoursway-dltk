/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.parser.ast.expressions;

import java.math.BigDecimal;

import org.eclipse.dltk.ast.DLTKToken;
import org.eclipse.dltk.ast.expressions.Literal;
import org.eclipse.dltk.utils.CorePrinter;


public class ComplexNumericLiteral extends Literal
{

	private double doubleValue;

	public ComplexNumericLiteral( DLTKToken number ) {
		super( number );
		String value = number.getText();
		try{
			this.doubleValue = Double.parseDouble(value);
		}catch(NumberFormatException e){
			BigDecimal decimal = new BigDecimal(value);
			this.doubleValue = decimal.doubleValue();
		}
	}
	
	public void printNode( CorePrinter output ) {
		output.formatPrintLn( this.getValue() );		
	}


	public int getKind() {
		return NUMBER_LITERAL;
	}
	
	public double getDoubleValue() {
		return doubleValue;
	}
}
