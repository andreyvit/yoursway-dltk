/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.scriptchecker.internal.ui;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.ui.console.IHyperlink;
import org.eclipse.ui.console.PatternMatchEvent;

public class ScriptCheckerSyntaxConsoleTracker extends
		ScriptCheckerConsoleTracker {
	public void matchFound(PatternMatchEvent event) {
		try {
			int offset = event.getOffset();
			int length = event.getLength();
			IHyperlink link = new ScriptCheckerSyntaxHyperlink(console);
			console.addHyperlink(link, offset, length);
		} catch (BadLocationException e) {
		}
	}

	public String getPattern() {
		// return "((\\w:)?[^:]+):(\\d+)\\s+\\((\\w+)\\)\\s+(.*)";
		return "(.*):(\\d+)\\s+";
	}
}
