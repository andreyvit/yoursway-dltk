/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.launching;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.eclipse.core.runtime.ILog;
import org.eclipse.dltk.internal.launching.AbstractInterpreterInstallType;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.tcl.core.TclNature;
import org.eclipse.dltk.tcl.launching.TclLaunchingPlugin;
import org.eclipse.dltk.utils.DeployHelper;
import org.osgi.framework.Bundle;

public class GenericTclInstallType extends AbstractInterpreterInstallType {

	public String getNatureId() {
		return TclNature.NATURE_ID;
	}

	public String getName() {
		return "Generic Tcl install";
	}

	private static String[] possibleExes = { "tclsh", "wish", "vtk", "expect" };

	protected String getPluginId() {
		return TclLaunchingPlugin.PLUGIN_ID;
	}

	protected String[] getPossibleInterpreterNames() {
		return possibleExes;
	}

	protected IInterpreterInstall doCreateInterpreterInstall(String id) {
		return new GenericTclInstall(this, id);
	}

	protected void filterEnvironment(Map environment) {
		// make sure that $auto_path is clean
		environment.remove("TCLLIBPATH");
		// block wish from showing window under linux
		environment.remove("DISPLAY");
	}

	protected File createPathFile() throws IOException {
		DeployHelper.deploy(TclLaunchingPlugin.getDefault(), "scripts").append("");
		
		Bundle bundle = TclLaunchingPlugin.getDefault().getBundle();
		return storeToMetadata(bundle, "path.tcl", "scripts/path.tcl");
	}

	protected String[] parsePaths(String result) {
		ArrayList paths = new ArrayList();
		String subs = null;
		int index = 0;
		while (index < result.length()) {
			// skip whitespaces
			while (index < result.length()
					&& Character.isWhitespace(result.charAt(index)))
				index++;
			if (index == result.length())
				break;

			if (result.charAt(index) == '{') {
				int start = index;
				while (index < result.length() && result.charAt(index) != '}')
					index++;
				if (index == result.length())
					break;
				subs = result.substring(start + 1, index);
			} else {
				int start = index;
				while (index < result.length() && result.charAt(index) != ' ')
					index++;
				subs = result.substring(start, index);
			}

			paths.add(subs);
			index++;
		}

		return (String[]) paths.toArray(new String[paths.size()]);
	}

	protected ILog getLog() {
		return TclLaunchingPlugin.getDefault().getLog();
	}
}
