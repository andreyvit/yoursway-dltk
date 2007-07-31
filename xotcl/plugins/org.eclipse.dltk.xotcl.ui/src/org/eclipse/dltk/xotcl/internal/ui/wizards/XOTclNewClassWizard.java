package org.eclipse.dltk.xotcl.internal.ui.wizards;

import org.eclipse.dltk.tcl.internal.ui.TclImages;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.wizards.NewSourceModulePage;
import org.eclipse.dltk.ui.wizards.NewSourceModuleWizard;

public class XOTclNewClassWizard extends NewSourceModuleWizard {

	public static final String WIZARD_ID = "org.eclipse.dltk.xotcl.wizards.newclass";

	public XOTclNewClassWizard() {
		setDefaultPageImageDescriptor(TclImages.DESC_WIZBAN_FILE_CREATION);
		setDialogSettings(DLTKUIPlugin.getDefault().getDialogSettings());
		setWindowTitle(XOTclWizardMessages.NewClassWizard_title);
	}

	protected NewSourceModulePage createNewSourceModulePage() {
		return new XOTclNewFilePage() {
			protected String getPageTitle() {
				return XOTclWizardMessages.NewClassPage_title;
			}

			protected String getPageDescription() {
				return XOTclWizardMessages.NewClassPage_description;
			}
			protected String getFileName() {
				final String fileText = getFileText();

				String[] extensions = new String[]{"xotcl", "tcl"};
				for (int i = 0; i < extensions.length; ++i) {
					String extension = extensions[i];
					if (extension.length() > 0 && fileText.endsWith("." + extension)) {
						return fileText;
					}
				}

				return fileText + "." + extensions[0];
			}
			protected String getFileContent() {
				String text = getFileText();
				String className = Character.toUpperCase(text.charAt(0))
						+ text.substring(1);
				return XOTclWizardMessages.XOTcl_module_prefix + "Class " + className + "\n";
			}
		};
	}
}