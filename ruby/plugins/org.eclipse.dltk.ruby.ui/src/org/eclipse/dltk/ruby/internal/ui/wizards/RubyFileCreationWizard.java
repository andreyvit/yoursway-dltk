package org.eclipse.dltk.ruby.internal.ui.wizards;

import org.eclipse.dltk.ruby.core.RubyNature;
import org.eclipse.dltk.ruby.internal.ui.RubyImages;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.wizards.NewSourceModulePage;
import org.eclipse.dltk.ui.wizards.NewSourceModuleWizard;

public class RubyFileCreationWizard extends NewSourceModuleWizard {
	
	public RubyFileCreationWizard(){
		setDefaultPageImageDescriptor(RubyImages.DESC_WIZBAN_FILE_CREATION);
		setDialogSettings(DLTKUIPlugin.getDefault().getDialogSettings());
		setWindowTitle(RubyWizardMessages.FileCreationWizard_title);
	}

	protected NewSourceModulePage newNewSourceModulePage() {		
		return new NewSourceModulePage() {
						
			protected String getRequiredNature() {
				return RubyNature.NATURE_ID;
			}

			protected String getPageDescription() {
				return "This wizard creates a new Ruby file.";
			}

			protected String getPageTitle() {		
				return "Create new Ruby file";
			}
		};		
	}
}
