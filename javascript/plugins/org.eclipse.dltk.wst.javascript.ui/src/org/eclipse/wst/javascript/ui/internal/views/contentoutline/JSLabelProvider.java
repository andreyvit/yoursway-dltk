/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.javascript.ui.internal.views.contentoutline;



import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.javascript.ui.internal.common.ContentElement;
import org.eclipse.wst.javascript.ui.internal.common.JSContentElementConstants;
import org.eclipse.wst.javascript.ui.internal.editor.JSEditorPluginImageHelper;
import org.eclipse.wst.javascript.ui.internal.editor.JSEditorPluginImages;


class JSLabelProvider extends LabelProvider {

	Image imageForFunc = JSEditorPluginImageHelper.getInstance().getImageDescriptor(JSEditorPluginImages.IMG_OBJ_PUBLIC).createImage();
	Image imageForVar = JSEditorPluginImageHelper.getInstance().getImageDescriptor(JSEditorPluginImages.IMG_OBJ_DEFAULT).createImage();

	public Image getImage(Object element) {
		Image image = null;

		if (element != null) {
			ContentElement contentElement = (ContentElement) element;

			if (contentElement.getType() == JSContentElementConstants.JS_FUNCTION)
				image = imageForFunc;
			else if (contentElement.getType() == JSContentElementConstants.JS_VARIABLE)
				image = imageForVar;
		}

		return image;
	}

	public void dispose() {
		imageForFunc.dispose();
		imageForVar.dispose();
	}
}
