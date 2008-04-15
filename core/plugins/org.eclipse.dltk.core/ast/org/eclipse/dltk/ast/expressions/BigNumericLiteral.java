/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ast.expressions;

import java.math.BigInteger;

import org.eclipse.dltk.ast.DLTKToken;
import org.eclipse.dltk.utils.CorePrinter;

public class BigNumericLiteral extends Literal {

	private BigInteger bigValue;

	public BigNumericLiteral(int start, int end, BigInteger value) {
		super(start, end);
		this.bigValue = value;
	}

	public BigNumericLiteral(int start, int end, String value, int radix) {
		super(start, end);
		this.bigValue = new BigInteger(value, radix);
	}

	public BigNumericLiteral(DLTKToken token) {
		super(token);
		if (fLiteralValue.equalsIgnoreCase("0l")) 
			this.bigValue = BigInteger.ZERO;
		else if (fLiteralValue.startsWith("0x") || fLiteralValue.startsWith("0X"))
			this.bigValue = new BigInteger(fLiteralValue.substring(2), 16);
		else if(fLiteralValue.startsWith("0"))
			this.bigValue = new BigInteger(fLiteralValue.substring(1), 8);
		else
			this.bigValue = new BigInteger(fLiteralValue);
	}

	public String getValue() {
		return bigValue.toString();
	}

	/**
	 * Return kind.
	 */

	public int getKind() {
		return NUMBER_LITERAL;
	}

	/**
	 * Testing purposes only. Used to print number.
	 */

	public void printNode(CorePrinter output) {
		output.formatPrintLn(this.getValue());
	}

	public BigInteger getLongValue() {
		return bigValue;
	}

}
