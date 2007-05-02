/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.navigator;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.corext.refactoring.reorg.ReorgMoveStarter;
import org.eclipse.dltk.internal.corext.refactoring.reorg.ReorgPolicyFactory;
import org.eclipse.dltk.internal.corext.refactoring.reorg.ReorgUtils;
import org.eclipse.dltk.internal.corext.refactoring.reorg.IReorgPolicy.ICopyPolicy;
import org.eclipse.dltk.internal.corext.refactoring.reorg.IReorgPolicy.IMovePolicy;
import org.eclipse.dltk.internal.ui.refactoring.reorg.ReorgCopyStarter;
import org.eclipse.dltk.internal.ui.refactoring.reorg.ScriptCopyProcessor;
import org.eclipse.dltk.internal.ui.refactoring.reorg.ScriptMoveProcessor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.ui.actions.CopyFilesAndFoldersOperation;
import org.eclipse.ui.navigator.CommonDropAdapter;
import org.eclipse.ui.navigator.CommonDropAdapterAssistant;
import org.eclipse.ui.views.navigator.LocalSelectionTransfer;

public class ScriptDropAssistant  extends CommonDropAdapterAssistant {
	private List fElements;
	private ScriptMoveProcessor fMoveProcessor;
	private int fCanMoveElements;
	private ScriptCopyProcessor fCopyProcessor;
	private int fCanCopyElements; 

	public IStatus handleDrop(CommonDropAdapter dropAdapter, DropTargetEvent dropTargetEvent, Object target) { 
		if (LocalSelectionTransfer.getInstance().isSupportedType(dropAdapter.getCurrentTransfer())) {
			try {

				switch (dropAdapter.getCurrentOperation()) {
					case DND.DROP_MOVE :
						handleDropMove(target);
						break;
					case DND.DROP_COPY :
						handleDropCopy(target);
						break;
				}
			} catch (ModelException e) {
				//TODO: uncomment 
				//ExceptionHandler.handle(e, PackagesMessages.SelectionTransferDropAdapter_error_title, PackagesMessages.SelectionTransferDropAdapter_error_message); 
			} catch (InvocationTargetException e) {
				//TODO: uncomment
				//ExceptionHandler.handle(e, RefactoringMessages.OpenRefactoringWizardAction_refactoring, RefactoringMessages.OpenRefactoringWizardAction_exception); 
			} catch (InterruptedException e) {
				//ok
			} finally {
				//TODO: uncomment
				// The drag source listener must not perform any operation
				// since this drop adapter did the remove of the source even
				// if we moved something.
				//event.detail= DND.DROP_NONE;
			}
			clear();
			return Status.OK_STATUS;
		} else if (FileTransfer.getInstance().isSupportedType(dropAdapter.getCurrentTransfer())) {
			try {

				final Object data = FileTransfer.getInstance().nativeToJava(dropAdapter.getCurrentTransfer());
				if (!(data instanceof String[]))
					return Status.CANCEL_STATUS;

				final IContainer targetContainer = getActualTarget(target);
				if (targetContainer == null)
					return Status.CANCEL_STATUS;


				getShell().forceActive();
				new CopyFilesAndFoldersOperation(getShell()).copyFiles((String[]) data, targetContainer);
			} catch (ModelException e) {
				//TODO: uncomment
				//String title = PackagesMessages.DropAdapter_errorTitle; 
				//String message = PackagesMessages.DropAdapter_errorMessage; 
				//ExceptionHandler.handle(e, getShell(), title, message);
			}
			return Status.OK_STATUS;
		}
		return Status.CANCEL_STATUS;
	}

	public IStatus validateDrop(Object target, int operation, TransferData transferType) { 
		IStatus result = Status.OK_STATUS;
		if (LocalSelectionTransfer.getInstance().isSupportedType(transferType)) {
			initializeSelection();
			try {
				switch (operation) {
					case DND.DROP_DEFAULT :
						if (handleValidateDefault(target) != DND.DROP_NONE) {
							result = Status.OK_STATUS;
						} else {
							result = Status.CANCEL_STATUS;
						}
						break;
					case DND.DROP_COPY :
						if (handleValidateCopy(target) != DND.DROP_NONE) {
							result = Status.OK_STATUS;
						} else {
							result = Status.CANCEL_STATUS;
						}
						break;
					case DND.DROP_MOVE :
						if (handleValidateMove(target) != DND.DROP_NONE) {
							result = Status.OK_STATUS;
						} else {
							result = Status.CANCEL_STATUS;
						} 
						break;
				}
			} catch (ModelException e) {
				//TODO: uncomment
				//ExceptionHandler.handle(e, PackagesMessages.SelectionTransferDropAdapter_error_title, PackagesMessages.SelectionTransferDropAdapter_error_message); 
				//event.detail= DND.DROP_NONE;
				result = Status.CANCEL_STATUS;
			}
		}
		return result;
	} 
	
