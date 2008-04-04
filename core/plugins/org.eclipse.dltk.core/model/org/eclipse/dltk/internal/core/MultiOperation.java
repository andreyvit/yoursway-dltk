/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.core;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelStatus;
import org.eclipse.dltk.core.IModelStatusConstants;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ModelException;


/**
 * This class is used to perform operations on multiple <code>IModelElement</code>.
 * It is responible for running each operation in turn, collecting
 * the errors and merging the corresponding <code>ModelElementDelta</code>s.
 * <p>
 * If several errors occured, they are collected in a multi-status
 * <code>ModelStatus</code>. Otherwise, a simple <code>ModelStatus</code>
 * is thrown.
 */
public abstract class MultiOperation extends ModelOperation {
	/**
	 * Table specifying insertion positions for elements being 
	 * copied/moved/renamed. Keyed by elements being processed, and
	 * values are the corresponding insertion point.
	 * @see #processElements()
	 */
	protected Map insertBeforeElements = new HashMap(1);
	/**
	 * Table specifying the new parent for elements being 
	 * copied/moved/renamed.
	 * Keyed by elements being processed, and
	 * values are the corresponding destination parent.
	 */
	protected Map newParents;
	/**
	 * This table presents the data in <code>fRenamingList</code> in a more
	 * convenient way.
	 */
	protected Map renamings;
	/**
	 * The list of renamings supplied to the operation
	 */
	protected String[] renamingsList = null;
	/**
	 * Creates a new <code>MultiOperation</code> on <code>elementsToProcess</code>.
	 */
	protected MultiOperation(IModelElement[] elementsToProcess, boolean force) {
		super(elementsToProcess, force);
	}
	/**
	 * Creates a new <code>MultiOperation</code>.
	 */
	protected MultiOperation(IModelElement[] elementsToProcess, IModelElement[] parentElements, boolean force) {
		super(elementsToProcess, parentElements, force);
		this.newParents = new HashMap(elementsToProcess.length);
		if (elementsToProcess.length == parentElements.length) {
			for (int i = 0; i < elementsToProcess.length; i++) {
				this.newParents.put(elementsToProcess[i], parentElements[i]);
			}
		} else { //same destination for all elements to be moved/copied/renamed
			for (int i = 0; i < elementsToProcess.length; i++) {
				this.newParents.put(elementsToProcess[i], parentElements[0]);
			}
		}
	
	}
	/**
	 * Convenience method to create a <code>ModelException</code>
	 * embending a <code>ModelStatus</code>.
	 */
	protected void error(int code, IModelElement element) throws ModelException {
		throw new ModelException(new ModelStatus(code, element));
	}
	/**
	 * Executes the operation.
	 *
	 * @exception ModelException if one or several errors occured during the operation.
	 * If multiple errors occured, the corresponding <code>ModelStatus</code> is a
	 * multi-status. Otherwise, it is a simple one.
	 */
	protected void executeOperation() throws ModelException {
		processElements();
	}
	/**
	 * Returns the parent of the element being copied/moved/renamed.
	 */
	protected IModelElement getDestinationParent(IModelElement child) {
		return (IModelElement)this.newParents.get(child);
	}
	/**
	 * Returns the name to be used by the progress monitor.
	 */
	protected abstract String getMainTaskName();
	/**
	 * Returns the new name for <code>element</code>, or <code>null</code>
	 * if there are no renamings specified.
	 */
	protected String getNewNameFor(IModelElement element) throws ModelException {
		String newName = null;
		if (this.renamings != null)
			newName = (String) this.renamings.get(element);
		if (newName == null && element instanceof IMethod && ((IMethod) element).isConstructor())
			newName = getDestinationParent(element).getElementName();
		return newName;
	}
	/**
	 * Sets up the renamings hashtable - keys are the elements and
	 * values are the new name.
	 */
	private void initializeRenamings() {
		if (this.renamingsList != null && this.renamingsList.length == this.elementsToProcess.length) {
			this.renamings = new HashMap(this.renamingsList.length);
			for (int i = 0; i < this.renamingsList.length; i++) {
				if (this.renamingsList[i] != null) {
					this.renamings.put(this.elementsToProcess[i], this.renamingsList[i]);
				}
			}
		}
	}
	/**
	 * Returns <code>true</code> if this operation represents a move or rename, <code>false</code>
	 * if this operation represents a copy.<br>
	 * Note: a rename is just a move within the same parent with a name change.
	 */
	protected boolean isMove() {
		return false;
	}
	/**
	 * Returns <code>true</code> if this operation represents a rename, <code>false</code>
	 * if this operation represents a copy or move.
	 */
	protected boolean isRename() {
		return false;
	}
	
