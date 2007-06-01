package org.eclipse.dltk.launching;


public interface IInterpreterRunnerFactory {
	IInterpreterRunner createRunner(IInterpreterInstall install);
}
