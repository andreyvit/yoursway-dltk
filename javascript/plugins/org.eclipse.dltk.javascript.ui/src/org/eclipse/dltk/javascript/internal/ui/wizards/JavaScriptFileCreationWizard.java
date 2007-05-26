/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.javascript.internal.ui.wizards;

import org.eclipse.dltk.javascript.core.JavaScriptNature;
import org.eclipse.dltk.javascript.ui.JavaScriptImages;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.wizards.NewSourceModulePage;
import org.eclipse.dltk.ui.wizards.NewSourceModuleWizard;

public class JavaScriptFileCreationWizard extends NewSourceModuleWizard {

	public JavaScriptFileCreationWizard() {
		setDefaultPageImageDescriptor(JavaScriptImages.DESC_WIZBAN_PROJECT_CREATION);
		setDialogSettings(DLTKUIPlugin.getDefault().getDialogSettings());
		setWindowTitle(JavascriptWizardMessages.FileCreationWizard_title);
	}

	protected NewSourceModulePage createNewSourceModulePage() {
		return new NewSourceModulePage() {

			protected String getRequiredNature() {
				return JavaScriptNature.NATURE_ID;
			}

			protected String getPageDescription() {
				return "This wizard creates a new JavaScript file.";
			}

			protected String getPageTitle() {
				return "Create new JavaScript file";
			}
		};
	}
}
