package org.eclipse.dltk.debug.ui.breakpoints;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.debug.core.model.IScriptWatchPoint;
import org.eclipse.swt.widgets.Composite;

public class ScriptWatchPointPropertyPage extends ScriptBreakpointPropertyPage {
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
}
