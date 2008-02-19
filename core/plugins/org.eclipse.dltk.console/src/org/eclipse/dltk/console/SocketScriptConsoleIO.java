/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.console;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketScriptConsoleIO implements IScriptConsoleIO {
	private final Socket socket;

	private final IScriptConsoleIO io;

	public SocketScriptConsoleIO(Socket socket) throws IOException {
		this.socket = socket;

		BufferedInputStream input = new BufferedInputStream(socket
				.getInputStream());

		BufferedOutputStream output = new BufferedOutputStream(socket
				.getOutputStream());

		io = new ScriptConsoleIO(input, output);
	}

	public String getId() {
		return io.getId();
	}

	public InterpreterResponse execInterpreter(String command)
			throws IOException {
		return io.execInterpreter(command);
	}

	public ShellResponse execShell(String command, String[] args)
			throws IOException {
		return io.execShell(command, args);
	}

	public void close() throws IOException {
		io.close();
		socket.close();
	}
	public String getInitialResponse() {
		return io.getInitialResponse();
	}
}
