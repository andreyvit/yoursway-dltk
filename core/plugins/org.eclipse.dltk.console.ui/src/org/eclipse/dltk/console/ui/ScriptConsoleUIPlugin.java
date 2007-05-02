/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.console.ui;

import java.net.URL;

import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class ScriptConsoleUIPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.dltk.console.ui";

	// The shared instance
	private static ScriptConsoleUIPlugin plugin;

	/**
	 * The constructor
	 */
	public ScriptConsoleUIPlugin() {
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);

		ILaunchManager launchManager = DebugPlugin.getDefault()
				.getLaunchManager();
		launchManager.addLaunchListener(ScriptConsoleManager.getInstance());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);

		ScriptConsoleManager manager = ScriptConsoleManager.getInstance();

		DebugPlugin.getDefault().getLaunchManager().removeLaunchListener(
				manager);
		manager.closeAll();
	}

	public ImageDescriptor getImageDescriptor(String key) {
		ImageRegistry registry = getImageRegistry();
		return registry.getDescriptor(key);
	}

	public Image getImage(String key) {
		ImageRegistry registry = getImageRegistry();
		return registry.get(key);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static ScriptConsoleUIPlugin getDefault() {
		return plugin;
	}

	protected void initializeImageRegistry(ImageRegistry registry) {
		String[][] images = new String[][] {
				{ "icons/save.gif", ScriptConsoleUIConstants.SAVE_SESSION_ICON },
				{ "icons/terminate.gif",
						ScriptConsoleUIConstants.TERMINATE_ICON } };

		for (int i = 0; i < images.length; ++i) {
			URL url = getDefault().getBundle().getEntry(images[i][0]);
			registry.put(images[i][1], ImageDescriptor.createFromURL(url));
		}
	}
}
