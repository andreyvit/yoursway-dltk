/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.wizards;

import java.io.ByteArrayInputStream;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.jface.viewers.IStructuredSelection;

public abstract class NewSourceModuleWizard extends NewElementWizard {

	private NewSourceModulePage page;

	private String filename;

	private ISourceModule module;

	public NewSourceModuleWizard() {
	}
	
	protected abstract NewSourceModulePage newNewSourceModulePage();
	
	public void addPages() {
		page = newNewSourceModulePage();
		addPage(page);
		page.init(getSelection());
	}

	public IModelElement getCreatedElement() {
		return module;
	}

	public boolean performFinish() {
		// TODO: add correct behaviour

		try {
			page.createFile(null);
		} catch (CoreException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return true;

	}

	protected void finishPage(IProgressMonitor monitor)
			throws InterruptedException, CoreException {

		IStructuredSelection selection = getSelection();
		Object element = selection.getFirstElement();

		if (element instanceof IModelElement) {

			IModelElement modelElement = (IModelElement) element;
			IResource res = modelElement.getResource();

			if (res instanceof IContainer) {
				IContainer container = (IContainer) res;
				IFile file = container.getFile(new Path(filename));
				ByteArrayInputStream stream = new ByteArrayInputStream(
						new byte[] {});
				file.create(stream, false, null);
			}
		}
	}
}
