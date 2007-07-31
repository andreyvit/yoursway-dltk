package org.eclipse.dltk.xotcl.internal.ui.wizards;

import org.eclipse.dltk.tcl.core.TclNature;
import org.eclipse.dltk.ui.wizards.NewSourceModulePage;

public abstract class XOTclNewFilePage extends NewSourceModulePage {
	protected String getRequiredNature() {
		return TclNature.NATURE_ID;
	}
}
