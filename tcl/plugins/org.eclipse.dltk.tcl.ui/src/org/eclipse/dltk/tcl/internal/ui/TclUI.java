/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.ui;

import org.eclipse.dltk.console.ui.ScriptConsoleUIPlugin;
import org.eclipse.dltk.tcl.core.TclNature;
import org.eclipse.dltk.tcl.internal.ui.text.TclTextTools;
import org.eclipse.dltk.ui.DLTKUILanguageManager;
import org.eclipse.dltk.ui.IDLTKUILanguageToolkit;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;


/**
 * The main plugin class to be used in the desktop.
 */
public class TclUI extends AbstractUIPlugin {
	public static final String PLUGIN_ID = "org.eclipse.dltk.tcl.ui";
	public static final String ID_ACTION_SET = "org.eclipse.dltk.tcl.ui.TclActionSet";
	
	//The shared instance.
	private static TclUI plugin;
	
	private TclTextTools fTclTextTools;
	
	/**
	 * The constructor.
	 */
	public TclUI() {
		plugin = this;
	}

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		
		ScriptConsoleUIPlugin.getDefault();
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

	/**
	 * Returns the shared instance.
	 */
	public static TclUI getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path.
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.dltk.tcl.ui", path);
	}
	
	public synchronized TclTextTools getTextTools() {
		IDLTKUILanguageToolkit languageToolkit = DLTKUILanguageManager.getLanguageToolkit(TclNature.NATURE_ID);
		return (TclTextTools) languageToolkit.getTextTools();
	}	
	public synchronized TclTextTools internalgetTextTools() {
		if (fTclTextTools == null)
			fTclTextTools= new TclTextTools(true);
		return fTclTextTools;			
	}
}
