/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
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


public class RenameSourceModuleWizard extends RenameRefactoringWizard {
	
	public RenameSourceModuleWizard(Refactoring refactoring) {
		super(refactoring,
			RefactoringMessages.RenameCuWizard_defaultPageTitle, 
			RefactoringMessages.RenameCuWizard_inputPage_description, 
			DLTKPluginImages.DESC_WIZBAN_REFACTOR_CU,
			""/*IScriptHelpContextIds.RENAME_CU_WIZARD_PAGE*/); //$NON-NLS-1$
	}
	
//	protected RefactoringStatus validateNewName(String newName) {
//		String fullName= ModelUtil.getRenamedCUName(getSourceModule(), newName);
//		return super.validateNewName(fullName);
//	}

//	private ISourceModule getSourceModule() {
//		return (ISourceModule) getSourceModuleProcessor().getElements()[0];
//	}

//	private RenameSourceModuleProcessor getSourceModuleProcessor() {
//		return ((RenameSourceModuleProcessor) ((RenameRefactoring) getRefactoring()).getProcessor());
//	}
}
