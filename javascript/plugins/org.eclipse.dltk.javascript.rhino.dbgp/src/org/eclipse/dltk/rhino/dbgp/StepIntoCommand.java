/**
 * 
 */
package org.eclipse.dltk.rhino.dbgp;

import java.util.HashMap;

final class StepIntoCommand extends DBGPDebugger.Command {
	/**
	 * 
	 */
	private final DBGPDebugger debugger;

	/**
	 * @param debugger
	 */
	StepIntoCommand(DBGPDebugger debugger) {
		this.debugger = debugger;
	}

	void parseAndExecute(String command, HashMap options) {
		Object tid = options.get("-i");
		this.debugger.runTransctionId = (String) tid;
		if (this.debugger.stackmanager.getStackDepth() > 0) {
			this.debugger.stackmanager.stepIn();
		} else {
			while (!this.debugger.isInited) {
				Thread.yield();
			}
			synchronized (this.debugger) {
				this.debugger.notify();
			}
			this.debugger.stackmanager.resume();
		}
	}
}