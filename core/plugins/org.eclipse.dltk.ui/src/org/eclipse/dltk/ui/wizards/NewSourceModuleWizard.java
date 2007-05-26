/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
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
import org.eclipse.dltk.core.ISourceModule;

public abstract class NewSourceModuleWizard extends NewElementWizard {

	private NewSourceModulePage page;

	private ISourceModule module;

	protected abstract NewSourceModulePage createNewSourceModulePage();

	public void addPages() {
		super.addPages();

		page = createNewSourceModulePage();
		page.init(getSelection());
		addPage(page);
	}

	public IModelElement getCreatedElement() {
		return module;
	}

	protected void finishPage(IProgressMonitor monitor)
			throws InterruptedException, CoreException {
		module = page.createFile(monitor);
	}
}
