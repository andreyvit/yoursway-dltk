/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.parsers.raw;

public class MagicBackslashSubstitution extends TclElement implements
		ISubstitution {

	public static boolean iAm(CodeScanner input) {
		int c = input.read();
		if (c == -1)
			return false;
		if (c != '\\') {
			input.unread();
			return false;
		}
		boolean nl = TclTextUtils.isNewLine(input);
		input.unread();
		return nl;
	}

	public boolean readMe(CodeScanner input) throws TclParseException {
		if (!iAm(input))
			return false;
		setStart(input.getPosition());
		input.read();
		TclTextUtils.skipNewLine(input);
		int c = -1;
		do {
			c = input.read();
		} while (c != -1 && TclTextUtils.isTrueWhitespace(c));
		input.unread();
		setEnd(input.getPosition() - 1);
		return true;
	}
}
