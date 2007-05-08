/**
 * 
 */
package org.eclipse.dltk.rhino.dbgp;

import java.util.HashMap;

import org.eclipse.dltk.rhino.dbgp.DBGPDebugger.Command;

final class StdInCommand extends DBGPDebugger.Command {
	/**
	 * 
	 */
	private final DBGPDebugger debugger;

	/**
	 * @param debugger
	 */
	StdInCommand(DBGPDebugger debugger) {
		this.debugger = debugger;
	}

	void parseAndExecute(String command, HashMap options) {
		this.debugger.printResponse("<response command=\"srdin\"\r\n"
				+ "          success=\"1\"\r\n"
				+ "          transaction_id=\"" + options.get("-i")
				+ "\">\r\n" + "</response>\r\n" + "");
	}
}