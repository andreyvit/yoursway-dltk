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

import java.util.Vector;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.wst.javascript.ui.internal.common.ContentElement;
import org.eclipse.wst.javascript.ui.internal.editor.ContentElementProvider;


class JSTreeContentProvider implements ITreeContentProvider {

	private ContentElementProvider fContentElementProvider = new JSContentElementProvider();

	/**
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose() {
	}

	/**
	 * @see ITreeContentProvider#getChildren(Object parentElement)
	 */
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof ContentElement) {
			Vector children = ((ContentElement) parentElement).getChildren();
			if (children != null)
				return children.toArray();
		}
		return null;
	}

	/**
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(Object
	 *      inputElement)
	 */
	public Object[] getElements(Object inputElement) {
		return fContentElementProvider.getContentElements(inputElement).toArray();
	}

	/**
	 * @see ITreeContentProvider#getParent(Object element)
	 */
	public Object getParent(Object element) {
		if (element instanceof ContentElement) {
			return ((ContentElement) element).getParent();
		}
		return null;
	}

	/**
	 * @see ITreeContentProvider#hasChildren(Object element)
	 */
	public boolean hasChildren(Object element) {
		if (element instanceof ContentElement) {
			return ((ContentElement) element).hasChildren(element);
		}
		return false;
	}

	/**
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(Viewer
	 *      viewer, Object oldInput, Object newInput)
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}
}
