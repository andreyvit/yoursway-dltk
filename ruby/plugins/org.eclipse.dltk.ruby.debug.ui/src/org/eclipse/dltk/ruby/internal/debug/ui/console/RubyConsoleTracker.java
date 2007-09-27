/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.debug.ui.console;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.console.IPatternMatchListenerDelegate;
import org.eclipse.ui.console.PatternMatchEvent;
import org.eclipse.ui.console.TextConsole;

/**
 * Provides links for stack traces
 */
public class RubyConsoleTracker implements IPatternMatchListenerDelegate {

	private TextConsole fConsole;

	public void connect(TextConsole console) {
		fConsole = console;
	}

	public void disconnect() {
		fConsole = null;
	}

	protected TextConsole getConsole() {
		return fConsole;
	}

	public void matchFound(PatternMatchEvent event) {
		try {
			int offset = event.getOffset();
			int length = event.getLength();
			IDocument document = getConsole().getDocument();
			String text = document.get(offset, length);
			if (text.indexOf("from -e") != -1) {
				return;
			}
			String trim = text.trim();
			String from_ = "from ";
			if (trim.startsWith(from_)) {
				int shift = text.indexOf(from_) + from_.length();
				offset += shift;
				length -= shift;
			}
			RubyFileHyperlink link = new RubyFileHyperlink(fConsole);
			if (link.isCorrect(offset, length)) {
				fConsole.addHyperlink(link, offset, length);
			}
		} catch (BadLocationException e) {
		}
	}

}