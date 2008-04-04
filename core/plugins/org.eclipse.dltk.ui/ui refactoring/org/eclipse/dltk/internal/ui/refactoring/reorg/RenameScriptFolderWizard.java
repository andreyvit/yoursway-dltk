/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.refactoring.reorg;

import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.internal.corext.refactoring.rename.RenameScriptFolderProcessor;
import org.eclipse.dltk.internal.ui.refactoring.RefactoringMessages;
import org.eclipse.dltk.internal.ui.util.RowLayouter;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.ltk.core.refactoring.Refactoring;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.RenameRefactoring;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;



public class RenameScriptFolderWizard extends RenameRefactoringWizard {
	
	public RenameScriptFolderWizard(Refactoring refactoring) {
		super(refactoring, 
			RefactoringMessages.RenamePackageWizard_defaultPageTitle, 
			RefactoringMessages.RenamePackageWizard_inputPage_description, 
			DLTKPluginImages.DESC_WIZBAN_REFACTOR_PACKAGE,
			""/*IScriptHelpContextIds.RENAME_PACKAGE_WIZARD_PAGE*/); //$NON-NLS-1$
	}
	
	protected RenameInputWizardPage createInputPage(String message, String initialSetting) {
		if (DLTKCore.DEBUG) {
			System.err.println("TODO: Add help support"); //$NON-NLS-1$
		}
		return new RenamePackageInputWizardPage(message, ""/*IScriptHelpContextIds.RENAME_PACKAGE_WIZARD_PAGE*/, initialSetting) { //$NON-NLS-1$
			protected RefactoringStatus validateTextField(String text) {
				return validateNewName(text);
			}	
		};
	}

	private static class RenamePackageInputWizardPage extends RenameInputWizardPage {
		
		private Button fRenameSubpackages;
		private static final String RENAME_SUBPACKAGES= "renameSubpackages"; //$NON-NLS-1$
		
		public RenamePackageInputWizardPage(String message, String contextHelpId, String initialValue) {
			super(message, contextHelpId, true, initialValue);
		}
	
		protected void addAdditionalOptions(Composite composite, RowLayouter layouter) {
			fRenameSubpackages= new Button(composite, SWT.CHECK);
			fRenameSubpackages.setText(RefactoringMessages.RenamePackageWizard_rename_subpackages);
			boolean subpackagesSelection= getBooleanSetting(RENAME_SUBPACKAGES, getRenamePackageProcessor().getRenameSubpackages());
			fRenameSubpackages.setSelection(subpackagesSelection);
			getRenamePackageProcessor().setRenameSubpackages(subpackagesSelection);
			fRenameSubpackages.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			fRenameSubpackages.addSelectionListener(new SelectionAdapter(){
				public void widgetSelected(SelectionEvent e) {
					getRenamePackageProcessor().setRenameSubpackages(fRenameSubpackages.getSelection());
				}
			});
			layouter.perform(fRenameSubpackages);
			
			Label separator= new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
			separator.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			layouter.perform(separator);
		}
		
		public void dispose() {
			if (saveSettings() && fRenameSubpackages.isEnabled())
				saveBooleanSetting(RENAME_SUBPACKAGES, fRenameSubpackages);
			super.dispose();
		}
		
		private RenameScriptFolderProcessor getRenamePackageProcessor() {
			return (RenameScriptFolderProcessor) ((RenameRefactoring) getRefactoring()).getProcessor();
		}
	}
}
