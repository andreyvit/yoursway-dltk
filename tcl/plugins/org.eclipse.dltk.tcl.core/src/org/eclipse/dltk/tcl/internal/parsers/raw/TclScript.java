package org.eclipse.dltk.tcl.internal.parsers.raw;

import java.util.ArrayList;
import java.util.List;

public class TclScript extends TclElement {
	
	List commands; 
	
	public TclScript() {		
		commands = new ArrayList();
	}
	
	public void addCommand (TclCommand cmd) {
		commands.add (cmd);
	}
	
	public String getRawText() {
		//TODO
		return null;
	}

	public List getCommands () {
		return commands;
	}
	
}
