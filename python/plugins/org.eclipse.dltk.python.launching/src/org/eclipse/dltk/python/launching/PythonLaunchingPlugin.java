/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.launching;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class PythonLaunchingPlugin extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.dltk.python.launching";

	// The shared instance
	private static PythonLaunchingPlugin plugin;

	public PythonLaunchingPlugin() {
		plugin = this;
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	public static PythonLaunchingPlugin getDefault() {
		return plugin;
	}

	public static String getUniqueIdentifier() {
		return PLUGIN_ID;
	}

	public static void log(IStatus status) {
		getDefault().getLog().log(status);
	}

	public static void log(String message) {
		log(new Status(IStatus.ERROR, getUniqueIdentifier(), IStatus.ERROR,
				message, null));
	}

	public static void log(Throwable e) {
		log(new Status(IStatus.ERROR, getUniqueIdentifier(), IStatus.ERROR, e
				.getMessage(), e));
	}
}
