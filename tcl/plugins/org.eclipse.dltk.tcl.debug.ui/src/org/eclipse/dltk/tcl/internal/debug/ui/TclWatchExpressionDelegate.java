package org.eclipse.dltk.tcl.internal.debug.ui;

import org.eclipse.dltk.debug.internal.core.model.ScriptWatchExpressionDelegate;

public class TclWatchExpressionDelegate extends ScriptWatchExpressionDelegate {
	private static final String DUMMY_VAR = "dummy_tcl_var_"
			+ Long.toString(System.currentTimeMillis());

	// TODO: Correctly implement
	protected String prepareExpression(String expression) {
		return "set " + DUMMY_VAR + " " + "\"" + expression + "\"";
	}
}
