/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.ui;

import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.ui.PluginImagesHelper;
import org.eclipse.jface.resource.ImageDescriptor;

public class RubyImages {
	private static final PluginImagesHelper helper = new PluginImagesHelper(
			RubyUI.getDefault().getBundle(), new Path("/icons"));
	
	public static final ImageDescriptor PROJECT_DECARATOR = helper
			.createUnManaged(PluginImagesHelper.T_OVR, "ruby_ovr.gif");

	public static final ImageDescriptor DESC_WIZBAN_PROJECT_CREATION = helper
			.createUnManaged(PluginImagesHelper.T_WIZBAN,
					"projectcreate_wiz.png");

	public static final ImageDescriptor DESC_WIZBAN_FILE_CREATION = helper
			.createUnManaged(PluginImagesHelper.T_WIZBAN, "filecreate_wiz.png");

	public static final ImageDescriptor DESC_OVR_STATIC_FIELD = helper
			.createUnManaged(PluginImagesHelper.T_OVR, "static.png");

	public static final ImageDescriptor DESC_OVR_CONST_FIELD = helper
			.createUnManaged(PluginImagesHelper.T_OVR, "const.png");
}
