package org.eclipse.dltk.itcl.internal.ui.wizards;

import org.eclipse.dltk.tcl.core.TclNature;
import org.eclipse.dltk.ui.wizards.NewSourceModulePage;

public abstract class IncrTclNewFilePage extends NewSourceModulePage {
	protected String getRequiredNature() {
		return TclNature.NATURE_ID;
	}
}
