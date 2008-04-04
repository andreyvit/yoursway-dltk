/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.scriptview;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.corext.refactoring.reorg.ReorgMoveStarter;
import org.eclipse.dltk.internal.corext.refactoring.reorg.ReorgPolicyFactory;
import org.eclipse.dltk.internal.corext.refactoring.reorg.ReorgUtils;
import org.eclipse.dltk.internal.corext.refactoring.reorg.IReorgPolicy.ICopyPolicy;
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
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.views.navigator.LocalSelectionTransfer;

public class SelectionTransferDropAdapter extends DLTKViewerDropAdapter
		implements TransferDropTargetListener {

	private List fElements;
	private ScriptMoveProcessor fMoveProcessor;
	private int fCanMoveElements;
	private ScriptCopyProcessor fCopyProcessor;
	private int fCanCopyElements;
	private ISelection fSelection;

	private static final long DROP_TIME_DIFF_TRESHOLD = 150;

	public SelectionTransferDropAdapter(StructuredViewer viewer) {
		super(viewer);

		setScrollEnabled(true);
		setExpandEnabled(true);
		setSelectionFeedbackEnabled(false);
		setFeedbackEnabled(false);
	}

	// ---- TransferDropTargetListener interface
	// ---------------------------------------

	public Transfer getTransfer() {
		return LocalSelectionTransfer.getInstance();
	}

	public boolean isEnabled(DropTargetEvent event) {
		Object target = event.item != null ? event.item.getData() : null;
		if (target == null)
			return false;
		return target instanceof IModelElement || target instanceof IResource;
	}

	// ---- Actual DND
	// -----------------------------------------------------------------

	public void dragEnter(DropTargetEvent event) {
		clear();
		super.dragEnter(event);
	}

	public void dragLeave(DropTargetEvent event) {
		clear();
		super.dragLeave(event);
	}

	private void clear() {
		setSelectionFeedbackEnabled(false);
		fElements = null;
		fSelection = null;
		fMoveProcessor = null;
		fCanMoveElements = 0;
		fCopyProcessor = null;
		fCanCopyElements = 0;
	}

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
		int result = internalDetermineOperation(target, operation, operations);

		if (result == DND.DROP_NONE) {
			setSelectionFeedbackEnabled(false);
		} else {
			setSelectionFeedbackEnabled(true);
		}

		return result;
	}

	private int internalDetermineOperation(Object target, int operation,
			int operations) {

		initializeSelection();

		if (target == null)
			return DND.DROP_NONE;

		// Do not allow to drop on itself, bug 14228
		if (getCurrentLocation() == LOCATION_ON) {
			IModelElement[] javaElements = ReorgUtils
					.getModelElements(fElements);
			if (contains(javaElements, target))
				return DND.DROP_NONE;

			IResource[] resources = ReorgUtils.getResources(fElements);
			if (contains(resources, target))
				return DND.DROP_NONE;
		}

		try {
			switch (operation) {
			case DND.DROP_DEFAULT:
				return handleValidateDefault(target, operations);
			case DND.DROP_COPY:
				return handleValidateCopy(target);
			case DND.DROP_MOVE:
				return handleValidateMove(target);
			}
		} catch (ModelException e) {
			ExceptionHandler.handle(e,
					ScriptMessages.SelectionTransferDropAdapter_error_title,
					ScriptMessages.SelectionTransferDropAdapter_error_message);
		}

		return DND.DROP_NONE;
	}

	private boolean contains(IResource[] resources, Object target) {
		for (int i = 0; i < resources.length; i++) {
			if (resources[i].equals(target))
				return true;
		}

		return false;
	}

	private boolean contains(IModelElement[] elements, Object target) {
		for (int i = 0; i < elements.length; i++) {
			if (elements[i].equals(target))
				return true;
		}

		return false;
	}

	protected void initializeSelection() {
		if (fElements != null)
			return;
		ISelection s = LocalSelectionTransfer.getInstance().getSelection();
		if (!(s instanceof IStructuredSelection)) {
			fSelection = StructuredSelection.EMPTY;
			fElements = Collections.EMPTY_LIST;
			return;
		}
		fSelection = s;
		fElements = ((IStructuredSelection) s).toList();
	}

	protected ISelection getSelection() {
		return fSelection;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean performDrop(Object data) {
		try {
			switch (getCurrentOperation()) {
			case DND.DROP_MOVE:
				handleDropMove(getCurrentTarget());
				break;
			case DND.DROP_COPY:
				handleDropCopy(getCurrentTarget());
				break;
			}
		} catch (ModelException e) {
			ExceptionHandler.handle(e,
					ScriptMessages.SelectionTransferDropAdapter_error_title,
					ScriptMessages.SelectionTransferDropAdapter_error_message);
		} catch (InvocationTargetException e) {
			ExceptionHandler
					.handle(
							e,
							RefactoringMessages.OpenRefactoringWizardAction_refactoring,
							RefactoringMessages.OpenRefactoringWizardAction_exception);
		} catch (InterruptedException e) {
			// ok
		}
		// The drag source listener must not perform any operation
		// since this drop adapter did the remove of the source even
		// if we moved something.
		return false;

	}

	private int handleValidateDefault(Object target, int operations)
			throws ModelException {
		if ((operations & DND.DROP_MOVE) != 0) {
			int result = handleValidateMove(target);
			if (result != DND.DROP_NONE)
				return result;
		}

		return handleValidateCopy(target);
	}

	private int handleValidateMove(Object target) throws ModelException {
		if (fMoveProcessor == null) {
			IMovePolicy policy = ReorgPolicyFactory.createMovePolicy(ReorgUtils
					.getResources(fElements), ReorgUtils
					.getModelElements(fElements));
			if (policy.canEnable())
				fMoveProcessor = new ScriptMoveProcessor(policy);
		}

		if (!canMoveElements())
			return DND.DROP_NONE;

		if (fMoveProcessor == null)
			return DND.DROP_NONE;

		// if (!fMoveProcessor.setDestination(
		// ReorgDestinationFactory.createDestination(target,
		// getCurrentLocation())).isOK())
		// return DND.DROP_NONE;
		if (target instanceof IModelElement) {
			if (!fMoveProcessor.setDestination((IModelElement) target).isOK()) {
				return DND.DROP_NONE;
			}
		}
		if (target instanceof IResource) {
			if (!fMoveProcessor.setDestination((IResource) target).isOK()) {
				return DND.DROP_NONE;
			}
		}

		return DND.DROP_MOVE;
	}

	private boolean canMoveElements() {
		if (fCanMoveElements == 0) {
			fCanMoveElements = 2;
			if (fMoveProcessor == null)
				fCanMoveElements = 1;
		}
		return fCanMoveElements == 2;
	}

	private void handleDropMove(final Object target) throws ModelException,
			InvocationTargetException, InterruptedException {
		IModelElement[] modelElements = ReorgUtils.getModelElements(fElements);
		IResource[] resources = ReorgUtils.getResources(fElements);
		ReorgMoveStarter starter = null;
		if (target instanceof IResource)
			starter = ReorgMoveStarter.create(modelElements, resources,
					(IResource) target);
		else if (target instanceof IModelElement)
			starter = ReorgMoveStarter.create(modelElements, resources,
					(IModelElement) target);
		if (starter != null)
			starter.run(getShell());
	}

	private int handleValidateCopy(Object target) throws ModelException {

		if (fCopyProcessor == null) {
			final ICopyPolicy policy = ReorgPolicyFactory.createCopyPolicy(
					ReorgUtils.getResources(fElements), ReorgUtils
							.getModelElements(fElements));
			fCopyProcessor = policy.canEnable() ? new ScriptCopyProcessor(
					policy) : null;
		}

		if (!canCopyElements())
			return DND.DROP_NONE;

		if (fCopyProcessor == null)
			return DND.DROP_NONE;
		if (target instanceof IModelElement) {
			if (!fCopyProcessor.setDestination((IModelElement) target).isOK()) {
				return DND.DROP_NONE;
			}
		}
		if (target instanceof IResource) {
			if (!fCopyProcessor.setDestination((IResource) target).isOK()) {
				return DND.DROP_NONE;
			}
		}
		// if (!fCopyProcessor.setDestination(
		// ReorgDestinationFactory.createDestination(target,
		// getCurrentLocation()).getDestination()).isOK())
		// return DND.DROP_NONE;

		return DND.DROP_COPY;
	}

	private boolean canCopyElements() {
		if (fCanCopyElements == 0) {
			fCanCopyElements = 2;
			if (fCopyProcessor == null)
				fCanCopyElements = 1;
		}
		return fCanCopyElements == 2;
	}

	private void handleDropCopy(final Object target) throws ModelException,
			InvocationTargetException, InterruptedException {
		IModelElement[] modelElements = ReorgUtils.getModelElements(fElements);
		IResource[] resources = ReorgUtils.getResources(fElements);
		ReorgCopyStarter starter = ReorgCopyStarter.create(modelElements,
				resources, (IModelElement) target);

		if (starter != null)
			starter.run(getShell());
	}

	private Shell getShell() {
		return getViewer().getControl().getShell();
	}

	/**
	 * {@inheritDoc}
	 */
	protected int getCurrentLocation() {
		if (getFeedbackEnabled()) {
			return super.getCurrentLocation();
		} else {
			return LOCATION_ON;
		}
	}
}
