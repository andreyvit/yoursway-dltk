/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.console;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ScriptConsoleIO implements IScriptConsoleIO {

	private static final String INTERPRETER = "interpreter";

	private static final String SHELL = "shell";

	private final InputStream input;

	private final OutputStream output;

	private final String id;
	
	private String initialResponse = null;

	protected static void logInterpreterResponse(String response) {
//		System.out.println("interpreter: " + response);
	}

	protected static void logShellResponse(String response) {
//		System.out.println("shell: " + response);
	}

	protected static String readFixed(int len, InputStream input)
			throws IOException {
		byte[] buffer = new byte[len];
		int from = 0;
		while (from < buffer.length) {
			int n = input.read(buffer, from, buffer.length - from);
			if (n == -1) {
				return null;
			}
			from += n;
		}
		return new String(buffer);
	}

	protected static int readLength(InputStream input) throws IOException {
		String strLen = readFixed(10, input);
		if (strLen == null) {
			return -1;
		}

		return Integer.parseInt(strLen);
	}

	protected static String readResponse(InputStream input) throws IOException {
		int len = readLength(input);
		if (len == -1) {
			return null;
		}

		String xml = readFixed(len, input);

		if (xml == null) {
			return null;
		}

		return xml;
	}

	public ScriptConsoleIO(InputStream input, OutputStream output)
			throws IOException {
		if (input == null || output == null) {
			throw new IllegalArgumentException();
		}

		this.input = input;
		this.output = output;

		this.id = ScriptConsoleXmlHelper.parseInfoXml(readResponse(input));
		// read all input from console
//		String readResponse = readResponse(input);
//		while (readResponse != null) {
//			try {
//				InterpreterResponse xml = ScriptConsoleXmlHelper
//						.parseInterpreterXml(readResponse);
//				String content = xml.getContent();
//				if (content.startsWith("%%{{START_OF_SCRIPT}}&&")) {
//					break;
//				}
//				if( initialResponse == null ) {
//					initialResponse = content;
//				}
//				else {
//					initialResponse += content;
//				}
////				this.output.write(content.getBytes());
////				this.output.flush();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			readResponse = readResponse(input);
//		}

	}
	public String getInitialResponse() {
		return this.initialResponse;
	}

	public String getId() {
		return id;
	}

	public ShellResponse execShell(String command, String[] args)
			throws IOException {

		output.write((SHELL + "\n").getBytes());
		output.write((command + "\n").getBytes());

		for (int i = 0; i < args.length; ++i) {
			output.write((args[i] + "\n").getBytes());
		}

		output.flush();

		final String response = readResponse(input);
		logShellResponse(response);
		return ScriptConsoleXmlHelper.parseShellXml(response);
	}

	public InterpreterResponse execInterpreter(String command)
			throws IOException {
		output.write((INTERPRETER + "\n").getBytes());
		output.write((command + "\n").getBytes());
		output.flush();

		final String response = readResponse(input);
		logInterpreterResponse(response);
		return ScriptConsoleXmlHelper.parseInterpreterXml(response);
	}

	public void close() throws IOException {
		input.close();
		output.close();
	}
}
