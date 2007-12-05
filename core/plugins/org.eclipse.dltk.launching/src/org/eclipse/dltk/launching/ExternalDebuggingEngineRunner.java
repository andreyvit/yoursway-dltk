package org.eclipse.dltk.launching;

import java.io.File;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.internal.launching.InterpreterMessages;

public abstract class ExternalDebuggingEngineRunner extends
		DebuggingEngineRunner {

	public ExternalDebuggingEngineRunner(IInterpreterInstall install) {
		super(install);
	}

	protected final InterpreterConfig alterConfig(InterpreterConfig config)
			throws CoreException {
		final File file = getDebuggingEnginePath();

		// Checking debugging engine path
		if (file == null || file.toString().length() == 0) {
			abort(
					InterpreterMessages.errDebuggingEnginePathNotSpecified,
					null,
					ScriptLaunchConfigurationConstants.ERR_DEBUGGING_ENGINE_NOT_CONFIGURED);
		} else if (!file.isFile()) {
			abort(
					InterpreterMessages.errDebuggingEnginePathInvalid,
					null,
					ScriptLaunchConfigurationConstants.ERR_DEBUGGING_ENGINE_NOT_CONFIGURED);
		}

		return alterConfig(config, file.toString());
	}

	protected abstract File getDebuggingEnginePath();

	protected abstract InterpreterConfig alterConfig(InterpreterConfig config,
			String debuggingEnginePath) throws CoreException;
}
