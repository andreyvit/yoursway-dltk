/**
 * 
 */
package org.eclipse.dltk.rhino.dbgp;

import java.util.HashMap;

import org.eclipse.dltk.rhino.dbgp.DBGPDebugger.Command;

final class FeatureSetCommand extends DBGPDebugger.Command {
	/**
	 * 
	 */
	private final DBGPDebugger debugger;

	/**
	 * @param debugger
	 */
	FeatureSetCommand(DBGPDebugger debugger) {
		this.debugger = debugger;
	}

	void parseAndExecute(String command, HashMap options) {
		this.debugger.printResponse("<response command=\"feature_set\"\r\n"
				+ "          feature_name=\"max_children\"\r\n"
				+ "          success=\"1\"\r\n"
				+ "          transaction_id=\"" + options.get("-i")
				+ "\">\r\n" + "</response>\r\n" + "");
	}
}