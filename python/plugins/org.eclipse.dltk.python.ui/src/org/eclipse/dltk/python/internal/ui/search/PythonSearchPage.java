package org.eclipse.dltk.python.internal.ui.search;

import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.python.core.PythonLanguageToolkit;
import org.eclipse.dltk.ui.search.ScriptSearchPage;

/**
 * Python search page
 */
public class PythonSearchPage extends ScriptSearchPage {

	/*
	 * @see org.eclipse.dltk.ui.search.ScriptSearchPage#getLanguageToolkit()
	 */
	protected IDLTKLanguageToolkit getLanguageToolkit() {
		return PythonLanguageToolkit.getDefault();
	}
}
