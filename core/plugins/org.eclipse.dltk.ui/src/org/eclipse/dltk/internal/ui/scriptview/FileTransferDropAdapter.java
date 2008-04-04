/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *

 *******************************************************************************/
package org.eclipse.dltk.internal.ui.scriptview;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.corext.util.Resources;
import org.eclipse.dltk.internal.ui.dnd.DLTKViewerDropAdapter;
import org.eclipse.dltk.ui.util.ExceptionHandler;
import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.actions.CopyFilesAndFoldersOperation;

/**
 * Adapter to handle file drop from other applications.
 */
public class FileTransferDropAdapter extends DLTKViewerDropAdapter implements
		TransferDropTargetListener {

	public FileTransferDropAdapter(StructuredViewer viewer) {
		super(viewer);

		setScrollEnabled(true);
		setExpandEnabled(true);
		setFeedbackEnabled(false);
	}

	// ---- TransferDropTargetListener interface
	// ---------------------------------------

	/**
	 * {@inheritDoc}
	 */
	public Transfer getTransfer() {
		return FileTransfer.getInstance();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isEnabled(DropTargetEvent event) {
		Object target = event.item != null ? event.item.getData() : null;
		if (target == null) {
			return false;
		}
		return target instanceof IModelElement || target instanceof IResource;
	}

	// ---- Actual DND
	// -----------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	public boolean validateDrop(Object target, int operation,
			TransferData transferType) {
		return determineOperation(target, operation, transferType,
				DND.DROP_MOVE | DND.DROP_LINK | DND.DROP_COPY) != DND.DROP_NONE;
	}

	/**
	 * {@inheritDoc}
	 */
	protected int determineOperation(Object target, int operation,
			TransferData transferType, int operations) {

		boolean isScriptFolder = target instanceof IScriptFolder;
		boolean isScriptProject = target instanceof IScriptProject;
		boolean isProjectFragment = target instanceof IProjectFragment;
		boolean isContainer = target instanceof IContainer;

		if (!(isScriptFolder || isScriptProject || isProjectFragment || isContainer)) {
			return DND.DROP_NONE;
		}

		if (isContainer) {
			IContainer container = (IContainer) target;
			if (container.isAccessible() && !Resources.isReadOnly(container)) {
				return DND.DROP_COPY;
			}
		} else {
			IModelElement element = (IModelElement) target;
			if (!element.isReadOnly()) {
				return DND.DROP_COPY;
			}
		}

		return DND.DROP_NONE;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean performDrop(final Object data) {
		try {
			int operation = getCurrentOperation();

			if (data == null || !(data instanceof String[])
					|| operation != DND.DROP_COPY) {
				return false;
			}

			final IContainer target = getActualTarget(getCurrentTarget());
			if (target == null) {
				return false;
			}

			// Run the import operation asynchronously.
			// Otherwise the drag source (e.g., Windows Explorer) will be
			// blocked
			// while the operation executes. Fixes bug 35796.
			Display.getCurrent().asyncExec(new Runnable() {
				public void run() {
					getShell().forceActive();
					new CopyFilesAndFoldersOperation(getShell()).copyFiles(
							(String[]) data, target);
				}
			});
			return false;
		} catch (ModelException e) {
			String title = ScriptMessages.DropAdapter_errorTitle;
			String message = ScriptMessages.DropAdapter_errorMessage;
			ExceptionHandler.handle(e, getShell(), title, message);
		}
		return true;
	}

	private IContainer getActualTarget(Object dropTarget) throws ModelException {
		if (dropTarget instanceof IContainer) {
			return (IContainer) dropTarget;
		} else if (dropTarget instanceof IModelElement) {
			return getActualTarget(((IModelElement) dropTarget)
					.getCorrespondingResource());
		}
		return null;
	}

	private Shell getShell() {
		return getViewer().getControl().getShell();
	}
}
