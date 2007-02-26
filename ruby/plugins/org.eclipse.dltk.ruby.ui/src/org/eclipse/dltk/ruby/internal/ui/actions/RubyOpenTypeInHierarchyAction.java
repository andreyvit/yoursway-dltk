package org.eclipse.dltk.ruby.internal.ui.actions;

import org.eclipse.dltk.internal.ui.actions.OpenTypeInHierarchyAction;
import org.eclipse.dltk.ruby.internal.ui.RubyUILanguageToolkit;
import org.eclipse.dltk.ui.IDLTKUILanguageToolkit;

public class RubyOpenTypeInHierarchyAction extends OpenTypeInHierarchyAction {

	protected IDLTKUILanguageToolkit getLanguageToolkit() {
		return RubyUILanguageToolkit.getInstance();
	}
}
