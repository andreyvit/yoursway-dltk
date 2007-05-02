/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.ui.wizards;

import java.io.ByteArrayInputStream;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.tcl.core.TclNature;
import org.eclipse.dltk.ui.wizards.NewPackageCreationWizard;
import org.eclipse.dltk.ui.wizards.NewPackageWizardPage;

public class TclPackageCreationWizard extends NewPackageCreationWizard {
	private static final String INDEX_PACKAGE_NAME = "pkgIndex.tcl";

	protected NewPackageWizardPage createNewPackageWizardPage() {
		return new NewPackageWizardPage() {
			public void createPackage(IProgressMonitor monitor)
					throws CoreException, InterruptedException {
				super.createPackage(monitor);
				if (this.fCreatedScriptFolder != null) {
					IContainer folder = (IContainer) this.fCreatedScriptFolder
							.getResource();
					IFile file = folder.getFile(new Path(INDEX_PACKAGE_NAME));
					file.create(new ByteArrayInputStream(new byte[] {}),
							IResource.FORCE, monitor);
				}
			}

			protected String getRequiredNature() {
				return TclNature.NATURE_ID;
			}
		};
	}
}
