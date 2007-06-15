/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.javascript.core.builders;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.builder.IScriptBuilder;

public class JavaScriptBuilder implements IScriptBuilder ,IExecutableExtension{

	public IStatus[] buildModelElements(IScriptProject project, List elements,
			IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		return null;
	}

	public IStatus[] buildResources(IScriptProject project, List resources,
			IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		return null;
	}

	public List getDependencies(IScriptProject project, List resources) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) throws CoreException {		
	}

}
