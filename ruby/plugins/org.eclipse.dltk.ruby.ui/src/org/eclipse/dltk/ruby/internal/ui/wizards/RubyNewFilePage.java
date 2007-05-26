package org.eclipse.dltk.ruby.internal.ui.wizards;

import org.eclipse.dltk.ruby.core.RubyNature;
import org.eclipse.dltk.ui.wizards.NewSourceModulePage;

public abstract class RubyNewFilePage extends NewSourceModulePage {
	protected String getRequiredNature() {
		return RubyNature.NATURE_ID;
	}
}
