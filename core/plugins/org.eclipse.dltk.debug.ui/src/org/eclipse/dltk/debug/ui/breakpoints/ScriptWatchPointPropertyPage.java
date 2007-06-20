package org.eclipse.dltk.debug.ui.breakpoints;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.debug.core.model.IScriptWatchPoint;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class ScriptWatchPointPropertyPage extends ScriptBreakpointPropertyPage {

	private Button suspendOnAccessButton;
	private Button suspendOnModificationButton;

	public ScriptWatchPointPropertyPage() {

	}

	protected void createTypeSpecificLabels(Composite parent)
			throws CoreException {
		setTitle("Script Watch Breakpoint");

		IScriptWatchPoint watchPoint = (IScriptWatchPoint) getBreakpoint();

		// Watch field
		createLabel(parent, "Watch field:");
		createLabel(parent, watchPoint.getFieldName());
	}

	protected void createTypeSpecificButtons(Composite parent) {
		suspendOnAccessButton = createCheckButton(parent, "Suspend on Access");
		suspendOnModificationButton = createCheckButton(parent,
				"Suspend on Modification");
	}

	protected void loadValues() throws CoreException {
		super.loadValues();

		IScriptWatchPoint watchpoint = (IScriptWatchPoint) getBreakpoint();
		suspendOnAccessButton.setSelection(watchpoint.isAccess());
		suspendOnModificationButton.setSelection(watchpoint.isModification());
	}

	protected void saveValues() throws CoreException {
		super.saveValues();
		
		IScriptWatchPoint watchpoint = (IScriptWatchPoint) getBreakpoint();
		watchpoint.setAccess(suspendOnAccessButton.getSelection());
		watchpoint.setModification(suspendOnModificationButton.getSelection());
	}
}
