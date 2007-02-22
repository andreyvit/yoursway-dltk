package org.eclipse.dltk.tcl.internal.ui.editor;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.tcl.ast.TclConstants;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.jface.resource.CompositeImageDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;

public class TclOutlineElementImageDescriptor extends CompositeImageDescriptor {

	private Point fSize;
	private int fFlags;

	private ImageDescriptor fBaseImage;

	public TclOutlineElementImageDescriptor(
			ImageDescriptor baseImageDescriptor, int flags, Point size) {
		fBaseImage = baseImageDescriptor;
		Assert.isNotNull(fBaseImage);

		fFlags = flags;
		Assert.isTrue(fFlags >= 0);

		fSize = size;
		Assert.isNotNull(fSize);
	}

	public void setImageSize(Point size) {
		Assert.isNotNull(size);
		Assert.isTrue(size.x >= 0 && size.y >= 0);

		fSize = size;
	}

	public Point getImageSize() {
		return new Point(fSize.x, fSize.y);
	}

	private ImageData getImageData(ImageDescriptor descriptor) {
		if (this.fBaseImage != null) {
			ImageData data = descriptor.getImageData(); // see bug 51965:
			// getImageData can
			// return null
			if (data == null) {
				data = DEFAULT_IMAGE_DATA;
				System.err.println("Image data not available: "
						+ descriptor.toString());
				// DLTKUIPlugin.logErrorMessage("Image data not available: " +
				// descriptor.toString()); //$NON-NLS-1$
			}
			return data;
		} else {
			System.err.println("Image data not available: "
					+ descriptor.toString());
			return DEFAULT_IMAGE_DATA;
		}
	}

	private void drawTopRight() {
		Point size = getSize();
		int x = 0;

		ImageData data = null;

		if ((fFlags == TclConstants.TCL_FIELD_TYPE_GLOBAL)) {
			data = getImageData(DLTKPluginImages.DESC_OVR_FIELD_GLOBAL);
		} else if ((fFlags == TclConstants.TCL_FIELD_TYPE_NAMESPACE)) {
			data = getImageData(DLTKPluginImages.DESC_OVR_FIELD_NAMESPACE);
		} else if ((fFlags == TclConstants.TCL_FIELD_TYPE_UPVAR)) {
			data = getImageData(DLTKPluginImages.DESC_OVR_FIELD_UPVAR);
		} else if ((fFlags == TclConstants.TCL_FIELD_TYPE_INDEX)) {
			data = getImageData(DLTKPluginImages.DESC_OVR_FIELD_INDEX);
		}

		if (data != null) {
			drawImage(data, size.x - x - data.width, 0);
			x += data.width;
		}
	}

	// Abstract method implementation
	protected void drawCompositeImage(int width, int height) {
		ImageData bg = getImageData(fBaseImage);

		if (bg != null) {
			drawImage(bg, 0, 0);
		}

		drawTopRight();
	}

	protected Point getSize() {
		return fSize;
	}
}
