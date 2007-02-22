package org.eclipse.dltk.dbgp.internal.breakpoints;

public class DbgpReturnBreakpoint extends DbgpBreakpoint {

	private String function;

	public DbgpReturnBreakpoint(String id, boolean enabled, int hitValue,
			int hitCount, String hitCondition, String function) {
		super(id, enabled, hitValue, hitCount, hitCondition);
		this.function = function;
	}

	public String getFunction() {
		return function;
	}
}
