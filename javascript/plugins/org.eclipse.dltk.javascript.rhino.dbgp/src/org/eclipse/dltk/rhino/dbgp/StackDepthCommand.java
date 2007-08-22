/**
 * 
 */
package org.eclipse.dltk.rhino.dbgp;

import java.util.HashMap;

import org.eclipse.dltk.rhino.dbgp.DBGPDebugger.Command;

final class StackDepthCommand extends DBGPDebugger.Command {
	/**
	 * 
	 */
	private final DBGPDebugger debugger;

	/**
	 * @param debugger
	 */
	StackDepthCommand(DBGPDebugger debugger) {
		this.debugger = debugger;
	}

	void parseAndExecute(String command, final HashMap options) {
		this.debugger.printResponse("<response command=\"stack_depth\"\r\n"
				+ "          depth=\"" + (this.debugger.stackmanager.getStackDepth())
				+ "\"\r\n" + "          transaction_id=\""
				+ options.get("-i") + "\">\r\n" + "</response>\r\n" + "");

	}
}