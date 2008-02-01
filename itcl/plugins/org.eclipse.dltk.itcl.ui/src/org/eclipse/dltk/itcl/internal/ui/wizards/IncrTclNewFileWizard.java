/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.itcl.internal.ui.wizards;

import org.eclipse.dltk.tcl.internal.ui.TclImages;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.wizards.NewSourceModulePage;
import org.eclipse.dltk.ui.wizards.NewSourceModuleWizard;

public class IncrTclNewFileWizard extends NewSourceModuleWizard {
	
	public static final String WIZARD_ID = "org.eclipse.dltk.xotcl.wizards.newfile";

	public IncrTclNewFileWizard() {
		setDefaultPageImageDescriptor(TclImages.DESC_WIZBAN_FILE_CREATION);
		setDialogSettings(DLTKUIPlugin.getDefault().getDialogSettings());
		setWindowTitle(IncrTclWizardMessages.NewFileWizard_title);
	}

	protected NewSourceModulePage createNewSourceModulePage() {
		return new IncrTclNewFilePage() {
			protected String getFileName() {
				final String fileText = getFileText();

				String[] extensions = new String[]{"itcl", "tcl"};
				for (int i = 0; i < extensions.length; ++i) {
					String extension = extensions[i];
					if (extension.length() > 0 && fileText.endsWith("." + extension)) {
						return fileText;
					}
				}

				return fileText + "." + extensions[0];
			}
			protected String getPageTitle() {
				return IncrTclWizardMessages.NewFilePage_title;
			}
			
			protected String getPageDescription() {
				return IncrTclWizardMessages.NewFilePage_description;
			}
		};
	}
}
