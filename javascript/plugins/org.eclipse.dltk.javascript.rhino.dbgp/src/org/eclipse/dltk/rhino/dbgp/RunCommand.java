/**
 * 
 */
package org.eclipse.dltk.rhino.dbgp;

import java.util.HashMap;

import org.eclipse.dltk.rhino.dbgp.DBGPDebugger.Command;

final class RunCommand extends DBGPDebugger.Command {
	/**
	 * 
	 */
	private final DBGPDebugger debugger;

	/**
	 * @param debugger
	 */
	RunCommand(DBGPDebugger debugger) {
		this.debugger = debugger;
	}

	void parseAndExecute(String command, HashMap options) {
		String object = (String) options.get("-i");
		this.debugger.runTransctionId = object;
		synchronized (this.debugger) {
			this.debugger.notify();
		}
		this.debugger.cmanager.resume();
		// printResponse("<response command=\"run\"\r\n"
		// + "status=\"starting\"" + " reason=\"ok\""
		// + " transaction_id=\"" + object + "\">\r\n"
		// + "</response>\r\n" + "");
	}
}