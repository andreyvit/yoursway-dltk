package org.eclipse.dltk.python.internal.debug.ui;

import org.eclipse.dltk.internal.debug.core.model.ScriptWatchExpressionDelegate;

public class PythonWatchExpressionDelegate extends ScriptWatchExpressionDelegate {
	private static final String DUMMY_VAR = "dummy_tcl_var_"
			+ Long.toString(System.currentTimeMillis());

	// TODO: Correctly implement
	protected String prepareExpression(String expression) {
		return "set " + DUMMY_VAR + " " + "\"" + expression + "\"";
	}
}
