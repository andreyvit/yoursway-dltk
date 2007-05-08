/**
 * 
 */
package org.eclipse.dltk.rhino.dbgp;

import java.util.HashMap;

import org.eclipse.dltk.rhino.dbgp.DBGPDebugger.Command;

final class FeatureGetCommand extends DBGPDebugger.Command {
	/**
	 * 
	 */
	private final DBGPDebugger debugger;

	/**
	 * @param debugger
	 */
	FeatureGetCommand(DBGPDebugger debugger) {
		this.debugger = debugger;
	}

	void parseAndExecute(String command, HashMap options) {
		this.debugger.printResponse("<response command=\"feature_get\"\r\n"
				+ "          feature_name=\"supports_async\"\r\n"
				+ "          supported=\"1\"\r\n"
				+ "          transaction_id=\"" + options.get("-i") + "\">"
				+ "1</response>\r\n" + "");
	}
}