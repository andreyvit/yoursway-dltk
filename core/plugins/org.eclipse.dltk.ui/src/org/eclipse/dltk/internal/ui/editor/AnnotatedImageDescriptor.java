/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.editor;

import org.eclipse.jface.resource.CompositeImageDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;

public abstract class AnnotatedImageDescriptor extends CompositeImageDescriptor {

	protected static ImageData getImageData(ImageDescriptor descriptor) {
		if (descriptor != null) {
			final ImageData data = descriptor.getImageData();

			if (data != null) {
				return data;
			}
		}

		return DEFAULT_IMAGE_DATA;
	}

	private Point fSize;

	private ImageDescriptor fBaseImage;

	public AnnotatedImageDescriptor(ImageDescriptor baseImageDescriptor,
			Point size) {
		fBaseImage = baseImageDescriptor;
		fSize = size;
	}

	public void setImageSize(Point size) {
		fSize = size;
	}

	protected Point getSize() {
		return fSize;
	}

	// Abstract method implementation
	protected void drawCompositeImage(int width, int height) {
		ImageData bg = getImageData(fBaseImage);

		if (bg != null) {
			drawImage(bg, 0, 0);
		}

		drawAnnotations();
	}

	protected void drawImageTopRight(ImageData data) {
		final Point size = getSize();
		drawImage(data, size.x - data.width, 0);
	}

	protected void drawImageTopLeft(ImageData data) {
		drawImage(data, 0, 0);
	}

	protected void drawImageBottomRight(ImageData data) {
		final Point size = getSize();
		drawImage(data, size.x - data.width, size.y - data.height);
	}

	protected void drawImageBottomLeft(ImageData data) {
		final Point size = getSize();
		drawImage(data, 0, size.y - data.height);
	}
	
	protected abstract void drawAnnotations();
}
