package org.eclipse.dltk.javascript.internal.debug.ui.console;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.ui.console.IHyperlink;
import org.eclipse.ui.console.IPatternMatchListenerDelegate;
import org.eclipse.ui.console.PatternMatchEvent;
import org.eclipse.ui.console.TextConsole;

public class JavaScriptConsoleTracker implements IPatternMatchListenerDelegate {
	private TextConsole console;

	public void connect(TextConsole console) {
		this.console = console;
	}

	public void disconnect() {
		console = null;
	}

	protected TextConsole getConsole() {
		return console;
	}

	public void matchFound(PatternMatchEvent event) {
		try {
			int offset = event.getOffset();
			int length = event.getLength();
			IHyperlink link = new JavaScriptFileHyperlink(console);
			console.addHyperlink(link, offset + 1, length - 2);
		} catch (BadLocationException e) {
		}
	}
}