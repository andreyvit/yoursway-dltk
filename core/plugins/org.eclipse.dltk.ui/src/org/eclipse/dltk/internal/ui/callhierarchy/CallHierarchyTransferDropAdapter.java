/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 * 			(report 36180: Callers/Callees view)
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.callhierarchy;

import java.util.List;

import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.internal.corext.callhierarchy.CallHierarchy;
import org.eclipse.dltk.internal.ui.scriptview.SelectionTransferDropAdapter;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;


class CallHierarchyTransferDropAdapter extends SelectionTransferDropAdapter {

	private CallHierarchyViewPart fCallHierarchyViewPart;

	public CallHierarchyTransferDropAdapter(CallHierarchyViewPart viewPart, StructuredViewer viewer) {
		super(viewer);
		fCallHierarchyViewPart= viewPart;
	}	
	
	/**
	 * {@inheritDoc}
	 */
	protected void doInputView(Object inputElements) {
		fCallHierarchyViewPart.setInputElements((IMember[]) inputElements);
	}
	
	/**
	 * {@inheritDoc}
	 */
	protected Object getInputElement(ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			List elements= ((IStructuredSelection) selection).toList();
			if (CallHierarchy.arePossibleInputElements(elements)) {
				return elements.toArray(new IMember[elements.size()]);
			}
		}
		return null;
	}
}
