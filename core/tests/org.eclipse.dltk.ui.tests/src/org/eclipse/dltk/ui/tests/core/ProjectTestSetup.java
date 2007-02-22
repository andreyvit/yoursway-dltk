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

package org.eclipse.dltk.ui.tests.core;

import junit.extensions.TestSetup;
import junit.framework.Test;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.ui.tests.DLTKProjectHelper;
import org.eclipse.dltk.ui.tests.TestOptions;


public class ProjectTestSetup extends TestSetup {

	public static final String PROJECT_NAME= "TestSetupProject";
	
	public static IDLTKProject getProject() {
		IProject project= ResourcesPlugin.getWorkspace().getRoot().getProject(PROJECT_NAME);
		return  DLTKCore.create(project);
	}
		
	private IDLTKProject fJProject;

	private boolean fAutobuilding;
	
	public ProjectTestSetup(Test test) {
		super(test);
	}
	
	/* (non-Javadoc)
	 * @see junit.extensions.TestSetup#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		
		IDLTKProject project= getProject();
		if (project.exists()) { // allow nesting of ProjectTestSetups
			return;
		}
		
		fAutobuilding = DLTKProjectHelper.setAutoBuilding(false);
		
		fJProject= DLTKProjectHelper.createDLTKProject(PROJECT_NAME);		
		
		TestOptions.initializeProjectOptions(fJProject);
		
		DLTKCore.setOptions(TestOptions.getDefaultOptions());
		TestOptions.initializeCodeGenerationOptions();
		//DLTKUIPlugin.getDefault().getCodeTemplateStore().load();		
	}

	/* (non-Javadoc)
	 * 
	 */
	protected void tearDown() throws Exception {
		if (fJProject != null) {
			DLTKProjectHelper.delete(fJProject);
			DLTKProjectHelper.setAutoBuilding(fAutobuilding);
		}
	}

}
