package org.eclipse.dltk.itcl.internal.core;

import org.eclipse.dltk.ast.Modifiers;

public interface IIncrTclModifiers extends Modifiers {
	public static final int AccIncrTcl = 2 << ( USER_MODIFIER + 3 );
	public static final int AccIncrTclProc = 2 << ( USER_MODIFIER + 4 );
	public static final int AccConstructor = 2 << ( USER_MODIFIER + 5 );
	public static final int AccDestructor = 2 << ( USER_MODIFIER + 6 );
}
