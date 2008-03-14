package org.eclipse.dltk.examples.internal.python.core;

import org.eclipse.dltk.core.AbstractLanguageToolkit;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;

public class ExamplePythonLanguageToolkit extends AbstractLanguageToolkit {
	private static ExamplePythonLanguageToolkit toolkit;

	public static IDLTKLanguageToolkit getDefault() {
		if (toolkit == null) {
			toolkit = new ExamplePythonLanguageToolkit();
		}
		return toolkit;
	}

	public String getLanguageName() {
		return "Python";
	}

	public String getNatureId() {
		return ExamplePythonNature.PYTHON_NATURE;
	}

	public String getLanguageContentType() {
		return "org.eclipse.dltk.examples.python.content-type";
	}
}
