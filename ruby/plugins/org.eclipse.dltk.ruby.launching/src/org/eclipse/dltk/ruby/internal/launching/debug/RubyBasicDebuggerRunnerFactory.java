package org.eclipse.dltk.ruby.internal.launching.debug;

import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterRunner;
import org.eclipse.dltk.launching.IInterpreterRunnerFactory;

public class RubyBasicDebuggerRunnerFactory implements
		IInterpreterRunnerFactory {
	
	public RubyBasicDebuggerRunnerFactory() {
		
	}

	public IInterpreterRunner createRunner(IInterpreterInstall install) {
		return new RubyBasicDebuggerRunner(install);
	}
}
