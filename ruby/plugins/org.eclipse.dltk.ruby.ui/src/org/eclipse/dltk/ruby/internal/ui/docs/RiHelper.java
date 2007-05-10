/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.ui.docs;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.dltk.launching.DLTKLaunchUtil;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.ruby.core.RubyNature;

public class RiHelper {
	private static RiHelper instance;
	
	private final static boolean[] busy = new boolean[] { false };

	public static RiHelper getInstance() {
		if (instance == null) {
			instance = new RiHelper();
		}

		return instance;
	}

	protected RiHelper() {

	}
	
	public String getDocFor(String keyword) {
		synchronized (busy) {
			while (busy[0]) {
				try {
//					System.out.println("Ri is busy, waiting...");
					busy.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
					return "";
				}
			}
//			System.out.println("Fetching doc from ri...");
			busy[0] = true;
		}
		IInterpreterInstall install = DLTKLaunchUtil
				.getDefaultInterpreterInstall(RubyNature.NATURE_ID);

		File interpreterPath = install.getInstallLocation();

		String[] cmdLine = new String[] { interpreterPath.toString(),
				interpreterPath.getParent() + File.separator + "ri",
				"--format", "html", keyword };

		BufferedReader input = null;
		OutputStreamWriter output = null;

		String result = null;
		
		try {
			try {
				Process process = DebugPlugin.exec(cmdLine, null);

				input = new BufferedReader(new InputStreamReader(process
						.getInputStream()));

				StringBuffer sb = new StringBuffer();
				String line = null;
				while ((line = input.readLine()) != null) {
					sb.append(line);
					sb.append('\n');
					
				}

				result = sb.toString();
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
		
		synchronized (busy) {
//			System.out.println("Doc from ri successfully fetched");
			busy[0] = false;
			busy.notify();
		}

		return result;
	}
}
