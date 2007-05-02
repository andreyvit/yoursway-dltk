/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.parsers.raw;

public class ErrorDescription {

	public static final int ERROR = 0;
	public static final int WARNING = 1;

	private String message;
	private int position;
	private int kind;

	public ErrorDescription(String msg, int pos, int knd) {
		message = msg;
		position = pos;
		kind = knd;
	}

	protected int getKind() {
		return kind;
	}

	protected String getMessage() {
		return message;
	}

	protected int getPosition() {
		return position;
	}

	public String toString() {
		return getMessage() + " at " + getPosition();
	}
}
