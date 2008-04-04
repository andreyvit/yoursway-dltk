package org.eclipse.dltk.tcl.internal.ui.preferences;

import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.tcl.core.TclLanguageToolkit;
import org.eclipse.dltk.ui.preferences.UserLibraryPreferencePage;

public class TclLibraryPreferencePage extends UserLibraryPreferencePage {
	public TclLibraryPreferencePage() {
	}

	protected IDLTKLanguageToolkit getLanguageToolkit() {
		return TclLanguageToolkit.getDefault();
	}
}
