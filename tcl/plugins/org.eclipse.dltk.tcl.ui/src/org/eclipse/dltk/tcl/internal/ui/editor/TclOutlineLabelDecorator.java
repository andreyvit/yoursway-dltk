/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.ui.editor;

import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.ui.viewsupport.ImageImageDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class TclOutlineLabelDecorator extends LabelProvider implements
		ILabelDecorator {
	public TclOutlineLabelDecorator() {
	}

	public String decorateText(String text, Object element) {
		return text;
	}

	public Image decorateImage(Image image, Object obj) {
		try {
			if (obj instanceof IField) {
				IField field = (IField) obj;
				ImageDescriptor baseImage = new ImageImageDescriptor(image);
				Rectangle bounds = image.getBounds();
				ImageDescriptor dsc = new TclOutlineElementImageDescriptor(
						baseImage, new Point(bounds.width, bounds.height),
						field.getFlags());
				
				return dsc.createImage();
			}
		} catch (ModelException e) {
			e.printStackTrace();
		}

		return image;
	}
}
