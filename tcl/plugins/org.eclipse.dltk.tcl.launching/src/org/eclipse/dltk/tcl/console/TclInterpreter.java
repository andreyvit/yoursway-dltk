/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.console;

import java.io.IOException;
import java.util.List;

import org.eclipse.dltk.console.ConsoleRequest;
import org.eclipse.dltk.console.IScriptConsoleIO;
import org.eclipse.dltk.console.IScriptInterpreter;
import org.eclipse.dltk.console.InterpreterResponse;
import org.eclipse.dltk.console.ShellResponse;


public class TclInterpreter implements IScriptInterpreter, ConsoleRequest {

	private static final String COMPLETE_COMMAND = "complete";

	private static final String DESCRIBE_COMMAND = "describe";
	
	private static final String CLOSE_COMMAND = "close";

	private IScriptConsoleIO protocol;

	private String content;

	private int state;

	// IScriptInterpreter
	public void exec(String command) throws IOException {
		InterpreterResponse response = protocol.execInterpreter(command);

		content = response.getContent();
		state = response.getState();
	}

	public String getOutput() {
		return content;
	}

	public int getState() {
		return state;
	}

	// IScriptInterpreterShell
	public List getCompletions(String commandLine, int position)
			throws IOException {

		String[] args = new String[] { commandLine, Integer.toString(position) };

		ShellResponse response = protocol.execShell(COMPLETE_COMMAND, args);

		return response.getCompletions();
	}

	public String getDescription(String commandLine, int position)
			throws IOException {
		String[] args = new String[] { commandLine, Integer.toString(position) };

		ShellResponse response = protocol.execShell(DESCRIBE_COMMAND, args);

		return response.getDescription();
	}

	public String[] getNames(String type) throws IOException {

		return null;
	}

	public void close() throws IOException {
		protocol.execShell(CLOSE_COMMAND, new String[] {});
		protocol.close();
	}

	// IScriptConsoleProtocol
	public void consoleConnected(IScriptConsoleIO protocol) {
		this.protocol = protocol;
	}
}
