/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.ui.wizards;

import org.eclipse.dltk.ruby.internal.ui.RubyImages;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.wizards.NewSourceModulePage;
import org.eclipse.dltk.ui.wizards.NewSourceModuleWizard;

public class RubyNewFileWizard extends NewSourceModuleWizard {
	
	public static final String WIZARD_ID = "org.eclipse.dltk.ruby.wizards.newfile"; //$NON-NLS-1$

	public RubyNewFileWizard() {
		setDefaultPageImageDescriptor(RubyImages.DESC_WIZBAN_FILE_CREATION);
		setDialogSettings(DLTKUIPlugin.getDefault().getDialogSettings());
		setWindowTitle(RubyWizardMessages.NewFileWizard_title);
	}

	protected NewSourceModulePage createNewSourceModulePage() {
		return new RubyNewFilePage() {
			protected String getPageTitle() {
				return RubyWizardMessages.NewFilePage_title;
			}
			
			protected String getPageDescription() {
				return RubyWizardMessages.NewFilePage_description;
			}
		};
	}
}
