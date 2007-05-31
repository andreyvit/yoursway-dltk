package org.eclipse.dltk.tcl.launching.debug;

import java.io.File;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.launching.IInterpreterConfigModifier;
import org.eclipse.dltk.launching.InterpreterConfig;
import org.eclipse.dltk.launching.debug.DbgpConstants;
import org.eclipse.dltk.tcl.internal.debug.TclDebugPreferences;
import org.eclipse.dltk.tcl.launching.TclLaunchingPlugin;

public class ActiveStateDebuggerConfigModifier implements
		IInterpreterConfigModifier {
	private static final String HOST_KEY = "-host-ide";
	private static final String PORT_KEY = "-port-ide";
	private static final String SHELL_KEY = "-app-shell";
	private static final String IDE_KEY = "-ide-key";
	private static final String SCRIPT_KEY = "-app-file";
	private static final String ARGS_SEPARATOR = "--";

	protected File getEngine() throws CoreException {
		File engine = new File(TclDebugPreferences.getDebuggingEnginePath()); // TODO:
		if (!engine.isFile()) {
			throw new CoreException(new Status(IStatus.ERROR,
					TclLaunchingPlugin.PLUGIN_ID, 180,
					"Tcl debugging engine not configured", null));
		}
		return engine;
	}

	public ActiveStateDebuggerConfigModifier() {
	}

	public InterpreterConfig modify(String exe, InterpreterConfig config)
			throws CoreException {
		String host = (String) config.getProperty(DbgpConstants.HOST_PROP);
		String port = (String) config.getProperty(DbgpConstants.PORT_PROP);
		String sessionId = (String) config
				.getProperty(DbgpConstants.SESSION_ID_PROP);

		InterpreterConfig newConfig = new InterpreterConfig();

		newConfig.setProperty("OVERRIDE_EXE", getEngine().toString());

		newConfig.addInterpreterArg(HOST_KEY);
		newConfig.addInterpreterArg(host);

		newConfig.addInterpreterArg(PORT_KEY);
		newConfig.addInterpreterArg(port);

		newConfig.addInterpreterArg(SHELL_KEY);
		newConfig.addInterpreterArg(exe);

		newConfig.addInterpreterArg(IDE_KEY);
		newConfig.addInterpreterArg(sessionId);

		newConfig.addInterpreterArg(SCRIPT_KEY);

		newConfig.setScriptFile(config.getScriptFile());

		newConfig.addScriptArg(ARGS_SEPARATOR);
		newConfig.addScriptArgs(config.getScriptArgs());

		return newConfig;
	}
}
