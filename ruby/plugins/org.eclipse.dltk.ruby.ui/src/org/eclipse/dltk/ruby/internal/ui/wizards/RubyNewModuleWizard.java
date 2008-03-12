package org.eclipse.dltk.ruby.internal.ui.wizards;

import org.eclipse.dltk.ruby.internal.ui.RubyImages;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.wizards.NewSourceModulePage;
import org.eclipse.dltk.ui.wizards.NewSourceModuleWizard;

public class RubyNewModuleWizard extends NewSourceModuleWizard {
	
	public static final String WIZARD_ID = "org.eclipse.dltk.ruby.wizards.newmodule"; //$NON-NLS-1$

	public RubyNewModuleWizard() {
		setDefaultPageImageDescriptor(RubyImages.DESC_WIZBAN_FILE_CREATION);
		setDialogSettings(DLTKUIPlugin.getDefault().getDialogSettings());
		setWindowTitle(RubyWizardMessages.NewModuleWizard_title);
	}

	protected NewSourceModulePage createNewSourceModulePage() {
		return new RubyNewFilePage() {
			protected String getPageTitle() {
				return RubyWizardMessages.NewModulePage_title;
			}
			
			protected String getPageDescription() {
				return RubyWizardMessages.NewModulePage_description;
			}

			protected String getFileContent() {
				String text = getFileText();
				String className = Character.toUpperCase(text.charAt(0))
						+ text.substring(1);
				return "module " + className + "\nend"; //$NON-NLS-1$ //$NON-NLS-2$
			}
		};
	}
}