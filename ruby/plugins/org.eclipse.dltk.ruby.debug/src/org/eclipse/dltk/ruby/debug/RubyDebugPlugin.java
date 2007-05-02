/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.debug;

import java.io.IOException;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.dltk.utils.DeployHelper;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class RubyDebugPlugin extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.dltk.ruby.debug";

	// The shared instance
	private static RubyDebugPlugin plugin;

	/**
	 * The constructor
	 */
	public RubyDebugPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static RubyDebugPlugin getDefault() {
		return plugin;
	}
	
	private static final String DEBUGGER_DIR = "debugger";

	public IPath deployDebuggerSource() throws IOException {		
		return DeployHelper.deploy(this, DEBUGGER_DIR);		
	}
}
