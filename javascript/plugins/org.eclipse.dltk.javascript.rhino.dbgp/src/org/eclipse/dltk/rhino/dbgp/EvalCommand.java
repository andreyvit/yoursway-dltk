/**
 * 
 */
package org.eclipse.dltk.rhino.dbgp;

import java.util.HashMap;

import org.eclipse.dltk.rhino.dbgp.DBGPDebugger.Command;

final class EvalCommand extends DBGPDebugger.Command {
	/**
	 * 
	 */
	private final DBGPDebugger debugger;

	/**
	 * @param debugger
	 */
	EvalCommand(DBGPDebugger debugger) {
		this.debugger = debugger;
	}

	void parseAndExecute(String command, HashMap options) {
		String value = Base64Helper
				.decodeString((String) options.get("--"));
		DBGPDebugFrame fr = this.debugger.cmanager.getStackFrame(0);
		Object evaluated = fr.eval(value);
		StringBuffer valueBuffer = new StringBuffer();
		this.debugger.printProperty("eval", "eval", evaluated, valueBuffer, 0, true);
		this.debugger.printResponse("<response command=\"eval\"\r\n"
				+ " transaction_id=\"" + options.get("-i")
				+ "\" success=\"1\" " + ">\r\n" + valueBuffer
				+ "</response>\r\n" + "");
	}
}