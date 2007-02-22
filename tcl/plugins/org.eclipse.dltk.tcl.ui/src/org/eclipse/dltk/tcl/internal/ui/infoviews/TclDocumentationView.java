package org.eclipse.dltk.tcl.internal.ui.infoviews;

import org.eclipse.dltk.tcl.core.TclNature;
import org.eclipse.dltk.tcl.internal.ui.TclUI;
import org.eclipse.dltk.ui.infoviews.AbstractDocumentationView;
import org.eclipse.jface.preference.IPreferenceStore;


public class TclDocumentationView extends AbstractDocumentationView {
	protected IPreferenceStore getPreferenceStore() {
		return TclUI.getDefault().getPreferenceStore();
	}

	protected String getNature() {
		return TclNature.NATURE_ID;
	}
}
