/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.refactoring.reorg;

import org.eclipse.dltk.internal.ui.refactoring.RefactoringMessages;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.ltk.core.refactoring.Refactoring;


public class RenameScriptProjectWizard extends RenameRefactoringWizard {
	
	public RenameScriptProjectWizard(Refactoring refactoring) {
		super(refactoring,
			RefactoringMessages.RenameScriptProject_defaultPageTitle, 
			RefactoringMessages.RenameScriptProject_inputPage_description, 
			DLTKPluginImages.DESC_WIZBAN_REFACTOR,
			""/*IScriptHelpContextIds.RENAME_SCRIPT_PROJECT_WIZARD_PAGE*/);
	}
}
