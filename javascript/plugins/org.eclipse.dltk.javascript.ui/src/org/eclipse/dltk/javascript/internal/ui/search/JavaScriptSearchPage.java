package org.eclipse.dltk.javascript.internal.ui.search;

import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.internal.ui.search.DLTKSearchPage;
import org.eclipse.dltk.javascript.core.JavaScriptLanguageToolkit;

public class JavaScriptSearchPage extends DLTKSearchPage {
	protected IDLTKLanguageToolkit getLanguageToolkit() {
		return JavaScriptLanguageToolkit.getDefault();
	}
}
