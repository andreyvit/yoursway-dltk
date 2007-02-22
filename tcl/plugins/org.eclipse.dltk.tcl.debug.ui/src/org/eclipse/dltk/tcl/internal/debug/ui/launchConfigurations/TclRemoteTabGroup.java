package org.eclipse.dltk.tcl.internal.debug.ui.launchConfigurations;

import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;

public class TclRemoteTabGroup extends AbstractLaunchConfigurationTabGroup {

	public TclRemoteTabGroup() {
	}

	public void createTabs(ILaunchConfigurationDialog dialog, String mode) {
		ILaunchConfigurationTab[] tabs = new ILaunchConfigurationTab[] { new TclRemoteTab() };
		setTabs(tabs);
	}

}
