package org.eclipse.dltk.javascript.jsjdtdebugger;

import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterRunner;
import org.eclipse.dltk.launching.IInterpreterRunnerFactory;

public class JavaScriptAndJDTDebuggerRunnerFactory implements
		IInterpreterRunnerFactory {

	/*
	 * @see org.eclipse.dltk.launching.IInterpreterRunnerFactory#createRunner(org.eclipse.dltk.launching.IInterpreterInstall)
	 */
	public IInterpreterRunner createRunner(IInterpreterInstall install) {
		return new JavaScriptAndJDTDebuggerRunner(install);
	}
}
