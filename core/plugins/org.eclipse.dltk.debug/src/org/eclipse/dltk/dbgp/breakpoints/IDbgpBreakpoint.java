package org.eclipse.dltk.dbgp.breakpoints;

public interface IDbgpBreakpoint {
	final int HIT_CONDITION_GREATER = 0;

	final int HIT_CONDITION_EQUAL = 1;

	final int HIT_CONDITION_MULTIPLE = 2;

	String getId();

	boolean isEnabled();

	// -1 if not available
	int getHitCount();

	// -1 of not set
	int getHitValue();

	int getHitCondition();
}
