/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.core;

import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelStatus;
import org.eclipse.dltk.core.IModelStatusConstants;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.core.util.Messages;


/**
 * This operation copies/moves a collection of elements from their current
 * container to a new container, optionally renaming the elements.
 * <p>
 * Notes:
 * <ul>
 * <li>If there is already an element with the same name in the new container,
 * the operation either overwrites or aborts, depending on the collision policy
 * setting. The default setting is abort.
 * 
 * <li>When constructors are copied to a type, the constructors are
 * automatically renamed to the name of the destination type.
 * 
 * <li>When main types are renamed (move within the same parent), the
 * compilation unit and constructors are automatically renamed
 * 
 * <li>The collection of elements being copied must all share the same type of
 * container (for example, must all be type members).
 * 
 * <li>The elements are inserted in the new container in the order given.
 * 
 * <li>The elements can be positioned in the new container - see
 * #setInsertBefore. By default, the elements are inserted based on the default
 * positions as specified in the creation operation for that element type.
 * 
 * <li>This operation can be used to copy and rename elements within the same
 * container.
 * 
 * <li>This operation only copies elements contained within compilation units.
 * </ul>
 * 
 */
public class CopyElementsOperation extends MultiOperation {
	/**
	 * When executed, this operation will copy the given elements to the given
	 * containers. The elements and destination containers must be in the
	 * correct order. If there is > 1 destination, the number of destinations
	 * must be the same as the number of elements being copied/moved/renamed.
	 */
	public CopyElementsOperation(IModelElement[] elementsToCopy, IModelElement[] destContainers, boolean force) {
		super(elementsToCopy, destContainers, force);
	}

	/**
	 * When executed, this operation will copy the given elements to the given
	 * container.
	 */
	public CopyElementsOperation(IModelElement[] elementsToCopy, IModelElement destContainer, boolean force) {
		this(elementsToCopy, new IModelElement[] {
			destContainer
		}, force);
	}

	/**
	 * Returns the <code>String</code> to use as the main task name for
	 * progress monitoring.
	 */
	protected String getMainTaskName() {
		return Messages.operation_copyElementProgress;
	}

	/**
	 * Returns the nested operation to use for processing this element
	 */
	protected ModelOperation getNestedOperation(IModelElement element) {
		return null;
	}

	/**
	 * Returns <code>true</code> if this element is the main type of its
	 * compilation unit.
	 */
	protected boolean isRenamingMainType(IModelElement element, IModelElement dest) throws ModelException {
		if ((isRename() || getNewNameFor(element) != null) && dest.getElementType() == IModelElement.SOURCE_MODULE) {
			String typeName = dest.getElementName();
			if (DLTKCore.DEBUG) {
				System.err.println("TODO:Add extension remove code here...");
			}
			// typeName =
			// /*org.eclipse.dltk.internal.core.util.Util.getNameWithoutScriptLikeExtension((*/typeName;//);
			return element.getElementName().equals(typeName) && element.getParent().equals(dest);
		}
		return false;
	}

	/**
	 * Copy/move the element from the source to destination, renaming the
	 * elements as specified, honoring the collision policy.
	 * 
	 * @exception ScriptModelException
	 *                if the operation is unable to be completed
	 */
	protected void processElement(IModelElement element) throws ModelException {
		ModelOperation op = getNestedOperation(element);
//		boolean createElementInCUOperation = op instanceof CreateElementInCUOperation;
		if (op == null) {
			return;
		}
		if (DLTKCore.DEBUG) {
			System.err.println("TODO: Add CreateElementInCUOperation");
		}
//		if (createElementInCUOperation) {
//			IModelElement sibling = (IModelElement) this.insertBeforeElements.get(element);
//			if (sibling != null) {
//				((CreateElementInCUOperation) op).setRelativePosition(sibling, CreateElementInCUOperation.INSERT_BEFORE);
//			} else if (isRename()) {
//				IModelElement anchor = resolveRenameAnchor(element);
//				if (anchor != null) {
//					((CreateElementInCUOperation) op).setRelativePosition(anchor, CreateElementInCUOperation.INSERT_AFTER); // insert
//					// after
//					// so
//					// that
//					// the
//					// anchor
//					// is
//					// found
//					// before
//					// when
//					// deleted
//					// below
//				}
//			}
//			String newName = getNewNameFor(element);
//			if (newName != null) {
//				((CreateElementInCUOperation) op).setAlteredName(newName);
//			}
//		}
		executeNestedOperation(op, 1);
		ModelElement destination = (ModelElement) getDestinationParent(element);
		ISourceModule unit = destination.getSourceModule();
		if (!unit.isWorkingCopy()) {
			unit.close();
		}
//		if (createElementInCUOperation && isMove() && !isRenamingMainType(element, destination)) {
//			DeleteElementsOperation deleteOp = new DeleteElementsOperation(new IModelElement[] {
//				element
//			}, this.force);
//			executeNestedOperation(deleteOp, 1);
//		}
	}

