package org.eclipse.dltk.dbgp.internal.packets;

public class DbgpLogger implements IDbgpLogger {

	public void logInput(String input) {
		System.out.println("<<< " + input);
	}

	public void logOutput(String output) {
		System.out.println(">>> " + output);
	}
}
