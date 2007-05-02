/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.wizards;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.internal.ui.wizards.NewWizardMessages;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.dltk.ui.DLTKUIPlugin;


public abstract class NewPackageCreationWizard extends NewElementWizard {
	
	public static final String ID_WIZARD = "org.eclipse.dltk.tcl.ui.wizards.NewPackageCreationWizard";

	private NewPackageWizardPage fPage;

	public NewPackageCreationWizard() {
		super();
		setDefaultPageImageDescriptor(DLTKPluginImages.DESC_WIZBAN_NEWPACK);
		setDialogSettings(DLTKUIPlugin.getDefault().getDialogSettings());
		setWindowTitle(NewWizardMessages.NewPackageCreationWizard_title); 
	}

	protected abstract NewPackageWizardPage createNewPackageWizardPage(); 
	/*
	 * @see Wizard#addPages
	 */	
	public void addPages() {
		super.addPages();
		fPage= createNewPackageWizardPage();
		addPage(fPage);
		fPage.init(getSelection());
	}	
		
	protected void finishPage(IProgressMonitor monitor) throws InterruptedException, CoreException {
		fPage.createPackage(monitor); // use the full progress monitor
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.IWizard#performFinish()
	 */
	public boolean performFinish() {
		boolean res= super.performFinish();
		if (res) {
			selectAndReveal(fPage.getModifiedResource());
		}
		return res;
	}
		
	public IModelElement getCreatedElement() {
		return fPage.getNewScriptFolder();
	}	
	
}
