/**
 * 
 */
package org.eclipse.dltk.rhino.dbgp;

import java.util.HashMap;

import org.eclipse.dltk.rhino.dbgp.DBGPDebugger.Command;

final class BreakCommand extends DBGPDebugger.Command {
	/**
	 * 
	 */
	private final DBGPDebugger debugger;

	/**
	 * @param debugger
	 */
	BreakCommand(DBGPDebugger debugger) {
		this.debugger = debugger;
	}

	void parseAndExecute(String command, final HashMap options) {
		this.debugger.stackmanager.suspend();
		this.debugger.printResponse("<response command=\"break\"\r\n"
				+ "          success=\"1\"\r\n"
				+ "          transaction_id=\"" + options.get("-i")
				+ "\">\r\n" + "</response>\r\n" + "");

	}
}