package org.eclipse.dltk.dbgp.internal.breakpoints;

import org.eclipse.dltk.dbgp.breakpoints.IDbgpCallBreakpoint;

public class DbgpCallBreakpoint extends DbgpBreakpoint implements
		IDbgpCallBreakpoint {
	private String function;

	public DbgpCallBreakpoint(String id, boolean enabled, int hitValue,
			int hitCount, String hitCondition, String function) {
		super(id, enabled, hitValue, hitCount, hitCondition);
		this.function = function;
	}

	public String getFunction() {
		return function;
	}
}
