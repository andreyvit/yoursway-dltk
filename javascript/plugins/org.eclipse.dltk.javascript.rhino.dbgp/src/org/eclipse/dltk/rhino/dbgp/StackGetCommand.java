/**
 * 
 */
package org.eclipse.dltk.rhino.dbgp;

import java.io.File;
import java.util.HashMap;

import org.eclipse.dltk.rhino.dbgp.DBGPDebugger.Command;

final class StackGetCommand extends DBGPDebugger.Command {
	/**
	 * 
	 */
	private final DBGPDebugger debugger;

	/**
	 * @param debugger
	 */
	StackGetCommand(DBGPDebugger debugger) {
		this.debugger = debugger;
	}

	void parseAndExecute(String command, final HashMap options) {
		int level = Integer.parseInt((String) options.get("-d"));
		StringBuffer stack = new StringBuffer();
		if (this.debugger.cmanager.getStackDepth() >= level) {
			DBGPDebugFrame stackFrame = this.debugger.cmanager.getStackFrame(level);

			stack.append("<stack level=\""
					+ level
					+ "\"\r\n"
					+ "           type=\"file\"\r\n"
					+ "           filename=\""
					+ new File(stackFrame.getSourceName()).toURI()
							.toASCIIString() + "\"\r\n"
					+ "           lineno=\""
					+ (stackFrame.getLineNumber() + 1) + "\"\r\n"
					+ "           where=\"" + stackFrame.getWhere()
					+ "\"\r\n" + "           cmdbegin=\"1:0\"\r\n"
					+ "           cmdend=\"" + "1" + ":10\"/>");
			this.debugger.printResponse("<response command=\"stack_get\"\r\n" + "\r\n"
					+ "          transaction_id=\"" + options.get("-i")
					+ "\">\r\n" +

					stack + "</response>\r\n" + "");
		}

	}
}