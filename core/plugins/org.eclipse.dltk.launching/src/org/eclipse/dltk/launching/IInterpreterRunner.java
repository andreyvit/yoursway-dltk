package org.eclipse.dltk.launching;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;

public interface IInterpreterRunner {
	public void run(InterpreterRunnerConfiguration configuration,
			ILaunch launch, IProgressMonitor monitor) throws CoreException;

}
