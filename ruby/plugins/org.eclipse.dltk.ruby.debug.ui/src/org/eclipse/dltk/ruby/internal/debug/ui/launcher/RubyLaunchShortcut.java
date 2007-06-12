/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.debug.ui.launcher;

import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.dltk.internal.debug.ui.launcher.ScriptLaunchShortcut;
import org.eclipse.dltk.ruby.core.RubyNature;
import org.eclipse.dltk.ruby.launching.RubyLaunchConfigurationConstants;


public class RubyLaunchShortcut extends ScriptLaunchShortcut {

	/**
	 * Returns the type of configuration this shortcut is applicable to.
	 *
	 * @return the type of configuration this shortcut is applicable to
	 */
	protected ILaunchConfigurationType getConfigurationType() { //abstract in future
		return getLaunchManager().getLaunchConfigurationType(RubyLaunchConfigurationConstants.ID_RUBY_SCRIPT);
	}

    /*
     * @see org.eclipse.dltk.internal.debug.ui.launcher.ScriptLaunchShortcut#getNature()
     */
    protected String getNature()
    {
        return RubyNature.NATURE_ID;
    }

}
