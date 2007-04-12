package org.eclipse.dltk.javascript.internal.launching;

import org.eclipse.debug.ui.ILaunchConfigurationDialog;

public class JavaLocalApplicationTabGroup extends org.eclipse.jdt.internal.debug.ui.launcher.LocalJavaApplicationTabGroup {

	public void createTabs(ILaunchConfigurationDialog dialog, String mode) {
		super.createTabs(dialog, mode);
//		ILaunchConfigurationTab[] tabs = getTabs();
//		List tabsArray = new ArrayList(tabs.length + 1);
//		tabsArray.add(new ProfilerSettingsTab());
//		for (int i = 0; i < tabs.length; i++) {
//			tabsArray.add(tabs[i]);
//		}
//		setTabs((ILaunchConfigurationTab[]) tabsArray.toArray(new ILaunchConfigurationTab[tabsArray.size()]));
	}

}
