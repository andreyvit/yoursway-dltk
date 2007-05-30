package org.eclipse.dltk.launching;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.dltk.debug.core.IDebuggingEngine;
import org.eclipse.dltk.internal.launching.DLTKLaunchingPlugin;

public class DebuggingEngineRunner extends AbstractInterpreterRunner {
	private IDebuggingEngine engine;

	protected DebuggingEngineRunner(IInterpreterInstall install, IDebuggingEngine engine) {
		super(install);
		
		if (engine == null) {
			throw new IllegalArgumentException();
		}
		
		this.engine = engine;		 
	}

	protected String getPluginId() {
		return DLTKLaunchingPlugin.ID_PLUGIN;
	}

	public void run(InterpreterConfig config, ILaunch launch,
				IProgressMonitor monitor) throws CoreException {
		engine.getRunner().run();
	}
}
