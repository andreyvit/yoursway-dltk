/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.scriptview;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.corext.util.Resources;
import org.eclipse.dltk.internal.ui.dnd.DLTKViewerDropAdapter;
import org.eclipse.dltk.ui.util.ExceptionHandler;
import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.actions.CopyFilesAndFoldersOperation;


/**
 * Adapter to handle file drop from other applications.
 */ 
class FileTransferDropAdapter extends DLTKViewerDropAdapter implements TransferDropTargetListener {
	
	FileTransferDropAdapter(AbstractTreeViewer viewer) {
		super(viewer, DND.FEEDBACK_SCROLL | DND.FEEDBACK_EXPAND);
	}

	//---- TransferDropTargetListener interface ---------------------------------------
	
	public Transfer getTransfer() {
		return FileTransfer.getInstance();
	}
	
	public boolean isEnabled(DropTargetEvent event) {
		Object target= event.item != null ? event.item.getData() : null;
		if (target == null)
			return false;
		return target instanceof IModelElement || target instanceof IResource;
	}

	//---- Actual DND -----------------------------------------------------------------
	
	public void validateDrop(Object target, DropTargetEvent event, int operation) {
		event.detail= DND.DROP_NONE;
		
		boolean isScriptFolder= target instanceof IScriptFolder;
		boolean isScriptProject= target instanceof IDLTKProject;
		boolean isProjectFragment= target instanceof IProjectFragment;
		boolean isContainer= target instanceof IContainer;
		
		if (!(isScriptFolder || isScriptProject || isProjectFragment || isContainer)) 
			return;
			
		if (isContainer) {
			IContainer container= (IContainer)target;
			if (container.isAccessible() && !Resources.isReadOnly(container))
				event.detail= DND.DROP_COPY;
		} else {
			IModelElement element= (IModelElement)target;
			if (!element.isReadOnly()) 
				event.detail= DND.DROP_COPY;
		}
			
		return;	
	}

	public void drop(Object dropTarget, final DropTargetEvent event) {
		try {
			int operation= event.detail;

			event.detail= DND.DROP_NONE;
			final Object data= event.data;
			if (data == null || !(data instanceof String[]) || operation != DND.DROP_COPY)
				return;

			final IContainer target= getActualTarget(dropTarget);
			if (target == null)
				return;

			// Run the import operation asynchronously. 
			// Otherwise the drag source (e.g., Windows Explorer) will be blocked 
			// while the operation executes. Fixes bug 35796.
			Display.getCurrent().asyncExec(new Runnable() {
				public void run() {
					getShell().forceActive();
					new CopyFilesAndFoldersOperation(getShell()).copyFiles((String[]) data, target);
					// Import always performs a copy.
					event.detail= DND.DROP_COPY;
				}
			});
		} catch (ModelException e) {
			String title= ScriptMessages.DropAdapter_errorTitle; 
			String message= ScriptMessages.DropAdapter_errorMessage; 
			ExceptionHandler.handle(e, getShell(), title, message);
		}
	}
	
	private IContainer getActualTarget(Object dropTarget) throws ModelException{
		if (dropTarget instanceof IContainer)
			return (IContainer)dropTarget;
		else if (dropTarget instanceof IModelElement)
			return getActualTarget(((IModelElement)dropTarget).getCorrespondingResource());	
		return null;
	}
	
	private Shell getShell() {
		return getViewer().getControl().getShell();
	}
}
