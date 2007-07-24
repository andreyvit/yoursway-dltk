package org.eclipse.dltk.debug.ui.breakpoints;

public interface IScriptBreakpointLineValidator {
	boolean isValid(String line, int number);
}
