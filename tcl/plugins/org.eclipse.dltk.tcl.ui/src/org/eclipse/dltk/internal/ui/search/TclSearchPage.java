package org.eclipse.dltk.internal.ui.search;

import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.tcl.core.TclLanguageToolkit;

public class TclSearchPage extends DLTKSearchPage {
	protected IDLTKLanguageToolkit getLanguageToolkit() {
		return TclLanguageToolkit.getDefault();
	}
}
