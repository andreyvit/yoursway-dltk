package org.eclipse.dltk.internal.launching.debug;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.dltk.core.DLTKContributedExtension;
import org.eclipse.dltk.debug.core.ScriptDebugManager;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterRunner;
import org.eclipse.dltk.launching.IInterpreterRunnerFactory;
import org.eclipse.dltk.launching.debug.IDebuggingEngine;

public class DebuggingEngine extends DLTKContributedExtension implements
		IDebuggingEngine {

	private IInterpreterRunnerFactory factory;

	public DebuggingEngine(IInterpreterRunnerFactory factory,
			IConfigurationElement config) {
		this.factory = factory;

		/*
		 * this is a cheat - this class contains all the attributes of the
		 * configured extension, so leverage the code DLTKContributedExtension
		 * already provides
		 */
		setInitializationData(config, null, null);
	}

	public String getModelId() {
		return ScriptDebugManager.getInstance().getDebugModelByNature(
				getNatureId());
	}

	public IInterpreterRunner getRunner(IInterpreterInstall install) {
		return factory.createRunner(install);
	}
}
