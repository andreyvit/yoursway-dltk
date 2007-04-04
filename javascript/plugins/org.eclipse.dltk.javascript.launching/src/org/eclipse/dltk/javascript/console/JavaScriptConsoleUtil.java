package org.eclipse.dltk.javascript.console;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.dltk.console.ScriptConsoleServer;
import org.eclipse.dltk.javascript.core.JavaScriptNature;
import org.eclipse.dltk.launching.DLTKLaunchUtil;
import org.eclipse.dltk.launching.IInterpreterInstall;

public class JavaScriptConsoleUtil {
	public static void runDefaultTclInterpreter(JavaScriptInterpreter interpreter)
			throws CoreException, IOException {
		IInterpreterInstall install = DLTKLaunchUtil
				.getDefaultInterpreterInstall(JavaScriptNature.NATURE_ID);

		String proxyFilePath = JavaScriptConsoleProxy.getInstance().getFile()
				.getAbsolutePath();

		ScriptConsoleServer server = ScriptConsoleServer.getInstance();

		String id = server.register(interpreter);
		String port = Integer.toString(server.getPort());

		String[] args = new String[] { "localhost", port, id };

		DLTKLaunchUtil.launchScript(install, proxyFilePath, args,
				ILaunchManager.RUN_MODE);
	}
}
