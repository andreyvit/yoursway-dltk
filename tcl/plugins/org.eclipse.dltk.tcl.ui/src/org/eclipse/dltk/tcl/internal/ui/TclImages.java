/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.ui;

import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.ui.PluginImagesHelper;
import org.eclipse.jface.resource.ImageDescriptor;

public class TclImages {
	private static final PluginImagesHelper helper = new PluginImagesHelper(
			TclUI.getDefault().getBundle(), new Path("/icons"));

	public static final ImageDescriptor PROJECT_DECARATOR = helper
			.createUnManaged(PluginImagesHelper.T_OVR, "tcl_ovr.gif");

	public static final ImageDescriptor DESC_WIZBAN_PROJECT_CREATION = helper
			.createUnManaged(PluginImagesHelper.T_WIZBAN,
					"projectcreate_wiz.png");

	public static final ImageDescriptor DESC_WIZBAN_FILE_CREATION = helper
			.createUnManaged(PluginImagesHelper.T_WIZBAN, "filecreate_wiz.png");
}
