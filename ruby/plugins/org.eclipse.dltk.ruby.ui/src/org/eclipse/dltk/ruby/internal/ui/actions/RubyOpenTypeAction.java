package org.eclipse.dltk.ruby.internal.ui.actions;

import org.eclipse.dltk.ruby.internal.ui.RubyUILanguageToolkit;
import org.eclipse.dltk.ui.IDLTKUILanguageToolkit;
import org.eclipse.dltk.ui.actions.OpenTypeAction;

public class RubyOpenTypeAction extends OpenTypeAction {
	protected IDLTKUILanguageToolkit getUILanguageToolkit() {
		return RubyUILanguageToolkit.getInstance();
	}
}
