/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.launching;

import org.eclipse.dltk.launching.ScriptLaunchConfigurationConstants;

public final class RubyLaunchConfigurationConstants extends
		ScriptLaunchConfigurationConstants {

	private RubyLaunchConfigurationConstants() {

	}

	public static final String ID_RUBY_SCRIPT = "org.eclipse.dltk.ruby.launching.RubyLaunchConfigurationType"; //$NON-NLS-1$

	public static final String ID_RUBY_PROCESS_TYPE = "rubyInterpreter"; //$NON-NLS-1$
}
