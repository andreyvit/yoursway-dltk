/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.launching;

import org.eclipse.dltk.launching.ScriptLaunchConfigurationConstants;

public class PythonLaunchConfigurationConstants extends
		ScriptLaunchConfigurationConstants {
	protected PythonLaunchConfigurationConstants() {
		
	}
	
	public static final String ID_PYTHON_SCRIPT = "org.eclipse.dltk.python.launching.PythonLaunchConfigurationType"; //$NON-NLS-1$

	public static final String ID_PYTHON_PROCESS_TYPE = "pythonInterpreter"; //$NON-NLS-1$
}
