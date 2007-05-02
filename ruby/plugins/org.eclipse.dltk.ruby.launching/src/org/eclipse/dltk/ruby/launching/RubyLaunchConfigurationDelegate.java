/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
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