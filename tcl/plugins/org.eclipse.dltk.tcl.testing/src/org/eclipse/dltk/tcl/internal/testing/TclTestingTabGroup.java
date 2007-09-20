package org.eclipse.dltk.tcl.internal.testing;

import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.EnvironmentTab;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.dltk.debug.ui.launchConfigurations.ScriptArgumentsTab;
import org.eclipse.dltk.debug.ui.launchConfigurations.ScriptCommonTab;
import org.eclipse.dltk.tcl.internal.debug.ui.interpreters.TclInterpreterTab;

public class TclTestingTabGroup extends AbstractLaunchConfigurationTabGroup {

	public void createTabs(ILaunchConfigurationDialog dialog, String mode) {
		ILaunchConfigurationTab[] tabs = new ILaunchConfigurationTab[] {
				new TclTestingMainLaunchConfigurationTab(),
				new ScriptArgumentsTab(), new TclInterpreterTab(),
				new EnvironmentTab(), new ScriptCommonTab() {

					public void performApply(
							ILaunchConfigurationWorkingCopy configuration) {
						super.performApply(configuration);
						configuration.setAttribute(
								IDebugUIConstants.ATTR_CAPTURE_IN_CONSOLE,
								(String) null);
					}
				} };
		setTabs(tabs);
	}

}
