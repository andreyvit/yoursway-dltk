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

public class NewSourceFolderCreationWizard extends NewElementWizard {

	private NewSourceFolderWizardPage fPage;

	public NewSourceFolderCreationWizard() {
		super();
		setDefaultPageImageDescriptor(DLTKPluginImages.DESC_WIZBAN_NEWSRCFOLDR);
		setDialogSettings(DLTKUIPlugin.getDefault().getDialogSettings());
		setWindowTitle(NewWizardMessages.NewSourceFolderCreationWizard_title);
	}

	public void addPages() {
		super.addPages();
		fPage = new NewSourceFolderWizardPage();
		addPage(fPage);
		fPage.init(getSelection());
	}

	protected void finishPage(IProgressMonitor monitor)
			throws InterruptedException, CoreException {
		fPage.createProjectFragment(monitor); // use the full progress monitor
	}

	public boolean performFinish() {
		boolean res = super.performFinish();
		if (res) {
			selectAndReveal(fPage.getCorrespondingResource());
		}
		return res;
	}

	public IModelElement getCreatedElement() {
		return fPage.getNewProjectFragment();
	}
}
