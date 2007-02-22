package org.eclipse.dltk.tcl.ast;

import org.eclipse.dltk.ast.expressions.ExpressionConstants;

public final class TclConstants {
	
	private TclConstants() {
	}

	public static final int TCL_STATEMENT = ExpressionConstants.USER_EXPRESSION_START + 1;

	public static final int TCL_STATEMENTS = ExpressionConstants.USER_EXPRESSION_START + 2;

	public static final int TCL_BLOCK_EXPRESSION = ExpressionConstants.USER_EXPRESSION_START + 3;

	public static final int TCL_EXECUTE_EXPRESSION = ExpressionConstants.USER_EXPRESSION_START + 4;

	public static final int TCL_IDENTIFIER_EXPRESSION = ExpressionConstants.USER_EXPRESSION_START + 5;
	
	public final static int TCL_FIELD_TYPE_UPVAR = ExpressionConstants.USER_EXPRESSION_START + 6;
	
	public final static int TCL_FIELD_TYPE_GLOBAL = ExpressionConstants.USER_EXPRESSION_START + 7;
		
	public final static int TCL_FIELD_TYPE_NAMESPACE = ExpressionConstants.USER_EXPRESSION_START + 8;

	public static final int TCL_FIELD_TYPE_INDEX = ExpressionConstants.USER_EXPRESSION_START + 9;
}
