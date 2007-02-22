/*******************************************************************************
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.debug.ui.interpreters;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.dltk.debug.ui.DLTKDebugUIPlugin;
import org.eclipse.dltk.internal.debug.ui.DebugImageDescriptor;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;


public class LibraryLabelProvider extends LabelProvider {

	public Image getImage(Object element) {
		if (element instanceof LibraryStandin) {
			LibraryStandin library= (LibraryStandin) element;
			String key = null;
			key = DLTKPluginImages.IMG_OBJS_EXTJAR;
			IStatus status = library.validate();
			if (!status.isOK()) {
				ImageDescriptor base = DLTKPluginImages.getDescriptor(key);
				DebugImageDescriptor descriptor= new DebugImageDescriptor(base, DebugImageDescriptor.IS_OUT_OF_SYNCH);
				return DLTKDebugUIPlugin.getImageDescriptorRegistry().get(descriptor);
			}
			return DLTKPluginImages.get(key);
		} 
		return null;
	}

	public String getText(Object element) {
		if (element instanceof LibraryStandin) {
			return ((LibraryStandin)element).getSystemLibraryPath().toOSString();
		} 
		return null;
	}

}
