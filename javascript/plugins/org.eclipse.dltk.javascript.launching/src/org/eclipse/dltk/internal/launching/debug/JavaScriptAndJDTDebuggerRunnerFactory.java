package org.eclipse.dltk.internal.launching.debug;

import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterRunner;
import org.eclipse.dltk.launching.IInterpreterRunnerFactory;

public class JavaScriptAndJDTDebuggerRunnerFactory implements
		IInterpreterRunnerFactory {

	public JavaScriptAndJDTDebuggerRunnerFactory() {

	}

	public IInterpreterRunner createRunner(IInterpreterInstall install) {
		return new JavaScriptAndJDTDebuggerRunner(install);
	}
}
