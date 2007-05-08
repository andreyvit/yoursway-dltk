/**
 * 
 */
package org.eclipse.dltk.rhino.dbgp;

import java.util.HashMap;

import org.eclipse.dltk.rhino.dbgp.DBGPDebugger.Command;

final class PropertySetCommand extends DBGPDebugger.Command {
	/**
	 * 
	 */
	private final DBGPDebugger debugger;

	/**
	 * @param debugger
	 */
	PropertySetCommand(DBGPDebugger debugger) {
		this.debugger = debugger;
	}

	void parseAndExecute(String command, HashMap options) {
		String name = ((String) options.get("-n"));
		int num = Integer.parseInt((String) options.get("-d"));
		String value = Base64Helper
				.decodeString((String) options.get("--"));
		DBGPDebugFrame fr = this.debugger.cmanager.getStackFrame(num);
		fr.setValue(name, value);
		this.debugger.printResponse("<response command=\"property_set\"\r\n"
				+ " transaction_id=\"" + options.get("-i")
				+ "\" success=\"1\" " + ">\r\n" + "</response>\r\n" + "");
	}
}