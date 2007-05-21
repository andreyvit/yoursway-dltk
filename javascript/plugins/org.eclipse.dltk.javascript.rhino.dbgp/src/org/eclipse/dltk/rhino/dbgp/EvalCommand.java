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
		StringBuffer valueBuffer = new StringBuffer();
		if (this.debugger.cmanager.getStackDepth()==0){
			this.debugger.printProperty(value, value, "", valueBuffer, 0, true);
			this.debugger.printResponse("<response command=\"eval\"\r\n"
					+ " transaction_id=\"" + options.get("-i")
					+ "\" success=\"1\" " + ">\r\n" + valueBuffer
					+ "</response>\r\n" + "");
			return;
		}
		DBGPDebugFrame fr = this.debugger.cmanager.getStackFrame(0);
		Object evaluated = fr.eval(value);
		String shName=value;
		int k=shName.lastIndexOf('.');
		if (k!=-1)
		{
		shName=shName.substring(k+1);
		}
		this.debugger.printProperty(shName, value, evaluated, valueBuffer, 0, true);
		this.debugger.printResponse("<response command=\"eval\"\r\n"
				+ " transaction_id=\"" + options.get("-i")
				+ "\" success=\"1\" " + ">\r\n" + valueBuffer
				+ "</response>\r\n" + "");
	}
}