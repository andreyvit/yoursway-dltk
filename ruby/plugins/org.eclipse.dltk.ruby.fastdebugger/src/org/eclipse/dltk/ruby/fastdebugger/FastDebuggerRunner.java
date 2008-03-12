package org.eclipse.dltk.ruby.fastdebugger;

import java.io.IOException;
import java.text.MessageFormat;

import org.eclipse.core.internal.resources.File;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugException;
import org.eclipse.dltk.core.PreferencesLookupDelegate;
import org.eclipse.dltk.launching.DebuggingEngineRunner;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.InterpreterConfig;
import org.eclipse.dltk.launching.debug.DbgpInterpreterConfig;
import org.eclipse.dltk.ruby.debug.RubyDebugPlugin;
import org.eclipse.dltk.ruby.internal.launching.RubyGenericInstallType;

public class FastDebuggerRunner extends DebuggingEngineRunner {
	public static final String ENGINE_ID = "org.eclipse.dltk.ruby.fastdebugger"; //$NON-NLS-1$

	private static final String RUBY_HOST_VAR = "DBGP_RUBY_HOST"; //$NON-NLS-1$
	private static final String RUBY_PORT_VAR = "DBGP_RUBY_PORT"; //$NON-NLS-1$
	private static final String RUBY_KEY_VAR = "DBGP_RUBY_KEY"; //$NON-NLS-1$
	private static final String RUBY_LOG_VAR = "DBGP_RUBY_LOG"; //$NON-NLS-1$

	private static final String DEBUGGER_SCRIPT = "FastRunner.rb"; //$NON-NLS-1$

	protected IPath deploy() throws CoreException {
		try {
			return FastDebuggerPlugin.getDefault().deployDebuggerSource();
		} catch (IOException e) {
			// TODO: add code for handler
			throw new CoreException(new Status(IStatus.ERROR,
					FastDebuggerPlugin.PLUGIN_ID,
					Messages.FastDebuggerRunner_unableToDeployDebuggerSource, e));
		}
	}

	public FastDebuggerRunner(IInterpreterInstall install) {
		super(install);
	}

	protected InterpreterConfig addEngineConfig(InterpreterConfig config,
			PreferencesLookupDelegate delegate) throws CoreException {
		if (!(getInstall().getInterpreterInstallType() instanceof RubyGenericInstallType)) {
			throw new DebugException(
					new Status(IStatus.ERROR, FastDebuggerPlugin.PLUGIN_ID,
							Messages.FastDebuggerRunner_fastDebuggerCanOnlyBeRunWithGenericRubyInterpreter));
		}
		// Get debugger source location
		final IPath sourceLocation = deploy();

		final IPath scriptFile = sourceLocation.append(DEBUGGER_SCRIPT);
		
		// Creating new config
		InterpreterConfig newConfig = (InterpreterConfig) config.clone();
		newConfig.addInterpreterArg("-r" + scriptFile.toPortableString()); //$NON-NLS-1$
		newConfig.addInterpreterArg("-I" + sourceLocation.toPortableString()); //$NON-NLS-1$

		// Environment
		final DbgpInterpreterConfig dbgpConfig = new DbgpInterpreterConfig(
				config);

		newConfig.addEnvVar(RUBY_HOST_VAR, dbgpConfig.getHost());
		newConfig.addEnvVar(RUBY_PORT_VAR, Integer.toString(dbgpConfig
				.getPort()));

		String sessionId = dbgpConfig.getSessionId();
		newConfig.addEnvVar(RUBY_KEY_VAR, sessionId);

		if (isLoggingEnabled(delegate)) {
			newConfig.addEnvVar(RUBY_LOG_VAR, getLogFileName(delegate,
					sessionId).getAbsolutePath());
		}

		return newConfig;
	}

	protected String getDebuggingEngineId() {
		return ENGINE_ID;
	}

	/*
	 * @see org.eclipse.dltk.launching.DebuggingEngineRunner#getDebugPreferenceQualifier()
	 */
	protected String getDebugPreferenceQualifier() {
		return RubyDebugPlugin.PLUGIN_ID;
	}

	/*
	 * @see org.eclipse.dltk.launching.DebuggingEngineRunner#getDebuggingEnginePreferenceQualifier()
	 */
	protected String getDebuggingEnginePreferenceQualifier() {
		return FastDebuggerPlugin.PLUGIN_ID;
	}

	/*
	 * @see org.eclipse.dltk.launching.DebuggingEngineRunner#getLoggingEnabledPreferenceKey()
	 */
	protected String getLoggingEnabledPreferenceKey() {
		return FastDebuggerConstants.ENABLE_LOGGING;
	}

	/*
	 * @see org.eclipse.dltk.launching.DebuggingEngineRunner#getLogFileNamePreferenceKey()
	 */
	protected String getLogFileNamePreferenceKey() {
		return FastDebuggerConstants.LOG_FILE_NAME;
	}

	/*
	 * @see org.eclipse.dltk.launching.DebuggingEngineRunner#getLogFilePathPreferenceKey()
	 */
	protected String getLogFilePathPreferenceKey() {
		return FastDebuggerConstants.LOG_FILE_PATH;
	}
}
