package org.eclipse.dltk.dbgp.internal.breakpoints;

import org.eclipse.dltk.dbgp.breakpoints.IDbgpLineBreakpoint;

public class DbgpLineBreakpoint extends DbgpBreakpoint implements
		IDbgpLineBreakpoint {
	private String fileName;

	private int lineNumber;

	public DbgpLineBreakpoint(String id, boolean enabled, int hitValue,
			int hitCount, String hitCondition, String fileName, int lineNumber) {
		super(id, enabled, hitValue, hitCount, hitCondition);

		this.fileName = fileName;
		this.lineNumber = lineNumber;
	}

	public String getFilename() {
		return fileName;
	}

	public int getLineNumber() {
		return lineNumber;
	}
}
