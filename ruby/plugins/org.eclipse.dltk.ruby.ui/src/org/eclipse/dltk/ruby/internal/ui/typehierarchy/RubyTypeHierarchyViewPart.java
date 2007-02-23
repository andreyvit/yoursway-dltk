package org.eclipse.dltk.ruby.internal.ui.typehierarchy;

import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.internal.ui.typehierarchy.TypeHierarchyViewPart;
import org.eclipse.dltk.ruby.core.RubyLanguageToolkit;
import org.eclipse.dltk.ruby.internal.ui.RubyUI;
import org.eclipse.jface.preference.IPreferenceStore;

public class RubyTypeHierarchyViewPart extends TypeHierarchyViewPart {

	protected IDLTKLanguageToolkit getLanguageToolkit() {
		return RubyLanguageToolkit.getDefault();
	}

	protected IPreferenceStore getPreferenceStore() {
		return RubyUI.getDefault().getPreferenceStore();
	}

}
