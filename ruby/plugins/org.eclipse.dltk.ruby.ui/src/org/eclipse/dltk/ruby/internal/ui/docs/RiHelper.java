/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.ui.docs;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.WeakHashMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.dltk.launching.DLTKLaunchUtil;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterInstallChangedListener;
import org.eclipse.dltk.launching.PropertyChangeEvent;
import org.eclipse.dltk.launching.ScriptRuntime;
import org.eclipse.dltk.ruby.core.RubyNature;
import org.eclipse.dltk.ruby.internal.ui.RubyUI;
import org.eclipse.dltk.utils.DeployHelper;

public class RiHelper {
	private final static String SUFFIX = "DLTKDOCEND";
	
	private static RiHelper instance;

	private Process process;

	private WeakHashMap cache = new WeakHashMap();

	private InputStream inputStream;

	private OutputStream outputStream;

	private InputStreamReader inputReader;

	IInterpreterInstallChangedListener interpreterInstallChangedListener = new IInterpreterInstallChangedListener() {

		public void defaultInterpreterInstallChanged(
				IInterpreterInstall previous, IInterpreterInstall current) {
			if (process != null) {
				process.destroy();
				process = null;
			}
		}

		public void interpreterAdded(IInterpreterInstall Interpreter) {}

		public void interpreterChanged(PropertyChangeEvent event) {}

		public void interpreterRemoved(IInterpreterInstall Interpreter) {}
		
	};

	public static RiHelper getInstance() {
		if (instance == null) {
			instance = new RiHelper();
		}

		return instance;
	}

	protected RiHelper() {		
		ScriptRuntime.addInterpreterInstallChangedListener(interpreterInstallChangedListener);
	}
	
	private void checkProcess() {
		boolean requireCreation = false;
		if (process == null)
			requireCreation = true;
		else {
			try {
				process.exitValue();
				requireCreation = true;
			} catch (IllegalThreadStateException e) {
				// ok, process is alive
			}			
		}
		if (requireCreation) {
			process = null;
			IInterpreterInstall install = DLTKLaunchUtil
			.getDefaultInterpreterInstall(RubyNature.NATURE_ID);
			if (install != null) {
				IPath deployedPath;
				try {
					deployedPath = DeployHelper.deploy(RubyUI.getDefault(), "support/").append("dltkri.rb");
					String irbPath = deployedPath.toFile().getAbsolutePath();
					process = DebugPlugin.exec(new String[] {
							install.getInstallLocation().getAbsolutePath(),
							irbPath
						}, null);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (CoreException e) {
					e.printStackTrace();
				}
				if (process != null) {
					inputStream = process.getInputStream();
					outputStream = process.getOutputStream();
					inputReader = new InputStreamReader(inputStream);
				}
			}
		}
	}

	public synchronized String getDocFor(String keyword) {		
		Object cached = cache.get(keyword);
		if (cached != null) {
			return (String) cached;
		}
		
		String result = null;			
		
		checkProcess();
		
		if (process != null) {			
			try {
				outputStream.write((keyword + "\n").getBytes());
				outputStream.flush();
				
				// TODO: Please, use StringBuffer, 100 kb - very bad solution
				char data[] = new char[100000]; // 100 kb is enough for ANY rdoc 
				int pos = 0;
				while (true) {
					if (pos >= SUFFIX.length()) {
						String suffix = String.copyValueOf(data, pos - SUFFIX.length(), SUFFIX.length());
						if (suffix.equals(SUFFIX)) {
							break;
						}
					}
					int c = inputReader.read();
					if (c < 0) {
						break;	
					}
					data[pos++] = (char) c;
				}
				result = String.valueOf(data, 0, pos - SUFFIX.length());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
						
		if (result != null && result.length() > 0) {
			cache.put(keyword, result);
		}

		return result;
	}
	
}
