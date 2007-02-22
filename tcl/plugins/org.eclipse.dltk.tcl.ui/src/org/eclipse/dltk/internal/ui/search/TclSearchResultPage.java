package org.eclipse.dltk.internal.ui.search;

import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.tcl.core.TclLanguageToolkit;
import org.eclipse.search.ui.ISearchResultPage;

public class TclSearchResultPage extends DLTKSearchResultPage implements
		ISearchResultPage {

	protected IDLTKLanguageToolkit getLanguageToolkit() {
		return TclLanguageToolkit.getDefault();
	}
}
