package org.eclipse.dltk.ruby.internal.debug.ui.launcher;

import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.dltk.internal.debug.ui.launcher.ScriptLaunchShortcut;
import org.eclipse.dltk.ruby.core.RubyNature;
import org.eclipse.dltk.ruby.launching.IRubyLaunchConfigurationConstants;


public class RubyLaunchShortcut extends ScriptLaunchShortcut {

	/**
	 * Returns the type of configuration this shortcut is applicable to.
	 *
	 * @return the type of configuration this shortcut is applicable to
	 */
	protected ILaunchConfigurationType getConfigurationType() { //abstract in future
		return getLaunchManager().getLaunchConfigurationType(IRubyLaunchConfigurationConstants.ID_RUBY_SCRIPT);
	}

    /*
     * @see org.eclipse.dltk.internal.debug.ui.launcher.ScriptLaunchShortcut#getNature()
     */
    protected String getNature()
    {
        return RubyNature.NATURE_ID;
    }

}
