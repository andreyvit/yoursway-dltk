/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.launching;

import org.eclipse.dltk.launching.ScriptLaunchConfigurationConstants;

public class TclLaunchConfigurationConstants extends
		ScriptLaunchConfigurationConstants {
	
	protected TclLaunchConfigurationConstants() {
		
	}
	
	public static final String ID_TCL_SCRIPT = "org.eclipse.dltk.tcl.launching.TCLLaunchConfigurationType"; //$NON-NLS-1$

	public static final String ID_TCL_PROCESS_TYPE = "tclInterpreter"; //$NON-NLS-1$
}
