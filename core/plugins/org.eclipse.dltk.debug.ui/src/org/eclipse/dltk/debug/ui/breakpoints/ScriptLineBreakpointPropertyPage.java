package org.eclipse.dltk.debug.ui.breakpoints;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.debug.core.model.IScriptLineBreakpoint;
import org.eclipse.dltk.ui.util.SWTFactory;
import org.eclipse.swt.widgets.Composite;

public class ScriptLineBreakpointPropertyPage extends
		ScriptBreakpointPropertyPage {
	public ScriptLineBreakpointPropertyPage() {

	}

	protected void createTypeSpecificLabels(Composite parent)
			throws CoreException {
		setTitle(BreakpointMessages.LineBreakpointTitle);

		IScriptLineBreakpoint breakpoint = (IScriptLineBreakpoint) getBreakpoint();

		// Line number
		int lineNumber = breakpoint.getLineNumber();

		SWTFactory.createLabel(parent, BreakpointMessages.LineNumberLabel, 1);
		SWTFactory.createLabel(parent, Integer.toString(lineNumber), 1);
	}
}
