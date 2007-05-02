/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.ui;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.ui.ImageUtil;
import org.eclipse.jface.resource.ImageDescriptor;

public class RubyImages {
	public static final IPath ICONS_PATH = new Path("/icons");

	private static final ImageUtil util = new ImageUtil(RubyUI.getDefault()
			.getBundle(), ICONS_PATH);

	public static final ImageDescriptor DESC_WIZBAN_PROJECT_CREATION = util
			.createUnManaged(ImageUtil.T_WIZBAN, "projectcreate_wiz.png");

	public static final ImageDescriptor DESC_WIZBAN_FILE_CREATION = util
			.createUnManaged(ImageUtil.T_WIZBAN, "filecreate_wiz.png");
	
	public static final ImageDescriptor DESC_OVR_STATIC_FIELD = util
		.createUnManaged(ImageUtil.T_OVR, "static.png");
	
	public static final ImageDescriptor DESC_OVR_CONST_FIELD = util
	.createUnManaged(ImageUtil.T_OVR, "const.png");
}
