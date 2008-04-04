package org.eclipse.dltk.ruby.fastdebugger;

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

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.MessageFormat;

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
			throw new CoreException(
					new Status(
							IStatus.ERROR,
							FastDebuggerPlugin.PLUGIN_ID,
							Messages.FastDebuggerRunner_unableToDeployDebuggerSource,
							e));
		}
	}

	public FastDebuggerRunner(IInterpreterInstall install) {
		super(install);
	}

	protected InterpreterConfig addEngineConfig(InterpreterConfig config,
			PreferencesLookupDelegate delegate) throws CoreException {
		if (!(getInstall().getInterpreterInstallType() instanceof RubyGenericInstallType)) {
			throw new DebugException(
					new Status(
							IStatus.ERROR,
							FastDebuggerPlugin.PLUGIN_ID,
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

	public IPath resolveGemsPath(boolean user) {
		IPath gemsPath = null;

		gemsPath = new Path(getInstall().getInstallLocation().getAbsolutePath());
		gemsPath = gemsPath.removeLastSegments(2);

		if (user == true) {
			gemsPath = gemsPath.append("lib/ruby/user-gems/1.8/gems"); //$NON-NLS-1$

			IPath userGemsPathUbuntu = new Path("/var/lib/user-gems/1.8/gems"); //$NON-NLS-1$
			if ((gemsPath.toFile().exists() != true)
					&& (userGemsPathUbuntu.toFile().exists() == true)) {
				gemsPath = userGemsPathUbuntu;
			}
		} else {
			gemsPath = gemsPath.append("lib/ruby/gems/1.8/gems"); //$NON-NLS-1$

			IPath gemsPathUbuntu = new Path("/var/lib/gems/1.8/gems"); //$NON-NLS-1$
			if ((gemsPath.toFile().exists() != true)
					&& (gemsPathUbuntu.toFile().exists() == true)) {
				gemsPath = gemsPathUbuntu;
			}
		}

		return gemsPath;
	}

	private boolean resolveRubyDebugGemExists(boolean userGems) {
		boolean rubyDebugGemExists = false;
		IPath gemsPath = resolveGemsPath(userGems);

		if ((gemsPath != null) && (gemsPath.toFile().exists() == true)) {
			String[] files = gemsPath.toFile().list(new FilenameFilter() {

				public boolean accept(File dir, String name) {
					return name.indexOf('-') != -1
							&& "ruby-debug".equals(name.substring(0, //$NON-NLS-1$
									name.lastIndexOf('-')));
				}

			});
			rubyDebugGemExists = (files.length > 0);
		}

		return rubyDebugGemExists;
	}

	public boolean resolveRubyDebugGemExists() {
		return (resolveRubyDebugGemExists(true) || resolveRubyDebugGemExists(false));
	}

	protected void checkConfig(InterpreterConfig config) throws CoreException {
		super.checkConfig(config);

		if (resolveRubyDebugGemExists() != true) {
			abort(
					MessageFormat
							.format(
									Messages.FastDebuggerRunner_rubyDebugGemDoesntSeemToBeInstalled,
									new Object[] {
											getDebuggingEngine().getName(),
											getInstall().getInstallLocation()
													.getAbsolutePath() }), null);
		}
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
