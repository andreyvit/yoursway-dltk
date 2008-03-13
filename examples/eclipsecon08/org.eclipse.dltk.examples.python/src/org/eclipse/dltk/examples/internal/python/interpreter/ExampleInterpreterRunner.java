package org.eclipse.dltk.examples.internal.python.interpreter;

import org.eclipse.dltk.examples.internal.python.core.PythonCorePlugin;
import org.eclipse.dltk.launching.AbstractInterpreterRunner;
import org.eclipse.dltk.launching.IInterpreterInstall;

public class ExampleInterpreterRunner extends AbstractInterpreterRunner {
	public ExampleInterpreterRunner(IInterpreterInstall install) {
		super(install);
	}
	protected String getPluginId() {
		return PythonCorePlugin.PLUGIN_ID;
	}
	protected String getProcessType() {
		return "examplePythonInterpreter";
	}
}
