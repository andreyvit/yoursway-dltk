package org.eclipse.dltk.scriptchecker.internal.ui;

import java.util.regex.Pattern;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.ui.console.IHyperlink;
import org.eclipse.ui.console.IPatternMatchListener;
import org.eclipse.ui.console.PatternMatchEvent;
import org.eclipse.ui.console.TextConsole;

public class ScriptCheckerSyntaxConsoleTracker implements IPatternMatchListener {
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
			IHyperlink link = new ScriptCheckerSyntaxHyperlink(console);
			console.addHyperlink(link, offset, length);
		} catch (BadLocationException e) {
		}
	}

	public int getCompilerFlags() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getLineQualifier() {
		return null;
	}

	public String getPattern() {
		return "((\\w:)?[^:]+):(\\d+)\\s+\\((\\w+)\\)\\s+(.*)";
	}
}
