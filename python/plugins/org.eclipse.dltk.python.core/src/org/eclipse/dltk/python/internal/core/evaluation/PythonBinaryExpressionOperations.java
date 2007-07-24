/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.internal.core.evaluation;

import org.eclipse.dltk.ast.expressions.ExpressionConstants;
import org.eclipse.dltk.evaluation.types.ErrorDefinedType;
import org.eclipse.dltk.evaluation.types.SimpleType;
import org.eclipse.dltk.evaluation.types.UnknownType;
import org.eclipse.dltk.ti.types.IEvaluatedType;

/**
 * Used to handle binary expression type calculations.
 */
public class PythonBinaryExpressionOperations
{

	public static IEvaluatedType makeType( int left, int kind, int right ) {

//		 left are number.
		if( left == SimpleType.TYPE_NUMBER ) { // same types are
			switch( kind ) {
				case ExpressionConstants.E_PLUS:
				case ExpressionConstants.E_MINUS:
				case ExpressionConstants.E_MOD:
				case ExpressionConstants.E_MULT:
					if( right == SimpleType.TYPE_LIST ) { // FIXME: Only for
						// integers..
						return new SimpleType( SimpleType.TYPE_LIST );
					}
					if( right == SimpleType.TYPE_TUPLE ) { // FIXME: Only for
						// integers..
						return new SimpleType( SimpleType.TYPE_TUPLE );
					}
				case ExpressionConstants.E_DIV:
				case ExpressionConstants.E_DOUBLE_ARROW:
				case ExpressionConstants.E_POWER:
				case ExpressionConstants.E_RSHIFT:
				case ExpressionConstants.E_LSHIFT:
				case ExpressionConstants.E_XOR:
					if( right == SimpleType.TYPE_NUMBER ) {
						return new SimpleType( SimpleType.TYPE_NUMBER );
					}
					break;
				// to boolean expressions.
				case ExpressionConstants.E_GE:
				case ExpressionConstants.E_LE:
				case ExpressionConstants.E_GT:
				case ExpressionConstants.E_LT:
				case ExpressionConstants.E_EQUAL:
				case ExpressionConstants.E_NOT_EQUAL:
				case ExpressionConstants.E_NOT_EQUAL2:
					if( right == SimpleType.TYPE_NUMBER ) {
						return new SimpleType( SimpleType.TYPE_BOOLEAN );
					}
					break;
				// in expressions.
				case ExpressionConstants.E_IN:
				case ExpressionConstants.E_NOTIN:
					if( right == SimpleType.TYPE_DICT ) {
						return new SimpleType( SimpleType.TYPE_BOOLEAN );
					}
					if( right == SimpleType.TYPE_LIST ) {
						return new SimpleType( SimpleType.TYPE_BOOLEAN );
					}
					if( right == SimpleType.TYPE_TUPLE ) {
						return new SimpleType( SimpleType.TYPE_BOOLEAN );
					}
			}
			return ErrorDefinedType.INSTANCE; // Error other operations aren't
			// supported.
		}
		// left are string
		if( left == SimpleType.TYPE_STRING ) { // same types are
			if( kind == ExpressionConstants.E_PLUS ) {
				if( right == SimpleType.TYPE_STRING ) {
					return new SimpleType( SimpleType.TYPE_STRING );
				}
			}
			if( kind == ExpressionConstants.E_MOD ) { // string % op.
				if( right == SimpleType.TYPE_TUPLE ) {
					return new SimpleType( SimpleType.TYPE_STRING );
				}
				if( right == SimpleType.TYPE_DICT ) {
					return new SimpleType( SimpleType.TYPE_STRING );
				}
				// TODO: Add correct other types handling.
				// Because it may be one argument such as instance or type, or
				// also may be number or other string.
				// But only if one % are pressent in string. So may be check it
				// here? Or not here?
				return new SimpleType( SimpleType.TYPE_STRING );
			}
			if( kind == ExpressionConstants.E_MULT ) {
				if( right == SimpleType.TYPE_NUMBER ) {
					return new SimpleType( SimpleType.TYPE_STRING );
				}
			}
			return ErrorDefinedType.INSTANCE; // Other operations aren't
			// supported.
		}
		// left are Tuple
		if( left == SimpleType.TYPE_TUPLE ) { // same types are
			switch( kind ) {
				case ExpressionConstants.E_PLUS:
					if( right == SimpleType.TYPE_TUPLE ) {
						return new SimpleType( SimpleType.TYPE_TUPLE );
					}
					break;
				case ExpressionConstants.E_MULT:
					if( right == SimpleType.TYPE_NUMBER ) {
						return new SimpleType( SimpleType.TYPE_TUPLE );
					}
			}
			return ErrorDefinedType.INSTANCE; // Other operations aren't
			// supported.
		}
		// left are List
		if( left == SimpleType.TYPE_LIST ) { // same types are
			switch( kind ) {
				case ExpressionConstants.E_PLUS:
					if( right == SimpleType.TYPE_LIST ) {
						return new SimpleType( SimpleType.TYPE_LIST );
					}
					break;
				case ExpressionConstants.E_MULT:
					if( right == SimpleType.TYPE_NUMBER ) {
						return new SimpleType( SimpleType.TYPE_LIST );
					}
			}
			return ErrorDefinedType.INSTANCE; // Other operations aren't
			// supported.
		}
		// if left are Dict.
		if( left == SimpleType.TYPE_DICT ) {

		}

		return UnknownType.INSTANCE; // Not implemented or really unknown
	}

}
