/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.validators.internal.ui;

import java.awt.Dialog;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class ValidatorsUI extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.dltk.validators.ui";

	// The shared instance
	private static ValidatorsUI plugin;
	
	/**
	 * The constructor
	 */
	public ValidatorsUI() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
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
	public static ValidatorsUI getDefault() {
		return plugin;
	}
	public static void log(String message) {
		log(new Status(IStatus.ERROR, PLUGIN_ID, IStatus.ERROR,
				message, null));
	}
	public static void log(IStatus status) {
		getDefault().getLog().log(status);
	}

	public static void log(Throwable e) {
		log(new Status(IStatus.ERROR, PLUGIN_ID, IStatus.ERROR, e
				.getMessage(), e));
	}
	
	public ImageDescriptor getImageDescriptor(String key) {
		ImageRegistry registry = getImageRegistry();
		ImageDescriptor descriptor = registry.getDescriptor(key);
		if( descriptor == null ) {
			descriptor = DLTKPluginImages.createImageDescriptor(this.getBundle(), new Path(key), true);
			registry.put(key, descriptor);
		}
		return descriptor;
	}
}
