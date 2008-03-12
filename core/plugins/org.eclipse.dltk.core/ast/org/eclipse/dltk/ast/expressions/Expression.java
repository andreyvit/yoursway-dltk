/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
/*
 * (c) 2002, 2005 xored software and others all rights reserved. http://www.xored.com
 */
package org.eclipse.dltk.ast.expressions;

import org.eclipse.dltk.ast.DLTKToken;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.utils.CorePrinter;

/**
 * Base class for all expressions.
 * 
 */
public abstract class Expression extends Statement implements
		ExpressionConstants {

	protected Expression(int start, int end) {
		super(start, end);
	}

	protected Expression() {
	}

	public Expression(DLTKToken token) {
		super(token);
	}

	/**
	 * Return string representation of this expression operator. If this is
	 * aruthmetic, binary of assigment expression return expressin symbol. For
	 * some custom expressions return *.
	 * 
	 * @return string expression operator representation
	 */
	public String getOperator() {
		switch (this.getKind()) {
		case E_BAND:
			return " and "; //$NON-NLS-1$
		case E_BOR:
			return " or "; //$NON-NLS-1$
		case E_BXOR:
			return " xor "; //$NON-NLS-1$
		case E_DIV:
			return "/"; //$NON-NLS-1$
		case E_EQUAL:
			return "=="; //$NON-NLS-1$
		case E_LT:
			return "<"; //$NON-NLS-1$
		case E_LE:
			return "<="; //$NON-NLS-1$
		case E_GT:
			return ">"; //$NON-NLS-1$
		case E_GE:
			return ">="; //$NON-NLS-1$
		case E_MOD:
			return "%"; //$NON-NLS-1$
		case E_CONCAT:
			return "."; //$NON-NLS-1$
		case E_XOR:
			return " xor "; //$NON-NLS-1$
		case E_LAND:
			return "&&"; //$NON-NLS-1$
		case E_LOR:
			return "||"; //$NON-NLS-1$
		case E_LSHIFT:
			return "<<"; //$NON-NLS-1$
		case E_MINUS:
			return "-"; //$NON-NLS-1$
		case E_PLUS:
			return "+"; //$NON-NLS-1$
		case E_MULT:
			return "*"; //$NON-NLS-1$
		case E_RSHIFT:
			return ">>"; //$NON-NLS-1$
		case E_DOT_ASSIGN:
			return ".="; //$NON-NLS-1$
		case E_IDENTICAL:
			return "==="; //$NON-NLS-1$
		case E_NOTIDENTICAL:
			return "!==="; //$NON-NLS-1$
		case E_LNOT:
			return "!"; //$NON-NLS-1$
		case E_BNOT:
			return "~"; //$NON-NLS-1$
		case E_BNOT_ASSIGN:
			return "~="; //$NON-NLS-1$
		case E_NOT_EQUAL:
			return "!="; //$NON-NLS-1$
		case E_NOT_EQUAL2:
			return "<>"; //$NON-NLS-1$
		case E_DIV_ASSIGN:
			return "/="; //$NON-NLS-1$
		case E_PLUS_ASSIGN:
			return "+="; //$NON-NLS-1$
		case E_INC:
			return "++"; //$NON-NLS-1$
		case E_MINUS_ASSIGN:
			return "-="; //$NON-NLS-1$
		case E_DEC:
			return "--"; //$NON-NLS-1$
		case E_MOD_ASSIGN:
			return "%="; //$NON-NLS-1$
		case E_MULT_ASSIGN:
			return "*="; //$NON-NLS-1$
		case E_SR_ASSIGN:
			return ">>="; //$NON-NLS-1$
		case E_SL_ASSIGN:
			return "<<="; //$NON-NLS-1$
		case E_BXOR_ASSIGN:
			return "^="; //$NON-NLS-1$
		case E_BAND_ASSIGN:
			return "&="; //$NON-NLS-1$
		case E_DOUBLE_ARROW:
			return "=>"; //$NON-NLS-1$
		case E_SINGLE_ARROW:
			return "->"; //$NON-NLS-1$
		case E_AT:
			return "@"; //$NON-NLS-1$
		case E_DOLLAR:
			return "$"; //$NON-NLS-1$
		case E_COMMA:
			return ","; //$NON-NLS-1$
		case E_BOR_ASSIGN:
			return "|="; //$NON-NLS-1$
		case E_CONCAT_ASSIGN:
			return ".="; //$NON-NLS-1$
		case E_TILDE:
			return "~"; //$NON-NLS-1$
		case E_DOUBLESTAR_ASSIGN:
			return "**="; //$NON-NLS-1$
		case E_DOUBLEDIV_ASSIGN:
			return "//="; //$NON-NLS-1$
		case E_POWER:
			return "**"; //$NON-NLS-1$
		}

		return "*"; //$NON-NLS-1$
	}

	/**
	 * Testing purposes only. Print expression.
	 */
	public void printNode(CorePrinter output) {
		output.formatPrintLn("Expression" + getSourceRange() + ":" + getKind()); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public boolean equals(Object obj) {
		if (obj instanceof Statement) {
			Statement s = (Statement) obj;
			return sourceStart() == s.sourceStart()
					&& sourceEnd() == s.sourceEnd() && getKind() == s.getKind();
		}

		return false;
	}
}
