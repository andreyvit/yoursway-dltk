package org.eclipse.dltk.dbgp.internal.breakpoints;

import org.eclipse.dltk.dbgp.breakpoints.IDbgpExceptionBreakpoint;

public class DbgpExceptionBreakpoint extends DbgpBreakpoint implements
		IDbgpExceptionBreakpoint {

	private String exception;

	public DbgpExceptionBreakpoint(String id, boolean enabled, int hitValue,
			int hitCount, String hitCondition, String exception) {
		super(id, enabled, hitValue, hitCount, hitCondition);
		this.exception = exception;
	}

	public String getException() {
		return exception;
	}
}
