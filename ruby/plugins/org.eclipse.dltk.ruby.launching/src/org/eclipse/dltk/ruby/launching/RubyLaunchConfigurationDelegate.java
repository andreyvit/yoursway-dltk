package org.eclipse.dltk.ruby.launching;

import org.eclipse.dltk.launching.AbstractScriptLaunchConfigurationDelegate;
import org.eclipse.dltk.ruby.core.RubyNature;

public class RubyLaunchConfigurationDelegate  extends AbstractScriptLaunchConfigurationDelegate {

    /*
     * @see org.eclipse.dltk.launching.AbstractScriptLaunchConfigurationDelegate#getLanguageId()
     */
	public String getLanguageId() {
		return RubyNature.NATURE_ID;
	}

    /*
     * @see org.eclipse.dltk.launching.AbstractScriptLaunchConfigurationDelegate#getEnvironmentLibName()
     */
    protected String getEnvironmentLibName()
    {
        return "RUBYLIB";
    }

}