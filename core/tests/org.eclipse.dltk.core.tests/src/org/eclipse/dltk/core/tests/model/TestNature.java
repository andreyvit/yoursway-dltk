/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.core.tests.model;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;

public class TestNature implements IProjectNature {

	private IProject project;

	public void configure() throws CoreException {

	}

	public void deconfigure() throws CoreException {

	}

	public IProject getProject() {
		return project;
	}

	public void setProject(IProject value) {
		project = value;
	}

}
