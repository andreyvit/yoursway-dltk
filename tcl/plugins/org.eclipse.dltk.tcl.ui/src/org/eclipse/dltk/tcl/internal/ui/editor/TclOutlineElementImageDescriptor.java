/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.ui.editor;

import org.eclipse.dltk.ast.Modifiers;
import org.eclipse.dltk.internal.ui.editor.AnnotatedImageDescriptor;
import org.eclipse.dltk.tcl.ast.TclConstants;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;

public class TclOutlineElementImageDescriptor extends AnnotatedImageDescriptor {

	private int fFlags;

	public TclOutlineElementImageDescriptor(
			ImageDescriptor baseImageDescriptor, Point size, int flags) {
		super(baseImageDescriptor, size);
		this.fFlags = flags;
	}

	protected void drawAnnotations() {
		ImageData data = null;

		if ((fFlags & Modifiers.AccGlobal) != 0) {
			data = getImageData(DLTKPluginImages.DESC_OVR_FIELD_GLOBAL);
		} else if ((fFlags == TclConstants.TCL_FIELD_TYPE_NAMESPACE)) {
			data = getImageData(DLTKPluginImages.DESC_OVR_FIELD_NAMESPACE);
		} else if ((fFlags & Modifiers.AccUpVar) != 0) {
			data = getImageData(DLTKPluginImages.DESC_OVR_FIELD_UPVAR);
		} else if ((fFlags == TclConstants.TCL_FIELD_TYPE_INDEX)) {
			data = getImageData(DLTKPluginImages.DESC_OVR_FIELD_INDEX);
		}

		if (data != null) {
			drawImageTopRight(data);
		}
	}
}