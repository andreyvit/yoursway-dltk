package org.eclipse.dltk.tcl.internal.ui.text;

import org.eclipse.dltk.ui.text.DLTKColorConstants;

public final class TclColorConstants {
	private TclColorConstants() {
	}

	/**
	 * The color key for string and character literals in TCL code (value
	 * <code>"tcl_string"</code>).
	 */
	public static final String TCL_STRING = DLTKColorConstants.DLTK_STRING;

	/**
	 * The color key for TCL comments (value <code>"tcl_comment"</code>).
	 */
	public static final String TCL_SINGLE_LINE_COMMENT = DLTKColorConstants.DLTK_SINGLE_LINE_COMMENT;

	/**
	 * The color key for TCL variable (value <code>"tcl_variable"</code>).
	 */
	public static final String TCL_VARIABLE = "tcl_variable"; //$NON-NLS-1$

	/**
	 * The color key for TCL numbers (value <code>"tcl_number"</code>).
	 */
	public static final String TCL_NUMBER = DLTKColorConstants.DLTK_NUMBER;

	/**
	 * The color key for TCL keywords (value <code>"tcl_keyword"</code>).
	 */
	public static final String TCL_KEYWORD = DLTKColorConstants.DLTK_KEYWORD;

	/**
	 * The color key for TCL code (value <code>"tcl_default"</code>).
	 */
	public static final String TCL_DEFAULT = DLTKColorConstants.DLTK_DEFAULT;
}
