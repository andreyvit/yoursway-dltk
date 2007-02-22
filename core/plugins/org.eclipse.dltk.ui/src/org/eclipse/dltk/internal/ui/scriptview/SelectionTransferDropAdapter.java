/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.scriptview;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.corext.refactoring.reorg.ReorgMoveStarter;
import org.eclipse.dltk.internal.corext.refactoring.reorg.ReorgPolicyFactory;
import org.eclipse.dltk.internal.corext.refactoring.reorg.ReorgUtils;
import org.eclipse.dltk.internal.corext.refactoring.reorg.IReorgPolicy.IMovePolicy;
import org.eclipse.dltk.internal.ui.dnd.DLTKViewerDropAdapter;
import org.eclipse.dltk.internal.ui.refactoring.RefactoringMessages;
import org.eclipse.dltk.internal.ui.refactoring.reorg.ReorgCopyStarter;
import org.eclipse.dltk.internal.ui.refactoring.reorg.ScriptCopyProcessor;
import org.eclipse.dltk.internal.ui.refactoring.reorg.ScriptMoveProcessor;
import org.eclipse.dltk.ui.util.ExceptionHandler;
import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.views.navigator.LocalSelectionTransfer;


public class SelectionTransferDropAdapter extends DLTKViewerDropAdapter implements TransferDropTargetListener {

	private List fElements;
	private ScriptMoveProcessor fMoveProcessor;
	private int fCanMoveElements;
	private ScriptCopyProcessor fCopyProcessor;
	private int fCanCopyElements;
	private ISelection fSelection;

	private static final long DROP_TIME_DIFF_TRESHOLD= 150;

	public SelectionTransferDropAdapter(StructuredViewer viewer) {
		super(viewer, DND.FEEDBACK_SCROLL | DND.FEEDBACK_EXPAND);
	}

	//---- TransferDropTargetListener interface ---------------------------------------
	
	public Transfer getTransfer() {
		return LocalSelectionTransfer.getInstance();
	}
	
	public boolean isEnabled(DropTargetEvent event) {
		Object target= event.item != null ? event.item.getData() : null;
		if (target == null)
			return false;
		return target instanceof IModelElement || target instanceof IResource;
	}

	//---- Actual DND -----------------------------------------------------------------
	
	public void dragEnter(DropTargetEvent event) {
		clear();
		super.dragEnter(event);
	}
	
	public void dragLeave(DropTargetEvent event) {
		clear();
		super.dragLeave(event);
	}
	
	private void clear() {
		fElements= null;
		fSelection= null;
		fMoveProcessor= null;
		fCanMoveElements= 0;
		fCopyProcessor= null;
		fCanCopyElements= 0;
	}
	
	public void validateDrop(Object target, DropTargetEvent event, int operation) {
		event.detail= DND.DROP_NONE;
		
		if (tooFast(event)) 
			return;
		
		initializeSelection();
				
		try {
			switch(operation) {
				case DND.DROP_DEFAULT:	event.detail= handleValidateDefault(target, event); break;
				case DND.DROP_COPY: 	event.detail= handleValidateCopy(target, event); break;
				case DND.DROP_MOVE: 	event.detail= handleValidateMove(target, event); break;
			}
		} catch (ModelException e){
			ExceptionHandler.handle(e, ScriptMessages.SelectionTransferDropAdapter_error_title, ScriptMessages.SelectionTransferDropAdapter_error_message); 
			event.detail= DND.DROP_NONE;
		}	
	}

	protected void initializeSelection(){
		if (fElements != null)
			return;
		ISelection s= LocalSelectionTransfer.getInstance().getSelection();
		if (!(s instanceof IStructuredSelection))
			return;
		fSelection= s;	
		fElements= ((IStructuredSelection)s).toList();
	}
	
	protected ISelection getSelection(){
		return fSelection;
	}
	
	private boolean tooFast(DropTargetEvent event) {
		return Math.abs(LocalSelectionTransfer.getInstance().getSelectionSetTime() - (event.time & 0xFFFFFFFFL)) < DROP_TIME_DIFF_TRESHOLD;
	}	

