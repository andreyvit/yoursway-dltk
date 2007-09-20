/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.testing.ui;

import org.eclipse.ui.PlatformUI;

import org.eclipse.jface.action.Action;

/**
 * Requests to rerun a test.
 */
public class RerunAction extends Action {
	private String fTestId;
	private String fClassName;
	private String fTestName;
	private TestRunnerViewPart fTestRunner;
	private String fLaunchMode;
	
	/**
	 * Constructor for RerunAction.
	 */
	public RerunAction(String actionName, TestRunnerViewPart runner, String testId, String className, String testName,
			String launchMode) {
		super(actionName); 
		PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IDLTKTestingHelpContextIds.RERUN_ACTION);
		fTestRunner= runner;
		fTestId= testId;
		fClassName= className;
		fTestName= testName;
		fLaunchMode= launchMode;
	}

	/*
	 * @see IAction#run()
	 */
	public void run() {
		fTestRunner.rerunTest(fTestId, fClassName, fTestName, fLaunchMode);
	}
}
