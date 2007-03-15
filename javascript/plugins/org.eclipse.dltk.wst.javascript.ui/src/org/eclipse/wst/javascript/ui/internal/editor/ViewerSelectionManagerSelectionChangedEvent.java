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
package org.eclipse.wst.javascript.ui.internal.editor;



import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;

/**
 * This event is used by the SimpleTreeViewer to tell the SimpleViewerSelectionManager that
 * the selection is being set programatically, instead of coming form a user click on the tree viewer.
 */
public class ViewerSelectionManagerSelectionChangedEvent extends SelectionChangedEvent {


	/**
	 * Default <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;

	public ViewerSelectionManagerSelectionChangedEvent(ISelectionProvider source, ISelection selection) {
		super(source, selection);
	}
}
