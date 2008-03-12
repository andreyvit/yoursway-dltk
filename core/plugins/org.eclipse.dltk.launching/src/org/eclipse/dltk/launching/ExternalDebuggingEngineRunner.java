package org.eclipse.dltk.launching;

import java.io.File;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.PreferencesLookupDelegate;
import org.eclipse.dltk.internal.launching.InterpreterMessages;
import org.eclipse.dltk.utils.PlatformFileUtils;

public abstract class ExternalDebuggingEngineRunner extends
		DebuggingEngineRunner {

	public ExternalDebuggingEngineRunner(IInterpreterInstall install) {
		super(install);
	}

	protected final InterpreterConfig addEngineConfig(InterpreterConfig config,
			PreferencesLookupDelegate delegate) throws CoreException {

		final File file = PlatformFileUtils
				.findAbsoluteOrEclipseRelativeFile(getDebuggingEnginePath(delegate));

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

		return alterConfig(config, delegate);
	}

	/**
	 * Returns the preference key used to store the external debugging engine
	 * path.
	 */
	protected abstract String getDebuggingEnginePreferenceKey();

//	/**
//	 * Returns the id of the plugin whose preference store that contains the
//	 * debugging engine path.
//	 */
//	protected abstract String getDebuggingEnginePreferenceQualifier();

	
	protected File getDebuggingEnginePath(PreferencesLookupDelegate delegate) {
		String key = getDebuggingEnginePreferenceKey();
		String qualifier = getDebuggingEnginePreferenceQualifier();

		String path = delegate.getString(qualifier, key);
		if (!(path == null && "".equals(path))) { //$NON-NLS-1$
			return PlatformFileUtils
					.findAbsoluteOrEclipseRelativeFile(new File(path));
		}

		return null;
	}
	
	protected String getDebuggingPreference(PreferencesLookupDelegate delegate, String key) {
		String qualifier = getDebuggingEnginePreferenceQualifier();
		return delegate.getString(qualifier, key);
	}

	protected abstract InterpreterConfig alterConfig(InterpreterConfig config,
			PreferencesLookupDelegate delegate) throws CoreException;
}
