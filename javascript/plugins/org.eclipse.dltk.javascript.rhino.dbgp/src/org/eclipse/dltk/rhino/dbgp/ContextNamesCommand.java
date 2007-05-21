/**
 * 
 */
package org.eclipse.dltk.rhino.dbgp;

import java.util.HashMap;

import org.eclipse.dltk.rhino.dbgp.DBGPDebugger.Command;

final class ContextNamesCommand extends DBGPDebugger.Command {
	/**
	 * 
	 */
	private final DBGPDebugger debugger;

	/**
	 * @param debugger
	 */
	ContextNamesCommand(DBGPDebugger debugger) {
		this.debugger = debugger;
	}

	void parseAndExecute(String command, HashMap options) {
		String object = (String) options.get("-i");
		this.debugger.runTransctionId = object;
		
		this.debugger.printResponse("<response command=\"context_names\"\r\n"				
				+ "          transaction_id=\"" + options.get("-i") + "\">"
				+ "    <context name=\"Local\" id=\"0\"/>\r\n" + 
						"    <context name=\"Global\" id=\"1\"/>\r\n" + 
						"    <context name=\"Class\" id=\"2\"/>\r\n" + 
						""
				+ "</response>\r\n" + "");
	}
}