	public void drop(Object target, DropTargetEvent event) {
		try{
			switch(event.detail) {
				case DND.DROP_MOVE: handleDropMove(target, event); break;
				case DND.DROP_COPY: handleDropCopy(target, event); break;
			}
		} catch (ModelException e){
			ExceptionHandler.handle(e, ScriptMessages.SelectionTransferDropAdapter_error_title, ScriptMessages.SelectionTransferDropAdapter_error_message); 
		} catch(InvocationTargetException e) {
			ExceptionHandler.handle(e, RefactoringMessages.OpenRefactoringWizardAction_refactoring, RefactoringMessages.OpenRefactoringWizardAction_exception); 
		} catch (InterruptedException e) {
			//ok
		} finally {
			// The drag source listener must not perform any operation
			// since this drop adapter did the remove of the source even
			// if we moved something.
			event.detail= DND.DROP_NONE;
		}
	}
	
	private int handleValidateDefault(Object target, DropTargetEvent event) throws ModelException{
		if (target == null)
			return DND.DROP_NONE;
		
		if ((event.operations & DND.DROP_MOVE) != 0) {
			return handleValidateMove(target, event);
		}
		if ((event.operations & DND.DROP_COPY) != 0) {
			return handleValidateCopy(target, event);
		}
		return DND.DROP_NONE;
	}
	
	private int handleValidateMove(Object target, DropTargetEvent event) throws ModelException{
		if (target == null)
			return DND.DROP_NONE;
		
		if (fMoveProcessor == null) {
			IMovePolicy policy= ReorgPolicyFactory.createMovePolicy(ReorgUtils.getResources(fElements), ReorgUtils.getModelElements(fElements));
			if (policy.canEnable())
				fMoveProcessor= new ScriptMoveProcessor(policy);
		}

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
			fCanMoveElements= 2;
			if (fMoveProcessor == null)
				fCanMoveElements= 1;
		}
		return fCanMoveElements == 2;
	}

	private void handleDropMove(final Object target, DropTargetEvent event) throws ModelException, InvocationTargetException, InterruptedException{
		IModelElement[] modelElements= ReorgUtils.getModelElements(fElements);
		IResource[] resources= ReorgUtils.getResources(fElements);
		ReorgMoveStarter starter= null;
		if (target instanceof IResource) 
			starter= ReorgMoveStarter.create(modelElements, resources, (IResource)target);
		else if (target instanceof IModelElement)
			starter= ReorgMoveStarter.create(modelElements, resources, (IModelElement)target);
		if (starter != null)
			starter.run(getShell());
	}

	private int handleValidateCopy(Object target, DropTargetEvent event) throws ModelException{

		if (fCopyProcessor == null)
			fCopyProcessor= ScriptCopyProcessor.create(ReorgUtils.getResources(fElements), ReorgUtils.getModelElements(fElements));

		if (!canCopyElements())
			return DND.DROP_NONE;	

		if (target instanceof IResource && fCopyProcessor != null && fCopyProcessor.setDestination((IResource)target).isOK())
			return DND.DROP_COPY;
		else if (target instanceof IModelElement && fCopyProcessor != null && fCopyProcessor.setDestination((IModelElement)target).isOK())
			return DND.DROP_COPY;
		else
			return DND.DROP_NONE;					
	}
			
	private boolean canCopyElements() {
		if (fCanCopyElements == 0) {
			fCanCopyElements= 2;
			if (fCopyProcessor == null)
				fCanCopyElements= 1;
		}
		return fCanCopyElements == 2;
	}		
	
	private void handleDropCopy(final Object target, DropTargetEvent event) throws ModelException, InvocationTargetException, InterruptedException{
		IModelElement[] modelElements= ReorgUtils.getModelElements(fElements);
		IResource[] resources= ReorgUtils.getResources(fElements);
		ReorgCopyStarter starter= null;
		if (target instanceof IResource) 
			starter= ReorgCopyStarter.create(modelElements, resources, (IResource)target);
		else if (target instanceof IModelElement)
			starter= ReorgCopyStarter.create(modelElements, resources, (IModelElement)target);
		if (starter != null)
			starter.run(getShell());
	}

	private Shell getShell() {
		return getViewer().getControl().getShell();
	}
}
