package org.eclipse.dltk.tcl.internal.debug.ui.launchConfigurations;

import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.EnvironmentTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.dltk.tcl.internal.debug.ui.interpreters.TclInterpreterTab;


public class TclTabGroup extends AbstractLaunchConfigurationTabGroup {
	public void createTabs(ILaunchConfigurationDialog dialog, String mode) {
		ILaunchConfigurationTab[] tabs = new ILaunchConfigurationTab[] {
				new TclMainLaunchConfigurationTab(),
				new TclScriptArgumentsTab(),
				new TclInterpreterTab(),
				new EnvironmentTab(),
				//new SourceContainerLookupTab(),
				//new CommonTab()
				new TclCommonTab()
		};
		setTabs(tabs);
	}
}
