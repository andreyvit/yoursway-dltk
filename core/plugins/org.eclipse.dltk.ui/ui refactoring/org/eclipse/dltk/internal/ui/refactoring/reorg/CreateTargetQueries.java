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

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.internal.corext.refactoring.reorg.ICreateTargetQueries;
import org.eclipse.dltk.internal.corext.refactoring.reorg.ICreateTargetQuery;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.util.PixelConverter;
import org.eclipse.dltk.ui.wizards.NewPackageCreationWizard;
import org.eclipse.dltk.ui.wizards.NewPackageWizardPage;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWizard;


public class CreateTargetQueries implements ICreateTargetQueries {

	private final Wizard fWizard;
	private final Shell fShell;

	public CreateTargetQueries(Wizard wizard) {
		fWizard= wizard;
		fShell= null;
	}
	
	public CreateTargetQueries(Shell shell) {
		fShell = shell;
		fWizard= null;
	}

	private Shell getShell() {
		Assert.isTrue(fWizard == null || fShell == null);
		if (fWizard != null)
			return fWizard.getContainer().getShell();
		else if (fShell != null)
			return fShell;
		else
			return DLTKUIPlugin.getActiveWorkbenchShell();
	}
	
	public ICreateTargetQuery createNewPackageQuery() {
		return new ICreateTargetQuery() {
			public Object getCreatedTarget(Object selection) {
				IWorkbenchWizard packageCreationWizard= new NewPackageCreationWizard() {
					protected NewPackageWizardPage createNewPackageWizardPage() {
						return new NewPackageWizardPage() {
							protected String getRequiredNature() {
								if (DLTKCore.DEBUG) {
									System.err.println("TODO: Add correct create new package for move case.");
								}
								return null;
							}							
						};
					}					
				};
				
				IWizardPage[] pages= openNewElementWizard(packageCreationWizard, getShell(), selection);
				
				NewPackageWizardPage page= (NewPackageWizardPage) pages[0];
				return page.getNewScriptFolder();
			}
			
			public String getNewButtonLabel() {
				return ReorgMessages.ReorgMoveWizard_newPackage;
			}
		};
	}
	
	private IWizardPage[] openNewElementWizard(IWorkbenchWizard wizard, Shell shell, Object selection) {
		wizard.init(DLTKUIPlugin.getDefault().getWorkbench(), new StructuredSelection(selection));
		
		WizardDialog dialog= new WizardDialog(shell, wizard);
		PixelConverter converter= new PixelConverter(shell);

		dialog.setMinimumPageSize(converter.convertWidthInCharsToPixels(70), converter.convertHeightInCharsToPixels(20));
		dialog.create();
		dialog.open();
		IWizardPage[] pages= wizard.getPages();
		return pages;
	}
}
