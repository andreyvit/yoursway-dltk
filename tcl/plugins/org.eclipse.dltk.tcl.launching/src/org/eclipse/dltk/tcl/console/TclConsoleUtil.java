package org.eclipse.dltk.tcl.console;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.dltk.console.ScriptConsoleServer;
import org.eclipse.dltk.launching.DLTKLaunchUtil;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.tcl.core.TclNature;


public class TclConsoleUtil {
	public static void runDefaultTclInterpreter(TclInterpreter interpreter)
			throws CoreException, IOException {
		IInterpreterInstall install = DLTKLaunchUtil
				.getDefaultInterpreterInstall(TclNature.NATURE_ID);

		String proxyFilePath = TclConsoleProxy.getInstance().getFile()
				.getAbsolutePath();

		ScriptConsoleServer server = ScriptConsoleServer.getInstance();

		String id = server.register(interpreter);
		String port =  Integer.toString(server.getPort());

		String[] args = new String[] { "localhost", port, id };

		DLTKLaunchUtil.launchScript(install, proxyFilePath,
				args, ILaunchManager.RUN_MODE);
	}
}
