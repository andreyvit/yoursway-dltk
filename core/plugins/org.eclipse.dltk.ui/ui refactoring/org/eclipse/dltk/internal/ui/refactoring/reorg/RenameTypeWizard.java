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
package org.eclipse.dltk.internal.ui.refactoring.reorg;

import org.eclipse.dltk.internal.ui.refactoring.RefactoringMessages;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ltk.core.refactoring.Refactoring;


/**
 * The type renaming wizard.
 */
public class RenameTypeWizard extends RenameRefactoringWizard {

	public RenameTypeWizard(Refactoring refactoring) {
		this(refactoring, RefactoringMessages.RenameTypeWizard_defaultPageTitle, RefactoringMessages.RenameTypeWizardInputPage_description, DLTKPluginImages.DESC_WIZBAN_REFACTOR_TYPE,
				""/*IScriptHelpContextIds.RENAME_TYPE_WIZARD_PAGE*/);
	}

	public RenameTypeWizard(Refactoring refactoring, String defaultPageTitle, String inputPageDescription, ImageDescriptor inputPageImageDescriptor, String pageContextHelpId) {
		super(refactoring, defaultPageTitle, inputPageDescription, inputPageImageDescriptor, pageContextHelpId);
	}

	/*
	 * non java-doc
	 * 
	 * @see RefactoringWizard#addUserInputPages
	 */
	protected void addUserInputPages() {
		super.addUserInputPages();
//		if (isRenameType())
//			addPage(new RenameTypeWizardSimilarElementsPage());

	}

//	public RenameTypeProcessor getRenameTypeProcessor() {
//		RefactoringProcessor proc= ((RenameRefactoring) getRefactoring()).getProcessor();
//		if (proc instanceof RenameTypeProcessor)
//			return (RenameTypeProcessor) proc;
//		else if (proc instanceof RenameSourceModuleProcessor) {
//			RenameSourceModuleProcessor rcu= (RenameSourceModuleProcessor) proc;
//			return rcu.getRenameTypeProcessor();
//		}
//		Assert.isTrue(false); // Should never get here
//		return null;
//	}

	protected boolean isRenameType() {
		return true;
	}

//	protected RenameInputWizardPage createInputPage(String message, String initialSetting) {
//		return new RenameTypeWizardInputPage(message, IScriptHelpContextIds.RENAME_TYPE_WIZARD_PAGE, true, initialSetting) {
//
//			protected RefactoringStatus validateTextField(String text) {
//				return validateNewName(text);
//			}
//		};
//	}
}
