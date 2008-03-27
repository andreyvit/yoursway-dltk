package org.eclipse.dltk.xotcl.core;

import org.eclipse.dltk.ast.Modifiers;

public interface IXOTclModifiers extends Modifiers {
	public static final int AccXOTcl = 2 << (USER_MODIFIER + 1);
	public static final int AccXOTclObject = 2 << (USER_MODIFIER + 2);
}
