/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.dnd;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.ui.editor.EditorUtility;
import org.eclipse.jface.util.TransferDragSourceListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorInputTransfer;
import org.eclipse.ui.part.EditorInputTransfer.EditorInputData;

public class EditorInputTransferDragAdapter extends DragSourceAdapter implements
		TransferDragSourceListener {

	private ISelectionProvider fProvider;
	private ArrayList/* <EditorInputData> */fEditorInputDatas;

	public EditorInputTransferDragAdapter(ISelectionProvider provider) {
		Assert.isNotNull(provider);
		fProvider = provider;
	}

	/*
	 * @see TransferDragSourceListener#getTransfer
	 */
	public Transfer getTransfer() {
		return EditorInputTransfer.getInstance();
	}

	/*
	 * @see org.eclipse.swt.dnd.DragSourceListener#dragStart
	 */
	public void dragStart(DragSourceEvent event) {
		fEditorInputDatas = new ArrayList();

		ISelection selection = fProvider.getSelection();
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			for (Iterator iter = structuredSelection.iterator(); iter.hasNext();) {
				Object element = iter.next();
				IEditorInput editorInput = null;
				try {
					editorInput = EditorUtility.getEditorInput(element);
				} catch (ModelException e1) {
					if (DLTKCore.DEBUG) {
						e1.printStackTrace();
					}
				}
				if (editorInput != null && editorInput.getPersistable() != null) {
					String editorId = EditorUtility.getEditorID(editorInput,
							element);
					// see
					// org.eclipse.ui.internal.ide.EditorAreaDropAdapter.openNonExternalEditor(..):
					IEditorRegistry editorReg = PlatformUI.getWorkbench()
							.getEditorRegistry();
					IEditorDescriptor editorDesc = editorReg
							.findEditor(editorId);
					if (editorDesc != null && !editorDesc.isOpenExternal()) {
						fEditorInputDatas.add(EditorInputTransfer
								.createEditorInputData(editorId, editorInput));
					}
				}
			}
		}

		event.doit = fEditorInputDatas.size() > 0;
	}

	/*
	 * @see org.eclipse.swt.dnd.DragSourceListener#dragSetData
	 */
	public void dragSetData(DragSourceEvent event) {
		if (EditorInputTransfer.getInstance().isSupportedType(event.dataType)
				&& fEditorInputDatas.size() > 0) {
			event.data = fEditorInputDatas
					.toArray(new EditorInputData[fEditorInputDatas.size()]);
		}
	}

	/*
	 * @see org.eclipse.swt.dnd.DragSourceListener#dragFinished
	 */
	public void dragFinished(DragSourceEvent event) {
		fEditorInputDatas = null;
		Assert.isTrue(event.detail != DND.DROP_MOVE);
	}
}