	/**
	 * Subclasses must implement this method to process a given <code>IModelElement</code>.
	 */
	protected abstract void processElement(IModelElement element) throws ModelException;
	/**
	 * Processes all the <code>IModelElement</code>s in turn, collecting errors
	 * and updating the progress monitor.
	 *
	 * @exception ModelException if one or several operation(s) was unable to
	 * be completed.
	 */
	protected void processElements() throws ModelException {
		beginTask(getMainTaskName(), this.elementsToProcess.length);
		IModelStatus[] errors = new IModelStatus[3];
		int errorsCounter = 0;
		for (int i = 0; i < this.elementsToProcess.length; i++) {
			try {
				verify(this.elementsToProcess[i]);
				processElement(this.elementsToProcess[i]);
			} catch (ModelException jme) {
				if (errorsCounter == errors.length) {
					// resize
					System.arraycopy(errors, 0, (errors = new IModelStatus[errorsCounter*2]), 0, errorsCounter);
				}
				errors[errorsCounter++] = jme.getModelStatus();
			} finally {
				worked(1);
			}
		}
		done();
		if (errorsCounter == 1) {
			throw new ModelException(errors[0]);
		} else if (errorsCounter > 1) {
			if (errorsCounter != errors.length) {
				// resize
				System.arraycopy(errors, 0, (errors = new IModelStatus[errorsCounter]), 0, errorsCounter);
			}
			throw new ModelException(ModelStatus.newMultiStatus(errors));
		}
	}
	/**
	 * Sets the insertion position in the new container for the modified element. The element
	 * being modified will be inserted before the specified new sibling. The given sibling
	 * must be a child of the destination container specified for the modified element.
	 * The default is <code>null</code>, which indicates that the element is to be
	 * inserted at the end of the container.
	 */
	public void setInsertBefore(IModelElement modifiedElement, IModelElement newSibling) {
		this.insertBeforeElements.put(modifiedElement, newSibling);
	}
	/**
	 * Sets the new names to use for each element being copied. The renamings
	 * correspond to the elements being processed, and the number of
	 * renamings must match the number of elements being processed.
	 * A <code>null</code> entry in the list indicates that an element
	 * is not to be renamed.
	 *
	 * <p>Note that some renamings may not be used.  If both a parent
	 * and a child have been selected for copy/move, only the parent
	 * is changed.  Therefore, if a new name is specified for the child,
	 * the child's name will not be changed.
	 */
	public void setRenamings(String[] renamingsList) {
		this.renamingsList = renamingsList;
		initializeRenamings();
	}
	/**
	 * This method is called for each <code>IModelElement</code> before
	 * <code>processElement</code>. It should check that this <code>element</code>
	 * can be processed.
	 */
	protected abstract void verify(IModelElement element) throws ModelException;
	/**
	 * Verifies that the <code>destination</code> specified for the <code>element</code> is valid for the types of the
	 * <code>element</code> and <code>destination</code>.
	 */
	protected void verifyDestination(IModelElement element, IModelElement destination) throws ModelException {
		if (destination == null || !destination.exists())
			error(IModelStatusConstants.ELEMENT_DOES_NOT_EXIST, destination);
		
		int destType = destination.getElementType();
		switch (element.getElementType()) {
//			case IModelElement.PACKAGE_DECLARATION :
//			case IModelElement.IMPORT_DECLARATION :
//				if (destType != IModelElement.COMPILATION_UNIT)
//					error(IModelStatusConstants.INVALID_DESTINATION, element);
//				break;
			case IModelElement.TYPE :
				if (destType != IModelElement.SOURCE_MODULE && destType != IModelElement.TYPE)
					error(IModelStatusConstants.INVALID_DESTINATION, element);
				break;
			case IModelElement.METHOD :
			case IModelElement.FIELD :			
				if (destType != IModelElement.TYPE)
					error(IModelStatusConstants.INVALID_DESTINATION, element);
				break;
			case IModelElement.SOURCE_MODULE :
				if (destType != IModelElement.SCRIPT_FOLDER)
					error(IModelStatusConstants.INVALID_DESTINATION, element);
				else {
					if( element instanceof SourceModule ) {
						SourceModule cu = (SourceModule)element;
						if (isMove() && cu.isWorkingCopy() && !cu.isPrimary())
							error(IModelStatusConstants.INVALID_ELEMENT_TYPES, element);
					}
					else if( element instanceof ExternalSourceModule ) {
						ExternalSourceModule cu = (ExternalSourceModule)element;
						if (isMove() && cu.isWorkingCopy() && !cu.isPrimary())
							error(IModelStatusConstants.INVALID_ELEMENT_TYPES, element);
					}
				}
				break;
			case IModelElement.SCRIPT_FOLDER :
				IScriptFolder fragment = (IScriptFolder) element;
				IModelElement parent = fragment.getParent();
				if (parent.isReadOnly())
					error(IModelStatusConstants.READ_ONLY, element);
				else if (destType != IModelElement.PROJECT_FRAGMENT)
					error(IModelStatusConstants.INVALID_DESTINATION, element);
				break;
			default :
				error(IModelStatusConstants.INVALID_ELEMENT_TYPES, element);
		}
	}
	/**
	 * Verify that the new name specified for <code>element</code> is
	 * valid for that type of script element.
	 */
	protected void verifyRenaming(IModelElement element) throws ModelException {
		String newName = getNewNameFor(element);
		boolean isValid = true;
	
		switch (element.getElementType()) {
			case IModelElement.SCRIPT_FOLDER :
//				if (((IScriptFolder) element).isDefaultPackage()) {
//					// don't allow renaming of default package (see PR #1G47GUM)
//					throw new ModelException(new ModelStatus(IModelStatusConstants.NAME_COLLISION, element));
//				}
				if(DLTKCore.DEBUG) {
					System.err.println("Need validation of script folder name"); //$NON-NLS-1$
				}
				isValid = true;
				break;
			case IModelElement.SOURCE_MODULE :
				
				if(DLTKCore.DEBUG) {
					System.err.println("Need validation of source module name"); //$NON-NLS-1$
				}
				isValid = true;
				break;			
			default :
				
				if(DLTKCore.DEBUG) {
					System.err.println("Need validation of name"); //$NON-NLS-1$
				}				
				isValid = true;
				break;
		}
	
		if (!isValid) {
			throw new ModelException(new ModelStatus(IModelStatusConstants.INVALID_NAME, element, newName));
		}
	}
	/**
	 * Verifies that the positioning sibling specified for the <code>element</code> is exists and
	 * its parent is the destination container of this <code>element</code>.
	 */
	protected void verifySibling(IModelElement element, IModelElement destination) throws ModelException {
		IModelElement insertBeforeElement = (IModelElement) this.insertBeforeElements.get(element);
		if (insertBeforeElement != null) {
			if (!insertBeforeElement.exists() || !insertBeforeElement.getParent().equals(destination)) {
				error(IModelStatusConstants.INVALID_SIBLING, insertBeforeElement);
			}
		}
	}
}