	/**
	 * Returns the anchor used for positioning in the destination for the
	 * element being renamed. For renaming, if no anchor has explicitly been
	 * provided, the element is anchored in the same position.
	 */
//	private IModelElement resolveRenameAnchor(IModelElement element) throws ModelException {
//		IParent parent = (IParent) element.getParent();
//		IModelElement[] children = parent.getChildren();
//		for (int i = 0; i < children.length; i++) {
//			IModelElement child = children[i];
//			if (child.equals(element)) {
//				return child;
//			}
//		}
//		return null;
//	}

	/**
	 * Possible failures:
	 * <ul>
	 * <li>NO_ELEMENTS_TO_PROCESS - no elements supplied to the operation
	 * <li>INDEX_OUT_OF_BOUNDS - the number of renamings supplied to the
	 * operation does not match the number of elements that were supplied.
	 * </ul>
	 */
	protected IModelStatus verify() {
		IModelStatus status = super.verify();
		if (!status.isOK()) {
			return status;
		}
		if (this.renamingsList != null && this.renamingsList.length != this.elementsToProcess.length) {
			return new ModelStatus(IModelStatusConstants.INDEX_OUT_OF_BOUNDS);
		}
		return ModelStatus.VERIFIED_OK;
	}

	/**
	 * @see MultiOperation
	 * 
	 * Possible failure codes:
	 * <ul>
	 * 
	 * <li>ELEMENT_DOES_NOT_EXIST - <code>element</code> or its specified
	 * destination is is <code>null</code> or does not exist. If a
	 * <code>null</code> element is supplied, no element is provided in the
	 * status, otherwise, the non-existant element is supplied in the status.
	 * <li>INVALID_ELEMENT_TYPES - <code>element</code> is not contained
	 * within a compilation unit. This operation only operates on elements
	 * contained within compilation units.
	 * <li>READ_ONLY - <code>element</code> is read only.
	 * <li>INVALID_DESTINATION - The destination parent specified for
	 * <code>element</code> is of an incompatible type. The destination for a
	 * package declaration or import declaration must be a compilation unit; the
	 * destination for a type must be a type or compilation unit; the destinaion
	 * for any type member (other than a type) must be a type. When this error
	 * occurs, the element provided in the operation status is the
	 * <code>element</code>.
	 * <li>INVALID_NAME - the new name for <code>element</code> does not have
	 * valid syntax. In this case the element and name are provided in the
	 * status.
	 * 
	 * </ul>
	 */
	protected void verify(IModelElement element) throws ModelException {
		if (element == null || !element.exists())
			error(IModelStatusConstants.ELEMENT_DOES_NOT_EXIST, element);
		if (element.getElementType() < IModelElement.TYPE)
			error(IModelStatusConstants.INVALID_ELEMENT_TYPES, element);
		if (element.isReadOnly())
			error(IModelStatusConstants.READ_ONLY, element);
		IModelElement dest = getDestinationParent(element);
		verifyDestination(element, dest);
		verifySibling(element, dest);
		if (this.renamingsList != null) {
			verifyRenaming(element);
		}
	}
}
