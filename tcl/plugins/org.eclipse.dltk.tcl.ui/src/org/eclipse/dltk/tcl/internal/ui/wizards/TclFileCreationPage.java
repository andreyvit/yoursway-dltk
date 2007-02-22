package org.eclipse.dltk.tcl.internal.ui.wizards;

import org.eclipse.dltk.tcl.core.TclNature;
import org.eclipse.dltk.ui.wizards.NewSourceModulePage;


public class TclFileCreationPage extends NewSourceModulePage {

	protected String getRequiredNature() {
		return TclNature.NATURE_ID;
	}

	protected String getPageDescription() {
		return "This wizard creates a new Tcl file.";
	}

	protected String getPageTitle() {		
		return "Create new Tcl file";
	}
}
