package org.eclipse.dltk.javascript.launching;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.dltk.javascript.core.JavaScriptNature;
import org.eclipse.dltk.launching.AbstractScriptLaunchConfigurationDelegate;
import org.eclipse.dltk.launching.IInterpreterRunner;
import org.eclipse.dltk.launching.InterpreterConfig;

public class JavaScriptLaunchConfigurationDelegate extends
		AbstractScriptLaunchConfigurationDelegate {

	protected void runRunner(ILaunchConfiguration configuration,
			IInterpreterRunner runner, InterpreterConfig config,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {
		if (runner instanceof IJavaScriptInterpreterRunner) {
			IJavaScriptInterpreterRunner jrunner = (IJavaScriptInterpreterRunner) runner;
			configureRunner(jrunner,config,launch);
		}
		runner.run(config, launch, monitor);
	}

	protected void configureRunner(IJavaScriptInterpreterRunner jrunner, InterpreterConfig config, ILaunch launch) {

	}

	public String getLanguageId() {
		return JavaScriptNature.NATURE_ID;
	}
}
