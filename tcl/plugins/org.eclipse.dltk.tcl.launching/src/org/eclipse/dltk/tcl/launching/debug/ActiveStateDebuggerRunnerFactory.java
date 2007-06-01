package org.eclipse.dltk.tcl.launching.debug;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterRunner;
import org.eclipse.dltk.launching.IInterpreterRunnerFactory;


public class ActiveStateDebuggerRunnerFactory implements
		IInterpreterRunnerFactory {
	
	public ActiveStateDebuggerRunnerFactory() {
		
	}

	public IInterpreterRunner createRunner(IInterpreterInstall install) {
		return new ActiveStateDebuggerRunner(install);
	}
}
