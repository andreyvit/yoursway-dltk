/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.internal.launching;

import org.eclipse.dltk.launching.AbstractInterpreterRunner;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.python.launching.PythonLaunchConfigurationConstants;
import org.eclipse.dltk.python.launching.PythonLaunchingPlugin;

public class PythonInterpreterRunner extends AbstractInterpreterRunner {
	public PythonInterpreterRunner(IInterpreterInstall install) {
		super(install);
	}

	protected String getPluginId() {
		return PythonLaunchingPlugin.getUniqueIdentifier();
	}

	protected String getProcessType() {
		return PythonLaunchConfigurationConstants.ID_PYTHON_PROCESS_TYPE;
	}
}
