/*******************************************************************************
 * Copyright (c) 2006, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.debug.ui.interpreters;

import org.eclipse.debug.internal.ui.DebugPluginImages;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.dltk.launching.EnvironmentVariable;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;


public class EnvironmentVariablesLabelProvider extends LabelProvider {

	public Image getImage(Object element) {
		return DebugPluginImages
		.getImage(IDebugUIConstants.IMG_OBJS_ENVIRONMENT);
	}

	public String getText(Object element) {
		if (element instanceof EnvironmentVariable ) {
			EnvironmentVariable var = (EnvironmentVariable) element;
			return var.getName() + "=" + var.getValue();
		} 
		return null;
	}

}
