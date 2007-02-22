package org.eclipse.dltk.dbgp.internal.breakpoints;

import org.eclipse.dltk.dbgp.breakpoints.IDbgpConditionalBreakpoint;

public class DbgpConditionalBreakpoint extends DbgpBreakpoint implements
		IDbgpConditionalBreakpoint {
	private String expression;

	public DbgpConditionalBreakpoint(String id, boolean enabled, int hitValue,
			int hitCount, String hitCondition, String expression) {
		super(id, enabled, hitValue, hitCount, hitCondition);
		this.expression = expression;
	}

	public String getExpression() {
		return expression;
	}

}
