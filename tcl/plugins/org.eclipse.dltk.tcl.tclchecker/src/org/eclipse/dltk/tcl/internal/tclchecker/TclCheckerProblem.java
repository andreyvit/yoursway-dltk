/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.tclchecker;

public class TclCheckerProblem {
	private String source;

	private int lineNumber;

	private TclCheckerProblemDescription description;

	public TclCheckerProblem(String source, int lineNumber, String messageID, String message) {
		this.source = source;
		this.lineNumber = lineNumber;
		this.description = TclCheckerProblemDescription.getProblemDescription(messageID, message);
	}

	public String getFile() {
		return source;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public TclCheckerProblemDescription getDescription() {
		return description;
	}
}
