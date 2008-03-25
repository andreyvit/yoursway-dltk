package org.eclipse.dltk.console.ui.internal;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.ui.console.IHyperlink;
import org.eclipse.ui.console.IPatternMatchListenerDelegate;
import org.eclipse.ui.console.PatternMatchEvent;
import org.eclipse.ui.console.TextConsole;

public class HTTPConsolePatternMatcher implements IPatternMatchListenerDelegate {
	private TextConsole textConsole;

	public void connect(TextConsole console) {
		this.textConsole = console;
	}

	public void disconnect() {
	}

	public void matchFound(PatternMatchEvent event) {
		IHyperlink link = new HTTPConsoleHyperlink(textConsole);
		try {
			textConsole
					.addHyperlink(link, event.getOffset(), event.getLength());
		} catch (BadLocationException e) {
		}
	}
}