	public boolean isSupportedType(TransferData transferType) {
		return super.isSupportedType(transferType) || FileTransfer.getInstance().isSupportedType(transferType);
	}
	
	private IContainer getActualTarget(Object dropTarget) throws ModelException {
		if (dropTarget instanceof IContainer)
			return (IContainer) dropTarget;
		else if (dropTarget instanceof IModelElement)
			return getActualTarget(((IModelElement) dropTarget).getCorrespondingResource());
		return null;
	}

	protected void initializeSelection() {
		if (fElements != null)
			return;
		ISelection s = LocalSelectionTransfer.getInstance().getSelection();
		if (!(s instanceof IStructuredSelection))
			return;
		fElements = ((IStructuredSelection) s).toList();
	}

	private void handleDropMove(final Object target) throws ModelException, InvocationTargetException, InterruptedException {
		IModelElement[] javaElements = ReorgUtils.getModelElements(fElements);
		IResource[] resources = ReorgUtils.getResources(fElements);
		ReorgMoveStarter starter = null;
		if (target instanceof IResource)
			starter = ReorgMoveStarter.create(javaElements, resources, (IResource) target);
		else if (target instanceof IModelElement)
			starter = ReorgMoveStarter.create(javaElements, resources, (IModelElement) target);
		if (starter != null)
			starter.run(getShell());
	}
 
	private void handleDropCopy(final Object target) throws ModelException, InvocationTargetException, InterruptedException {
		IModelElement[] javaElements = ReorgUtils.getModelElements(fElements);
		IResource[] resources = ReorgUtils.getResources(fElements);
		ReorgCopyStarter starter = null;
		if (target instanceof IResource)
			starter = ReorgCopyStarter.create(javaElements, resources, (IResource) target);
		else if (target instanceof IModelElement)
			starter = ReorgCopyStarter.create(javaElements, resources, (IModelElement) target);
		if (starter != null)
			starter.run(getShell());
	}
 
	private int handleValidateCopy(Object target) throws ModelException {

		final ICopyPolicy policy= ReorgPolicyFactory.createCopyPolicy(ReorgUtils.getResources(fElements), ReorgUtils.getModelElements(fElements));
		fCopyProcessor= policy.canEnable() ? new  ScriptCopyProcessor(policy) : null;
		
		if (!canCopyElements())
			return DND.DROP_NONE;	

		if (target instanceof IResource && fCopyProcessor != null && fCopyProcessor.setDestination((IResource)target).isOK())
			return DND.DROP_COPY;
		else if (target instanceof IModelElement && fCopyProcessor != null && fCopyProcessor.setDestination((IModelElement)target).isOK())
			return DND.DROP_COPY;
		else
			return DND.DROP_NONE;					
	}
 
	private int handleValidateDefault(Object target) throws ModelException {
		if (target == null)
			return DND.DROP_NONE;

		return handleValidateMove(target);
	}

	private int handleValidateMove(Object target) throws ModelException {
		if (target == null)
			return DND.DROP_NONE;
		
		IMovePolicy policy= ReorgPolicyFactory.createMovePolicy(ReorgUtils.getResources(fElements), ReorgUtils.getModelElements(fElements));
		fMoveProcessor= (policy.canEnable()) ? new ScriptMoveProcessor(policy) : null;

		if (!canMoveElements())
			return DND.DROP_NONE;	

		if (target instanceof IResource && fMoveProcessor != null && fMoveProcessor.setDestination((IResource)target).isOK())
			return DND.DROP_MOVE;
		else if (target instanceof IModelElement && fMoveProcessor != null && fMoveProcessor.setDestination((IModelElement)target).isOK())
			return DND.DROP_MOVE;
		else
			return DND.DROP_NONE;	
	}


	private boolean canMoveElements() {
		if (fCanMoveElements == 0) {
			fCanMoveElements = 2;
			if (fMoveProcessor == null)
				fCanMoveElements = 1;
		}
		return fCanMoveElements == 2;
	}


	private boolean canCopyElements() {
		if (fCanCopyElements == 0) {
			fCanCopyElements = 2;
			if (fCopyProcessor == null)
				fCanCopyElements = 1;
		}
		return fCanCopyElements == 2;
	}

	private void clear() {
		fElements = null;
		fMoveProcessor = null;
		fCanMoveElements = 0;
		fCopyProcessor = null;
		fCanCopyElements = 0;
	}
}