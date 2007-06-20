package org.eclipse.dltk.debug.ui.breakpoints;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.dltk.debug.core.model.IScriptBreakpoint;
import org.eclipse.dltk.debug.core.model.IScriptMethodEntryBreakpoint;
import org.eclipse.dltk.debug.core.model.IScriptWatchPoint;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class ScriptWatchPointPropertyPage extends ScriptBreakpointPropertyPage {
	// Other
	private Button breakOnEntryButton;
	private Button breakOnExitButton;
	
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
	
	private void createTypeSpecificExtensions(Composite mainComposite) {
		IScriptBreakpoint breakpoint = getBreakpoint();

		if (breakpoint instanceof IScriptMethodEntryBreakpoint) {
			IScriptMethodEntryBreakpoint ee = (IScriptMethodEntryBreakpoint) breakpoint;
			Composite createComposite = createComposite(mainComposite, 4);
			breakOnEntryButton = createCheckButton(createComposite,
					"Suspend on Method entry");
			breakOnExitButton = createCheckButton(createComposite,
					"Suspend on Method exit");
			breakOnEntryButton.setSelection(ee.breakOnEntry());
			breakOnExitButton.setSelection(ee.breakOnExit());
		}

		if (breakpoint instanceof IScriptWatchPoint) {
			IScriptWatchPoint ee = (IScriptWatchPoint) breakpoint;
			Composite createComposite = createComposite(mainComposite, 4);
			breakOnEntryButton = createCheckButton(createComposite,
					"Suspend on Access");
			breakOnExitButton = createCheckButton(createComposite,
					"Suspend on Modification");
			try {
				breakOnEntryButton.setSelection(ee.isAccess());
				breakOnExitButton.setSelection(ee.isModification());
			} catch (CoreException e) {
				DebugPlugin.log(e);
			}
		}
	}
}
