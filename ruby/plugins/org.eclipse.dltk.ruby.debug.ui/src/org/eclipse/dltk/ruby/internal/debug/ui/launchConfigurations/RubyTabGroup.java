package org.eclipse.dltk.ruby.internal.debug.ui.launchConfigurations;

import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.CommonTab;
import org.eclipse.debug.ui.EnvironmentTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.dltk.debug.ui.launchConfigurations.ScriptArgumentsTab;
import org.eclipse.dltk.ruby.internal.debug.ui.interpreters.RubyInterpreterTab;


public class RubyTabGroup extends AbstractLaunchConfigurationTabGroup {
	public void createTabs(ILaunchConfigurationDialog dialog, String mode) {
		ILaunchConfigurationTab[] tabs = new ILaunchConfigurationTab[] {
				new RubyMainLaunchConfigurationTab(),
				new ScriptArgumentsTab(),
				new RubyInterpreterTab(),
				new EnvironmentTab(),
				new CommonTab()
		};
		setTabs(tabs);
	}
}
