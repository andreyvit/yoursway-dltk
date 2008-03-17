package org.eclipse.dltk.examples.internal.python.interpreter;

import org.eclipse.dltk.examples.internal.python.core.ExamplePythonNature;
import org.eclipse.dltk.launching.AbstractInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterInstallType;

public class ExamplePythonInstall extends AbstractInterpreterInstall {
	public ExamplePythonInstall(IInterpreterInstallType type, String id) {
		super(type, id);
	}
	public String getNatureId() {
		return ExamplePythonNature.PYTHON_NATURE;
	}
}