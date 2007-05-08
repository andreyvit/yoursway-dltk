/**
 * 
 */
package org.eclipse.dltk.rhino.dbgp;

import java.util.HashMap;

import org.eclipse.dltk.rhino.dbgp.DBGPDebugger.Command;

final class RemoveBreakPointCommand extends DBGPDebugger.Command {
	/**
	 * 
	 */
	private final DBGPDebugger debugger;

	/**
	 * @param debugger
	 */
	RemoveBreakPointCommand(DBGPDebugger debugger) {
		this.debugger = debugger;
	}

	void parseAndExecute(String command, HashMap options) {
		this.debugger.cmanager.removeBreakpoint((String) options.get("-d"));
		this.debugger.printResponse("<response command=\"breakpoint_remove\"\r\n"
				+ " transaction_id=\"" + options.get("-i") + "\" />");
	}
}