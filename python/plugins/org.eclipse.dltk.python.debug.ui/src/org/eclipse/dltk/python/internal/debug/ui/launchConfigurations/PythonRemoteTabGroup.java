package org.eclipse.dltk.python.internal.debug.ui.launchConfigurations;

import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.CommonTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;

public class PythonRemoteTabGroup extends AbstractLaunchConfigurationTabGroup
{
    //~ Methods

    /*
     * @see
     * org.eclipse.debug.ui.ILaunchConfigurationTabGroup#createTabs(org.eclipse.debug.ui.ILaunchConfigurationDialog,
     * java.lang.String)
     */
    public void createTabs(ILaunchConfigurationDialog dialog, String mode)
    {
        ILaunchConfigurationTab[] tabs =
            new ILaunchConfigurationTab[]
            {
                new PythonRemoteLaunchConfigurationTab(),
                new CommonTab()
            };

        setTabs(tabs);
    }

}
