package org.eclipse.dltk.debug.ui.breakpoints;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.debug.core.model.IScriptMethodEntryBreakpoint;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class ScriptMethodBreakpointPropertyPage extends
		ScriptBreakpointPropertyPage {

	private Button breakOnEntryButton;
	private Button breakOnExitButton;

	public ScriptMethodBreakpointPropertyPage() {

	}

	protected void createTypeSpecificButtons(Composite parent) {
		setTitle(BreakpointMessages.MethodBreakpointTitle);

		breakOnEntryButton = createCheckButton(parent,
				BreakpointMessages.SuspendOnMethodEntryLabel);
		breakOnExitButton = createCheckButton(parent,
				BreakpointMessages.SuspendOnMethodExitLabel);
	}

	protected void loadValues() throws CoreException {
		super.loadValues();

		IScriptMethodEntryBreakpoint breakpoint = (IScriptMethodEntryBreakpoint) getBreakpoint();

		breakOnEntryButton.setSelection(breakpoint.breakOnEntry());
		breakOnExitButton.setSelection(breakpoint.breakOnExit());
	}

	protected void saveValues() throws CoreException {
		super.saveValues();

		IScriptMethodEntryBreakpoint breakpoint = (IScriptMethodEntryBreakpoint) getBreakpoint();

		breakpoint.setBreakOnEntry(breakOnEntryButton.getSelection());
		breakpoint.setBreakOnExit(breakOnExitButton.getSelection());
	}
}
