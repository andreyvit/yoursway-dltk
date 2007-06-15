/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.validators.internal.core;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.builder.IScriptBuilder;
import org.eclipse.dltk.validators.core.ValidatorRuntime;

public class ValidatorBuilder implements IScriptBuilder {

	public IStatus[] buildModelElements(IScriptProject project, List elements,
			IProgressMonitor monitor) {
		ValidatorRuntime.executeActiveValidatorsWithConsole(null, elements, null);
		return null;
	}

	public IStatus[] buildResources(IScriptProject project, List resources,
			IProgressMonitor monitor) {
		ValidatorRuntime.executeActiveValidatorsWithConsole(null, null, resources);
		return null;
	}

	public List getDependencies(IScriptProject project, List resources) {
		//We don't provide dependencies here.
		return null;
	}

}
