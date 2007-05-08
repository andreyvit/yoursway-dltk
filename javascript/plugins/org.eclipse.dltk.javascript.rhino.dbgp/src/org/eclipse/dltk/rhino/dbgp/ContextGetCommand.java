/**
 * 
 */
package org.eclipse.dltk.rhino.dbgp;

import java.util.HashMap;

import org.eclipse.dltk.rhino.dbgp.DBGPDebugger.Command;
import org.mozilla.javascript.Scriptable;

final class ContextGetCommand extends DBGPDebugger.Command {
	/**
	 * 
	 */
	private final DBGPDebugger debugger;

	/**
	 * @param debugger
	 */
	ContextGetCommand(DBGPDebugger debugger) {
		this.debugger = debugger;
	}

	void parseAndExecute(String command, HashMap options) {
		StringBuffer properties = new StringBuffer();
		int level = Integer.parseInt((String) options.get("-d"));
		DBGPDebugFrame stackFrame = this.debugger.cmanager.getStackFrame(level);
		String[] propertyIds = stackFrame.getParametersAndVars();
		for (int a = 0; a < propertyIds.length; a++) {
			String id = propertyIds[a].toString();
			Object value = stackFrame.getValue(a);
			this.debugger.printProperty(id, id, value, properties, 0, true);

		}
		Scriptable this1 = stackFrame.getThis();
		if (this1 != null) {
			String id = "this";
			this.debugger.printProperty(id, id, this1, properties, 0, false);
		}
		this.debugger.printResponse("<response command=\"context_get\"\r\n"
				+ "status=\"starting\"" + " reason=\"ok\""
				+ " transaction_id=\"" + options.get("-i") + "\">\r\n"
				+ properties + "</response>\r\n" + "");
	}
}