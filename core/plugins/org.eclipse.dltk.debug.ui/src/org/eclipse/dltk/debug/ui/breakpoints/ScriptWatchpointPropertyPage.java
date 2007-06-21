package org.eclipse.dltk.debug.ui.breakpoints;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.debug.core.model.IScriptWatchpoint;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class ScriptWatchpointPropertyPage extends ScriptBreakpointPropertyPage {

	private Button suspendOnAccessButton;
	private Button suspendOnModificationButton;

	public ScriptWatchpointPropertyPage() {

	}

	protected void createTypeSpecificLabels(Composite parent)
			throws CoreException {
		setTitle(BreakpointMessages.WatchpointTitle);

		IScriptWatchpoint watchPoint = (IScriptWatchpoint) getBreakpoint();

		// Watch field
		createLabel(parent, BreakpointMessages.WatchFieldLabel);
		createLabel(parent, watchPoint.getFieldName());
	}

	protected boolean hasExpressionEditor() {
		return false;
	}

	protected void createTypeSpecificButtons(Composite parent) {
		suspendOnAccessButton = createCheckButton(parent,
				BreakpointMessages.SuspendOnAccessLabel);
		suspendOnModificationButton = createCheckButton(parent,
				BreakpointMessages.SuspendOnModificationLabel);
	}

	protected void loadValues() throws CoreException {
		super.loadValues();

		IScriptWatchpoint watchpoint = (IScriptWatchpoint) getBreakpoint();
		suspendOnAccessButton.setSelection(watchpoint.isAccess());
		suspendOnModificationButton.setSelection(watchpoint.isModification());
	}

	protected void saveValues() throws CoreException {
		super.saveValues();

		IScriptWatchpoint watchpoint = (IScriptWatchpoint) getBreakpoint();
		watchpoint.setAccess(suspendOnAccessButton.getSelection());
		watchpoint.setModification(suspendOnModificationButton.getSelection());
	}
}
