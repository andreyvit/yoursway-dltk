package org.eclipse.dltk.examples.internal.python.interpreter;

import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.dltk.examples.internal.python.core.ExamplePythonNature;
import org.eclipse.dltk.launching.AbstractInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterInstallType;
import org.eclipse.dltk.launching.IInterpreterRunner;

public class ExamplePythonInstall extends AbstractInterpreterInstall {
	public ExamplePythonInstall(IInterpreterInstallType type, String id) {
		super(type, id);
	}
	public IInterpreterRunner getInterpreterRunner(String mode) {
		IInterpreterRunner runner = super.getInterpreterRunner(mode);
		if (runner != null) {
			return runner;
		}
		if (mode.equals(ILaunchManager.RUN_MODE)) {
			return new ExampleInterpreterRunner(this);
		}
		return null;
	}

	public String getNatureId() {
		return ExamplePythonNature.PYTHON_NATURE;
	}
}