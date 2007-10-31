package org.eclipse.dltk.ruby.fastdebugger;

import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterRunner;
import org.eclipse.dltk.launching.IInterpreterRunnerFactory;

public class FastRailsDebuggerRunnerFactory implements
		IInterpreterRunnerFactory {
	public IInterpreterRunner createRunner(IInterpreterInstall install) {
		return new FastRailsDebuggerRunner(install);
	}
}