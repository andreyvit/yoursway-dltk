package org.eclipse.dltk.launching;

import java.io.File;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.internal.launching.DLTKLaunchingPlugin;
import org.eclipse.dltk.internal.launching.InterpreterMessages;

public abstract class ExternalDebuggingEngineRunner extends
		DebuggingEngineRunner {

	public ExternalDebuggingEngineRunner(IInterpreterInstall install) {
		super(install);
	}

	protected final InterpreterConfig alterConfig(String exe,
			InterpreterConfig config) throws CoreException {
		final File debugEnginePath = getDebuggingEnginePath();

		// Checking debugging engine path
		IStatus status = null;
		if (debugEnginePath == null) {
			status = new Status(IStatus.ERROR, DLTKLaunchingPlugin.PLUGIN_ID,
					ScriptLaunchConfigurationConstants.ERR_NO_DEBUGGING_ENGINE,
					InterpreterMessages.errDebuggingEnginePathNotSpecified,
					null);
		} else if (!debugEnginePath.isFile()) {
			status = new Status(IStatus.ERROR, DLTKLaunchingPlugin.PLUGIN_ID,
					ScriptLaunchConfigurationConstants.ERR_NO_DEBUGGING_ENGINE,
					InterpreterMessages.errDebuggingEnginePathInvalid, null);
		}

		if (status != null) {
			throw new CoreException(status);
		}

		return alterConfig(exe, config, debugEnginePath.toString());
	}

	protected abstract File getDebuggingEnginePath();

	protected abstract InterpreterConfig alterConfig(String exe,
			InterpreterConfig config, String debuggingEnginePath)
			throws CoreException;
}
