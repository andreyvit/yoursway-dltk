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

	protected NewSourceModulePage newNewSourceModulePage() {
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
