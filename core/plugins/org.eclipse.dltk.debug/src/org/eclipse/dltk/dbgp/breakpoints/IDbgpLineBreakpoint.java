package org.eclipse.dltk.dbgp.breakpoints;

public interface IDbgpLineBreakpoint extends IDbgpBreakpoint {
	String getFilename();

	int getLineNumber();
}
