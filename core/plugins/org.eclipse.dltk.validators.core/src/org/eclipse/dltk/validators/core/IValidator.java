/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.validators.core;

import java.io.OutputStream;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.dltk.core.ISourceModule;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Contains validator properties.
 * @author Haiodo
 *
 */
public interface IValidator {
	String getID();
	String getName();
	void setName(String name);
	IValidatorType getValidatorType();
	boolean isValidatorValid();
	
	//Per-resouce operations
	// If console is non null then output to console are possible.
	IStatus validate(ISourceModule[] modules, OutputStream console );
	IStatus validate(IResource[] resources, OutputStream console );
	
	// Used to store information into
	void storeTo(Document doc, Element element);
	
	boolean isActive();
	void setActive(boolean active);
	
	/**
	 * Remove all reported markers.
	 * @param module
	 */
	void clean(ISourceModule[] module);
	void clean(IResource[] resource);
	
	public void setProgressMonitor(IProgressMonitor monitor);
}
