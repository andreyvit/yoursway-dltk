package org.eclipse.dltk.tcl.internal.debug.ui.interpreters;

import org.eclipse.dltk.internal.debug.ui.interpreters.LibraryLabelProvider;
import org.eclipse.dltk.tcl.internal.launching.PackagesHelper;
import org.eclipse.dltk.tcl.internal.launching.PackagesHelper.PackageLocation;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.swt.graphics.Image;

public class TclLibraryLabelProvider extends LibraryLabelProvider {
	public Image getImage(Object element) {
		if (element instanceof PackageLocation) {
			return DLTKPluginImages.get(DLTKPluginImages.IMG_OBJS_EXTJAR);
		} else if (element instanceof PackagesHelper.Package) {
			return DLTKPluginImages.get(DLTKPluginImages.IMG_OBJS_PACKAGE);
		}
		return super.getImage(element);
	}

	public String getText(Object element) {
		if (element instanceof PackageLocation) {
			return ((PackageLocation) element).getPath();
		} else if (element instanceof PackagesHelper.Package) {
			return ((PackagesHelper.Package) element).getName();
		}
		return super.getText(element);
	}
}
