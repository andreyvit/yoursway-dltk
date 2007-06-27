package org.eclipse.dltk.rhino.dbgp;

import java.util.HashMap;

import org.eclipse.dltk.rhino.dbgp.DBGPDebugger.Command;

public class GetBreakPointCommand extends Command {

	private DBGPDebugger debugger;

	public GetBreakPointCommand(DBGPDebugger debugger) {
		this.debugger = debugger;
	}

	
	void parseAndExecute(String command, HashMap options) {
		String id = (String) options.get("-d");

		BreakPoint breakpoint = this.debugger.cmanager.getBreakpoint(id);
		this.debugger.printResponse("<response command=\"breakpoint_get\"\r\n"
				+ " transaction_id=\""
				+ options.get("-i")
				+ "\">\r\n"
				+"<breakpoint"
				+ " id=\""
				+ id
				+ "\""
				+ " type=\""
				+ breakpoint.getType()
				+ "\""
				+ " state=\""
				+ breakpoint.getState()
				+ "\""
				+ " filename=\""
				+ breakpoint.file
				+ "\""
				+ " lineno=\""
				+ breakpoint.line
				+ "\""
				+ " function=\""
				+ breakpoint.method
				+ "\""
				+ " exception=\""
				+ "\""
				+ " hit_value=\""
				+ breakpoint.hitValue
				+ "\""
				+ " hit_condition=\""
				+ breakpoint.getHitCondition()
				+ "\""
				+ " hit_count=\""
				+ breakpoint.currentHitCount + "\""
				+" >\r\n"
				+"<expression>"+Base64Helper.encodeString(breakpoint.expression)+"</expression>"
				+ "</breakpoint>\r\n"
				+ "</response>\r\n" + "");
	}

}
