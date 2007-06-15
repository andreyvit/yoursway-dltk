/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.core;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ModelException;


public class ExternalScriptProject extends ScriptProject {
	
	/*
	 * Note this name can be surfaced in the UI (see https://bugs.eclipse.org/bugs/show_bug.cgi?id=128258)
	 */
	public static final String EXTERNAL_PROJECT_NAME = " "; //$NON-NLS-1$

	public ExternalScriptProject(IBuildpathEntry[] rawBuildpath) {
		super(ResourcesPlugin.getWorkspace().getRoot().getProject(EXTERNAL_PROJECT_NAME), ModelManager.getModelManager().getModel());
		try {
			getPerProjectInfo().rawBuildpath = rawBuildpath;
		} catch (ModelException e) {
			// getPerProjectInfo() never throws ModelException for an ExternalDLTKProject
		}
	}
	
	public boolean equals(Object o) {
		return this == o;
	}

	public boolean exists() {
		// external project never exists
		return false;
	}
	
	public String getOption(String optionName, boolean inheritDLTKCoreOptions) {
		if(DLTKCore.DEBUG) {
			System.err.println("ExternalScriptProject getOption should be corrected...");
		}
//		if (DLTKCore.COMPILER_PB_FORBIDDEN_REFERENCE.equals(optionName)
//				|| DLTKCore.COMPILER_PB_DISCOURAGED_REFERENCE.equals(optionName))
//			return DLTKCore.IGNORE;
		return super.getOption(optionName, inheritDLTKCoreOptions);
	}
	
	public boolean isOnBuildpath(IModelElement element) {
		// since project is external, no element is on buildpath (see https://bugs.eclipse.org/bugs/show_bug.cgi?id=61013#c16)
		return false;
	}

	public boolean isOnBuildpath(IResource resource) {
		// since project is external, no resource is on buildpath (see https://bugs.eclipse.org/bugs/show_bug.cgi?id=61013#c16)
		return false;
	}

}
