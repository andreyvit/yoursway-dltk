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
				int flags = field.getFlags();
				ImageDescriptor baseImage = new ImageImageDescriptor(image);
				Rectangle bounds = image.getBounds();
				ImageDescriptor dsc = new TclOutlineElementImageDescriptor(
						baseImage, flags,
						new Point(bounds.width, bounds.height));
				return dsc.createImage();
			}
		} catch (ModelException e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return image;
	}
}
