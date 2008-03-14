package org.eclipse.dltk.examples.python.internal;

import org.eclipse.dltk.examples.internal.python.ui.ExamplePythonUILanguageToolkit;
import org.eclipse.dltk.ui.IDLTKUILanguageToolkit;
import org.eclipse.dltk.ui.actions.OpenTypeAction;

public class ExamplePythonOpenTypeAction extends OpenTypeAction {
	protected IDLTKUILanguageToolkit getUILanguageToolkit() {
		return new ExamplePythonUILanguageToolkit();
	}
}
