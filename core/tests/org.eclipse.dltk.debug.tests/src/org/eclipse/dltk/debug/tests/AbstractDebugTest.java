/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.debug.tests;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IBreakpointManager;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.util.SafeRunnable;

/**
 * Tests for launch configurations
 */
public abstract class AbstractDebugTest extends TestCase {
	
	public static final int DEFAULT_TIMEOUT = 30000;
	
	public static IDLTKProject fScriptProject;
	
	/**
	 * Returns the launch manager
	 * 
	 * @return launch manager
	 */
	protected ILaunchManager getLaunchManager() {
		return DebugPlugin.getDefault().getLaunchManager();
	}
	
	/**
	 * Returns the breakpoint manager
	 * 
	 * @return breakpoint manager
	 */
	protected IBreakpointManager getBreakpointManager() {
		return DebugPlugin.getDefault().getBreakpointManager();
	}
	
	public AbstractDebugTest(String name) {
		super(name);
		// set error dialog to non-blocking to avoid hanging the UI during test
		ErrorDialog.AUTOMATED_MODE = true;
		SafeRunnable.setIgnoreErrors(true);
	}
	
	/**
	 * Returns the 'DebugTests' project.
	 * 
	 * @return the test project
	 */
	protected IDLTKProject getDLTKProject() {
		return getDLTKProject("DebugTests");
	}
	
	/**
	 * Returns the Script project with the given name.
	 * 
	 * @param name project name
	 * @return the Script project with the given name
	 */
	protected IDLTKProject getDLTKProject(String name) {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(name);
		return DLTKCore.create(project);
	}
}

