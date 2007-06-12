/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
/**
 * 
 */
package org.eclipse.dltk.console;

public class ScriptConsolePrompt {
	private final String newCommand;

	private final String continueCommand;

	private boolean mode;

	public ScriptConsolePrompt(String newCommand, String appendCommand) {
		this.newCommand = newCommand;
		this.continueCommand = appendCommand;
		this.mode = true;
	}

	public void setMode(boolean mode) {
		this.mode = mode;
	}

	public String toString() {
		return mode ? newCommand : continueCommand;
	}
}