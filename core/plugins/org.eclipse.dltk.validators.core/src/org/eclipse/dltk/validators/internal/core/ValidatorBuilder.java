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

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceManipulation;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.builder.IScriptBuilder;
import org.eclipse.dltk.internal.core.BuiltinProjectFragment;
import org.eclipse.dltk.internal.core.ExternalProjectFragment;
import org.eclipse.dltk.validators.core.ValidatorRuntime;

public class ValidatorBuilder implements IScriptBuilder {

	public IStatus[] buildModelElements(IScriptProject project, List elements,
			IProgressMonitor monitor, int buildType) {
		ValidatorRuntime.executeActiveValidators(null, elements, null, monitor);
		return null;
	}

	public IStatus[] buildResources(IScriptProject project, List resources,
			IProgressMonitor monitor, int buildType) {
		ValidatorRuntime
				.executeActiveValidators(null, null, resources, monitor);
		return null;
	}

	public List getDependencies(IScriptProject project, List resources) {
		// We don't provide dependencies here.
		return null;
	}

	public int estimateElementsToBuild(List elements) {
		if (ValidatorRuntime.getActiveValidators().length == 0)
			return 0;
		int estimation = 0;
		for (int i = 0; i < elements.size(); i++) {
			IModelElement element = (IModelElement) elements.get(i);
			if (element.getElementType() == IModelElement.SOURCE_MODULE) {
				IProjectFragment projectFragment = (IProjectFragment) element
						.getAncestor(IModelElement.PROJECT_FRAGMENT);
				if (!projectFragment.isExternal())
					estimation++;
			}
		}
		return estimation;
	}
}
