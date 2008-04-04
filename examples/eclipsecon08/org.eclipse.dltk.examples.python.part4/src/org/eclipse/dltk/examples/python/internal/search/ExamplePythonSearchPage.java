package org.eclipse.dltk.examples.python.internal.search;

import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.examples.internal.python.core.ExamplePythonLanguageToolkit;
import org.eclipse.dltk.ui.search.ScriptSearchPage;

public class ExamplePythonSearchPage extends ScriptSearchPage {
	protected IDLTKLanguageToolkit getLanguageToolkit() {
		return ExamplePythonLanguageToolkit.getDefault();
	}
}
