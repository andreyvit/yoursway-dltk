package org.eclipse.dltk.javascript.internal.ui;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.ui.ImageUtil;
import org.eclipse.jface.resource.ImageDescriptor;

public class JavaScriptImages {
	public static final IPath ICONS_PATH = new Path("/icons/full/");

	private static final ImageUtil util = new ImageUtil(JavaScriptUI.getDefault()
			.getBundle(), ICONS_PATH);

	public static final ImageDescriptor DESC_WIZBAN_PROJECT_CREATION = util
			.createUnManaged(ImageUtil.T_WIZBAN, "newjscriptfile_wiz.gif");

	
}
