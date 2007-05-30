/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.launching;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.dltk.launching.AbstractInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterInstallType;
import org.eclipse.dltk.launching.IInterpreterRunner;
import org.eclipse.dltk.ruby.core.RubyNature;
import org.eclipse.dltk.ruby.launching.RubyLaunchingPlugin;
import org.eclipse.dltk.utils.DeployHelper;

public class GenericRubyInstall extends AbstractInterpreterInstall {	
	
	
	public class BuiltinsHelper {
		public BuiltinsHelper() {

		}

		public String execute(String command) {			
			File builder = null;
			try {
				IPath path = DeployHelper.deploy(RubyLaunchingPlugin.getDefault(), "scripts");
				builder = path.append("builtin.rb").toFile();				
				
			} catch (IOException e1) {
				e1.printStackTrace();
				return null;
			}

			String[] cmdLine = new String[] {
					GenericRubyInstall.this.getInstallLocation()
							.getAbsolutePath(), builder.getAbsolutePath(),
					command };

			BufferedReader input = null;
			OutputStreamWriter output = null;

			try {
				try {
					Process process = DebugPlugin.exec(cmdLine, null);

					input = new BufferedReader(new InputStreamReader(process
							.getInputStream()));

					StringBuffer sb = new StringBuffer();
					String line = null;
					while ((line = input.readLine()) != null) {
						sb.append(line + "\n");
					}

					return sb.toString();
				} finally {
					if (output != null) {
						output.close();
					}

					if (input != null) {
						input.close();
					}
				}
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}
	}

	public GenericRubyInstall(IInterpreterInstallType type, String id) {
		super(type, id);
	}

	public IInterpreterRunner getInterpreterRunner(String mode) {
		IInterpreterRunner runner = super.getInterpreterRunner(mode);
		if (runner != null) {
			return runner;
		}
		
		if (mode.equals(ILaunchManager.RUN_MODE)) {
			return new RubyInterpreterRunner(this);
		} 
		
		//else if (mode.equals(ILaunchManager.DEBUG_MODE)) {
			//return new RubyInterpreterDebugger(this);
		//}

		return null;
	}

	private static final String prefix = "#### DLTK RUBY BUILTINS ####";
	private static final int prefixLength = prefix.length();
	private HashMap sources = null;

	private void initialize() {
		sources = new HashMap();
		String content = new BuiltinsHelper().execute("");
		int nl;
		int start = 0;
		int pos = content.indexOf(prefix, start);
		while (pos >= 0) {
			nl = content.indexOf('\n', pos);
			String filename = content.substring(pos + prefixLength, nl).trim();
			String data = "";
			pos = content.indexOf(prefix, nl + 1);
			if (pos != -1)
				data = content.substring(nl + 1, pos);
			else
				data = content.substring(nl + 1);
			String prev = (String) sources.get(filename);
			if (prev != null)
				data = prev + data;
			sources.put(filename, data);
		}
	}

	public String getBuiltinModuleContent(String name) {
		if (sources == null)
			initialize();
		return (String) sources.get(name);
	}

	public String[] getBuiltinModules() {
		if (sources == null)
			initialize();
		return (String[]) sources.keySet().toArray(new String[sources.size()]);
	}
	
	public String getNatureId() {
		return RubyNature.NATURE_ID;
	}
}