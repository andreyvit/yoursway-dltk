/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.console;

public class ScriptConsoleCompletionProposal {
	private String insert;

	private String display;

	private String type;

	public ScriptConsoleCompletionProposal(String insert, String display, String type) {
		this.insert = insert;
		this.display = display;
		this.type = type;
	}

	public String getDisplay() {
		return display;
	}

	public String getInsert() {
		return insert;
	}

	public String getType() {
		return type;
	}
}
