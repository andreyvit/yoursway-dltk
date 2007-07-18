package org.eclipse.dltk.debug.ui.breakpoints;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.debug.core.model.IScriptWatchpoint;
import org.eclipse.dltk.ui.util.SWTFactory;
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
		SWTFactory.createLabel(parent, BreakpointMessages.WatchFieldLabel, 1);
		SWTFactory.createLabel(parent, watchPoint.getFieldName(), 1);
	}

	protected boolean hasExpressionEditor() {
		return false;
	}

	protected void createTypeSpecificButtons(Composite parent) {
		suspendOnAccessButton = SWTFactory.createCheckButton(parent,
				BreakpointMessages.SuspendOnAccessLabel, null, false, 1);
		suspendOnModificationButton = SWTFactory.createCheckButton(parent,
				BreakpointMessages.SuspendOnModificationLabel, null, false, 1);
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
