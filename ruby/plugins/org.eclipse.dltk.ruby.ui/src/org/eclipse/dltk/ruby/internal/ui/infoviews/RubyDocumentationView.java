package org.eclipse.dltk.ruby.internal.ui.infoviews;

import org.eclipse.dltk.ruby.core.RubyNature;
import org.eclipse.dltk.ruby.internal.ui.RubyUI;
import org.eclipse.dltk.ui.infoviews.AbstractDocumentationView;
import org.eclipse.jface.preference.IPreferenceStore;


public class RubyDocumentationView extends AbstractDocumentationView {
	protected IPreferenceStore getPreferenceStore() {
		return RubyUI.getDefault().getPreferenceStore();
	}

	protected String getNature() {
		return RubyNature.NATURE_ID;
	}
}
