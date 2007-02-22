package org.eclipse.dltk.dbgp.internal.breakpoints;

import org.eclipse.dltk.dbgp.breakpoints.IDbgpWatchBreakpoint;

public class DbgpWatchBreakpoint extends DbgpBreakpoint implements
		IDbgpWatchBreakpoint {

	private String expression;

	public DbgpWatchBreakpoint(String id, boolean enabled, int hitValue,
			int hitCount, String hitCondition, String expression) {
		super(id, enabled, hitValue, hitCount, hitCondition);
		this.expression = expression;
	}

	public String getExpression() {
		return expression;
	}
}
