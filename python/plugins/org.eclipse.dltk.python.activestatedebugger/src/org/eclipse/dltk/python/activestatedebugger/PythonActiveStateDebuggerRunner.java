package org.eclipse.dltk.python.activestatedebugger;

import java.io.File;

import org.eclipse.dltk.launching.ExternalDebuggingEngineRunner;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.InterpreterConfig;
import org.eclipse.dltk.launching.debug.DbgpInterpreterConfig;

/**
 * Debugging engine implementation for ActiveState's python debugging engine.
 * 
 * <p>
 * This implementation does not require you to install the python debugging
 * engine as described in the install documentation. Instead, the path to the
 * debuggign engine will be passed to the python interpreter.
 * </p>
 * 
 * <p>
 * see: <a
 * href="http://aspn.activestate.com/ASPN/docs/Komodo/4.1/komodo-doc-debugpython.html">
 * http://aspn.activestate.com/ASPN/docs/Komodo/4.1/komodo-doc-debugpython.html</a>
 * </p>
 */
public class PythonActiveStateDebuggerRunner extends
		ExternalDebuggingEngineRunner {

	public static final String ENGINE_ID = "org.eclipse.dltk.python.activestatedebugger";

	public PythonActiveStateDebuggerRunner(IInterpreterInstall install) {
		super(install);
	}

	/*
	 * @see org.eclipse.dltk.launching.ExternalDebugginEngineRunner#alterConfig(java.lang.String,
	 *      org.eclipse.dltk.launching.InterpreterConfig, java.lang.String)
	 */
	protected InterpreterConfig alterConfig(InterpreterConfig config,
			String debugEnginePath) {

		DbgpInterpreterConfig dbgpConfig = new DbgpInterpreterConfig(config);
		final String host = dbgpConfig.getHost();
		final int port = dbgpConfig.getPort();
		final String sessionId = dbgpConfig.getSessionId();

		// python -S path/to/pydbgp.py -d host:port -k ide_key your-script.py
		config.addInterpreterArg("-S");
		config.addInterpreterArg(debugEnginePath);
		config.addInterpreterArg("-d");
		config.addInterpreterArg(host + ":" + port);
		config.addInterpreterArg("-k");
		config.addInterpreterArg(sessionId);

		return config;
	}

	/*
	 * @see org.eclipse.dltk.launching.ExternalDebugginEngineRunner#getDebuggingEnginePath()
	 */
	protected File getDebuggingEnginePath() {
		String path = PythonActiveStateDebuggerPlugin
				.getDefault()
				.getPreferenceStore()
				.getString(
						PythonActiveStateDebuggerConstants.DEBUGGING_ENGINE_PATH_KEY);

		if (path != null) {
			return new File(path);
		}

		return null;
	}

	/*
	 * @see org.eclipse.dltk.launching.DebuggingEngineRunner#getDebuggingEngineId()
	 */
	protected String getDebuggingEngineId() {
		return ENGINE_ID;
	}

}
