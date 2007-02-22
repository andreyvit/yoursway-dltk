package org.eclipse.dltk.tcl.console;

import org.eclipse.dltk.console.ScriptConsoleProxy;
import org.eclipse.dltk.tcl.launching.TclLaunchingPlugin;

public class TclConsoleProxy extends ScriptConsoleProxy {
	private static String TCL_PROXY = "ConsoleProxy.tcl";

	private static String TCL_LANGUAGE = "tcl";

	private static TclConsoleProxy instance;

	public static TclConsoleProxy getInstance() {
		if (instance == null) {
			instance = new TclConsoleProxy();
		}
		return instance;
	}

	protected TclConsoleProxy() {
		super(TclLaunchingPlugin.getDefault().getBundle().getEntry(TCL_PROXY),
				TCL_LANGUAGE);
	}
}
