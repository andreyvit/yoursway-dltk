/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.debug.ui.preferences;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.dltk.ui.ScriptElementImageProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * Label provider for Filter model objects
 */
public class FilterLabelProvider extends LabelProvider implements
		ITableLabelProvider {
	ScriptElementImageProvider provider;

	public FilterLabelProvider() {
		provider = new ScriptElementImageProvider();
	}

	private static final Image IMG_PKG = DLTKPluginImages.getDescriptor(
			DLTKPluginImages.IMG_OBJS_PACKAGE).createImage();
	private Map typeImages = new HashMap();

	/**
	 * @see ITableLabelProvider#getColumnText(Object, int)
	 */
	public String getColumnText(Object object, int column) {
		if (column == 0) {
			return ((Filter) object).getName();
		}
		return ""; //$NON-NLS-1$
	}

	/**
	 * @see ILabelProvider#getText(Object)
	 */
	public String getText(Object element) {
		return ((Filter) element).getName();
	}

	/**
	 * @see ITableLabelProvider#getColumnImage(Object, int)
	 */
	public Image getColumnImage(Object object, int column) {
		Filter filter = (Filter) object;
		String name = filter.getName();
		if (name.endsWith("*") || name.equals("")) { //$NON-NLS-1$ //$NON-NLS-2$
			return IMG_PKG;
		}
		Integer mod = new Integer(filter.getModifiers());
		if (typeImages.containsKey(mod)) {
			return (Image) typeImages.get(mod);
		} else {
			Image img = provider.getTypeImageDescriptor(filter.getModifiers(),
					false).createImage();
			typeImages.put(mod, img);
			return img;
		}
	}
}
