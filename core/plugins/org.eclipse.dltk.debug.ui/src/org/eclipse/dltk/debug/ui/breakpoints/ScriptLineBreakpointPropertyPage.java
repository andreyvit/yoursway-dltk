package org.eclipse.dltk.debug.ui.breakpoints;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.debug.core.model.IScriptLineBreakpoint;
import org.eclipse.swt.widgets.Composite;

public class ScriptLineBreakpointPropertyPage extends
		ScriptBreakpointPropertyPage {
	public ScriptLineBreakpointPropertyPage() {

	}

	protected void createTypeSpecificLabels(Composite parent)
			throws CoreException {
		setTitle("Script Line Breakpoint");
		
		IScriptLineBreakpoint breakpoint = (IScriptLineBreakpoint) getBreakpoint();

		// Line number
		int lineNumber = breakpoint.getLineNumber();
		createLabel(parent, "Line Number:");
		createLabel(parent, Integer.toString(lineNumber));
	}
}
