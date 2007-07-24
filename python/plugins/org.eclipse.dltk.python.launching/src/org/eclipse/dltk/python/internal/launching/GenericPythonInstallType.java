/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.internal.launching;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.ILog;
import org.eclipse.dltk.internal.launching.AbstractInterpreterInstallType;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.python.core.PythonNature;
import org.eclipse.dltk.python.launching.PythonLaunchingPlugin;
import org.osgi.framework.Bundle;

public class GenericPythonInstallType extends AbstractInterpreterInstallType {

	private static String[] interpreterNames = { "python" };

	public String getNatureId() {
		return PythonNature.NATURE_ID;
	}

	public String getName() {
		return "Generic Python install";
	}

	protected String getPluginId() {
		return PythonLaunchingPlugin.PLUGIN_ID;
	}

	protected String[] getPossibleInterpreterNames() {
		return interpreterNames;
	}

	protected IInterpreterInstall doCreateInterpreterInstall(String id) {
		return new GenericPythonInstall(this, id);
	}

	protected File createPathFile() throws IOException {
		Bundle bundle = PythonLaunchingPlugin.getDefault().getBundle();
		return storeToMetadata(bundle, "path.py", "scripts/path.py");
	}

	protected ILog getLog() {
		return PythonLaunchingPlugin.getDefault().getLog();
	}
}
