/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.launching;

import org.eclipse.dltk.launching.IDLTKLaunchConfigurationConstants;

public interface IRubyLaunchConfigurationConstants extends
		IDLTKLaunchConfigurationConstants {
	// id of configuration type
	public static final String ID_RUBY_SCRIPT = "org.eclipse.dltk.ruby.launching.RubyLaunchConfigurationType";

	public static final String ID_RUBY_PROCESS_TYPE = "rubyInterpreter";
}
