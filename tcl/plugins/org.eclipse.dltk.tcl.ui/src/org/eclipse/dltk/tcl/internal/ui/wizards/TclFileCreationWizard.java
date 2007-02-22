package org.eclipse.dltk.tcl.internal.ui.wizards;

import org.eclipse.dltk.ui.wizards.NewSourceModulePage;
import org.eclipse.dltk.ui.wizards.NewSourceModuleWizard;

public class TclFileCreationWizard extends NewSourceModuleWizard {
	public static final String ID_WIZARD = "org.eclipse.dltk.tcl.internal.ui.wizards.TclFileCreationWizard";
	
	protected NewSourceModulePage newNewSourceModulePage() {
		return new TclFileCreationPage();
	}
}
