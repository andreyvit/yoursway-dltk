package org.eclipse.dltk.tcl.internal.launching;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.launching.AbstractInterpreterDebugger;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.tcl.internal.debug.TclDebugPreferences;
import org.eclipse.dltk.tcl.launching.TclLaunchingPlugin;

public class TclInterpreterDebugger extends AbstractInterpreterDebugger {
	private static final String HOST_KEY = "-host-ide";
	private static final String PORT_KEY = "-port-ide";
	private static final String SHELL_KEY = "-app-shell";
	private static final String IDE_KEY = "-ide-key";
	private static final String SCRIPT_KEY = "-app-file";
	private static final String ARGS_SEPARATOR = "--";

	protected String getPluginIdentifier() {
		return TclLaunchingPlugin.PLUGIN_ID;
	}

	protected File getEngine() throws CoreException {
		File engine = new File(TclDebugPreferences.getDebuggingEnginePath());
		// TODO: add more checks
		if (!engine.isFile()) {
			abort("Tcl debugging engine not configured", null, 180);
		}
		return engine;
	}

	public TclInterpreterDebugger(IInterpreterInstall install) {
		super(install);
	}

	protected String[] getCommandLine(String id, String host, int port,
			String script, String[] args, String shell) throws CoreException {

		List list = new ArrayList();

		list.add(getEngine().getAbsolutePath());
		list.add(HOST_KEY);
		list.add(host);
		list.add(PORT_KEY);
		list.add(Integer.toString(port));
		list.add(SHELL_KEY);
		list.add(shell);
		list.add(IDE_KEY);
		list.add(id);
		list.add(SCRIPT_KEY);
		list.add(script);
		list.add(ARGS_SEPARATOR);
		for (int i = 0; i < args.length; ++i) {
			list.add(args[i]);
		}

		return (String[]) list.toArray(new String[list.size()]);
	}

	protected File getWorkingDir() throws CoreException {
		return getEngine().getParentFile();
	}
}
