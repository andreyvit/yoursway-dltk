package org.eclipse.dltk.javascript.internal.debug.ui.launchConfigurations;

import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;

public class JavaScriptRemoteTabGroup extends AbstractLaunchConfigurationTabGroup {

	public JavaScriptRemoteTabGroup() {
	}

	public void createTabs(ILaunchConfigurationDialog dialog, String mode) {
		ILaunchConfigurationTab[] tabs = new ILaunchConfigurationTab[] { new JavaScriptRemoteTab() };
		setTabs(tabs);
	}

}
