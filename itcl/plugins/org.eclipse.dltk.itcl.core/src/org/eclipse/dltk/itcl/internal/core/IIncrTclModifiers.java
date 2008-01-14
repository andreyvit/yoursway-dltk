package org.eclipse.dltk.itcl.internal.core;

import org.eclipse.dltk.ast.Modifiers;

public interface IIncrTclModifiers extends Modifiers {
	public static final int AccIncrTcl = USER_MODIFIER * 8;
	public static final int AccIncrTclProc = AccIncrTcl * 2;
	public static final int AccConstructor = AccIncrTclProc * 2;
	public static final int AccDestructor = AccConstructor * 2;
}
