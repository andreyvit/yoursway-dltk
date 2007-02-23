package org.eclipse.dltk.ruby.internal.ui.typehierarchy;

import org.eclipse.dltk.internal.ui.typehierarchy.HierarchyInformationControl;
import org.eclipse.dltk.ruby.internal.ui.RubyUI;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Shell;

public class RubyHierarchyInformationControl extends
		HierarchyInformationControl {

	public RubyHierarchyInformationControl(Shell parent, int shellStyle, int treeStyle) {
		super(parent, shellStyle, treeStyle);
	}

	protected IPreferenceStore getPreferenceStore() {
		return RubyUI.getDefault().getPreferenceStore();
	}

}
