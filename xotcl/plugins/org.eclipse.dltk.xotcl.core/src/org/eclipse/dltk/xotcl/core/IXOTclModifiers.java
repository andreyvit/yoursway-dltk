package org.eclipse.dltk.xotcl.core;

import org.eclipse.dltk.ast.Modifiers;

public interface IXOTclModifiers extends Modifiers {
	/**
	 * Used to split tcl and XOTcl methods, variables, etc.
	 */
	public static final int AccXOTcl = USER_MODIFIER * 2;
	public static final int AccXOTclObject = AccXOTcl * 2;
}
