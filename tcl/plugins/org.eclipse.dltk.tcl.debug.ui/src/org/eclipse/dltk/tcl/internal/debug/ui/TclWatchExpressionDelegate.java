package org.eclipse.dltk.tcl.internal.debug.ui;

import org.eclipse.dltk.internal.debug.core.model.ScriptWatchExpressionDelegate;

public class TclWatchExpressionDelegate extends ScriptWatchExpressionDelegate {
	protected String prepareExpression(String expression) {
		if (expression.startsWith("$")) {
			return "expr { " + expression + " }";
		}
		return expression;
	}
}
