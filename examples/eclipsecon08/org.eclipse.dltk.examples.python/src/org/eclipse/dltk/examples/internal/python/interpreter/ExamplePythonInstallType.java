package org.eclipse.dltk.examples.internal.python.interpreter;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.ILog;
import org.eclipse.dltk.examples.internal.python.core.ExamplePythonNature;
import org.eclipse.dltk.examples.internal.python.core.PythonCorePlugin;
import org.eclipse.dltk.internal.launching.AbstractInterpreterInstallType;
import org.eclipse.dltk.launching.IInterpreterInstall;

public class ExamplePythonInstallType extends AbstractInterpreterInstallType {
	private static final String[] INTERPRETER_NAMES = { "python", "pythonw" };
	public String getNatureId() {
		return ExamplePythonNature.PYTHON_NATURE;
	}
	public String getName() {
		return "Example Python";
	}
	protected String getPluginId() {
		return PythonCorePlugin.PLUGIN_ID;
	}
	protected String[] getPossibleInterpreterNames() {
		return INTERPRETER_NAMES;
	}
	protected IInterpreterInstall doCreateInterpreterInstall(String id) {
		return new ExamplePythonInstall(this, id);
	}
	protected File createPathFile() throws IOException {
		return storeToMetadata(PythonCorePlugin.getDefault(), "path.py", "scripts/path.py");
	}
	protected ILog getLog() {
		return PythonCorePlugin.getDefault().getLog();
	}
}
