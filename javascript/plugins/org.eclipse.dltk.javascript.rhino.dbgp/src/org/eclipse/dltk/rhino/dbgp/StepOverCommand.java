/**
 * 
 */
package org.eclipse.dltk.rhino.dbgp;

import java.util.HashMap;

import org.eclipse.dltk.rhino.dbgp.DBGPDebugger.Command;

final class StepOverCommand extends DBGPDebugger.Command {
	/**
	 * 
	 */
	private final DBGPDebugger debugger;

	/**
	 * @param debugger
	 */
	StepOverCommand(DBGPDebugger debugger) {
		this.debugger = debugger;
	}

	void parseAndExecute(String command, HashMap options) {
		Object tid = options.get("-i");
		this.debugger.runTransctionId = (String) tid;
		if (this.debugger.cmanager.getStackDepth() > 0) {
			this.debugger.cmanager.stepOver();
		} else {
			synchronized (this.debugger) {
				this.debugger.notify();
			}
			this.debugger.cmanager.resume();
		}
	}
}