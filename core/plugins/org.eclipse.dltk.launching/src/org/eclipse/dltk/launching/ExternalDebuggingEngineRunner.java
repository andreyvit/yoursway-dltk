package org.eclipse.dltk.launching;

import java.io.File;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.PreferencesLookupDelegate;
import org.eclipse.dltk.internal.launching.InterpreterMessages;

public abstract class ExternalDebuggingEngineRunner extends
		DebuggingEngineRunner {

	public ExternalDebuggingEngineRunner(IInterpreterInstall install) {
		super(install);
	}

	protected final InterpreterConfig addEngineConfig(InterpreterConfig config,
			PreferencesLookupDelegate delegate) throws CoreException {

		final File file = getDebuggingEnginePath(delegate);

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

	/**
	 * Returns the preference key used to store the external debugging engine
	 * path.
	 */
	protected abstract String getDebuggingEnginePreferenceKey();

	/**
	 * Returns the id of the plugin whose preference store that contains the
	 * debugging engine path.
	 */
	protected abstract String getDebuggingEnginePreferenceQualifier();

	protected File getDebuggingEnginePath(PreferencesLookupDelegate delegate) {
		String key = getDebuggingEnginePreferenceKey();
		String qualifier = getDebuggingEnginePreferenceQualifier();
		
		String path = delegate.getString(qualifier, key);
		if (path != null) {
			return new File(path);
		}

		return null;
	}

	protected abstract InterpreterConfig alterConfig(InterpreterConfig config,
			String debuggingEnginePath) throws CoreException;
}
