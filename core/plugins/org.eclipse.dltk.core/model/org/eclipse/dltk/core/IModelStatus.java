/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.core;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.dltk.internal.core.ModelStatus;


public interface IModelStatus extends IStatus {
	
	/**
	 * Singleton OK object
	 */
	public static final IModelStatus VERIFIED_OK = new ModelStatus(OK, OK, "OK");
	/**
	 * Returns whether this status indicates that a script model element does not exist.
	 * This convenience method is equivalent to
	 * <code>getCode() == IModelStatusConstants.ELEMENT_DOES_NOT_EXIST</code>.
	 *
	 * @return <code>true</code> if the status code indicates that a script model
	 *   element does not exist
	 * @see IModelStatusConstants#ELEMENT_DOES_NOT_EXIST
	 */
	boolean isDoesNotExist();
	/**
	 * Returns the path associated with the failure (see specification
	 * of the status code), or <code>null</code> if the failure is not 
	 * one of <code>DEVICE_PATH</code>, <code>INVALID_PATH</code>, 
	 * <code>PATH_OUTSIDE_PROJECT</code>, or <code>RELATIVE_PATH</code>.
	 *
	 * @return the path that caused the failure, or <code>null</code> if none
	 * @see IModelStatusConstants#DEVICE_PATH
	 * @see IModelStatusConstants#INVALID_PATH
	 * @see IModelStatusConstants#PATH_OUTSIDE_PROJECT
	 * @see IModelStatusConstants#RELATIVE_PATH
	 */
	IPath getPath();
}
