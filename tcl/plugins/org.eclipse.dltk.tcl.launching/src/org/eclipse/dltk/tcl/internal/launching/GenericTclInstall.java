/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.launching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.launching.AbstractInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterInstallType;
import org.eclipse.dltk.launching.IInterpreterRunner;
import org.eclipse.dltk.launching.InterpreterConfig;
import org.eclipse.dltk.launching.ScriptLaunchUtil;
import org.eclipse.dltk.tcl.core.TclNature;
import org.eclipse.dltk.tcl.launching.TclLaunchingPlugin;
import org.eclipse.dltk.utils.DeployHelper;

public class GenericTclInstall extends AbstractInterpreterInstall {
	public class BuiltinsHelper {
		StringBuffer source = new StringBuffer();

		void load() throws IOException, CoreException {
			final IPath builder = DeployHelper.deploy(TclLaunchingPlugin
					.getDefault(), "scripts/builtins.tcl"); //$NON-NLS-1$

			InterpreterConfig config = ScriptLaunchUtil
					.createInterpreterConfig(builder.toFile(), builder
							.removeLastSegments(1).toFile(), null);
			// config.addInterpreterArg("-KU"); //$NON-NLS-1$
			Process process = ScriptLaunchUtil.runScriptWithInterpreter(
					GenericTclInstall.this.getInstallLocation()
							.getAbsolutePath(), config);

			BufferedReader input = null;
			try {
				input = new BufferedReader(new InputStreamReader(process
						.getInputStream()));

				String line = null;
				while ((line = input.readLine()) != null) {
					source.append(line);
					source.append("\n");
				}
			} finally {
				if (input != null) {
					input.close();
				}
			}
		}
	}

	BuiltinsHelper helper = null;

	public GenericTclInstall(IInterpreterInstallType type, String id) {
		super(type, id);
	}

	public IInterpreterRunner getInterpreterRunner(String mode) {
		IInterpreterRunner runner = super.getInterpreterRunner(mode);

		if (runner != null) {
			return runner;
		}

		if (mode.equals(ILaunchManager.RUN_MODE)) {
			return new TclInterpreterRunner(this);
		}

		return null;
	}

	public String getNatureId() {
		return TclNature.NATURE_ID;
	}

	// Builtins
	public String getBuiltinModuleContent(String name) {
		if (helper == null) {
			helper = new BuiltinsHelper();
			try {
				helper.load();
			} catch (IOException e) {
				if (DLTKCore.DEBUG) {
					e.printStackTrace();
				}
			} catch (CoreException e) {
				if (DLTKCore.DEBUG) {
					e.printStackTrace();
				}
			}
		}
		return helper.source.toString();
	}

	public String[] getBuiltinModules() {
		return new String[] { "builtins.tcl" };
	}
}