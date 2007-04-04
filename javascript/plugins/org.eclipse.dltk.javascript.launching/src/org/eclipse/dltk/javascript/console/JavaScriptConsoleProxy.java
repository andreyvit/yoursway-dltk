package org.eclipse.dltk.javascript.console;

import org.eclipse.dltk.console.ScriptConsoleProxy;
import org.eclipse.dltk.javascript.launching.JavaScriptLaunchingPlugin;

public class JavaScriptConsoleProxy extends ScriptConsoleProxy {
	private static String TCL_PROXY = "ConsoleProxy.tcl";

	private static String TCL_LANGUAGE = "tcl";

	private static JavaScriptConsoleProxy instance;

	public static JavaScriptConsoleProxy getInstance() {
		if (instance == null) {
			instance = new JavaScriptConsoleProxy();
		}
		return instance;
	}

	protected JavaScriptConsoleProxy() {
		super(JavaScriptLaunchingPlugin.getDefault().getBundle().getEntry(TCL_PROXY),
				TCL_LANGUAGE);
	}
}
