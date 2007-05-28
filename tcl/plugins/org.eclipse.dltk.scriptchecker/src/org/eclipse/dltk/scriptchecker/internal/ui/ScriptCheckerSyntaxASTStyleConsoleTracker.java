package org.eclipse.dltk.scriptchecker.internal.ui;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.ui.console.PatternMatchEvent;

public class ScriptCheckerSyntaxASTStyleConsoleTracker extends
		ScriptCheckerConsoleTracker {

	public void matchFound(PatternMatchEvent event) {
		try {
			int offset = event.getOffset();
			int length = event.getLength();
			ScriptCheckerASTStyleToFileHyperlink link = new ScriptCheckerASTStyleToFileHyperlink(
					console);
			console.addHyperlink(link, link.computeOffset(offset, length,
					console), link.computeLength(offset, length, console));
			ScriptCheckerASTStyleToGuideHyperlink guideLink = new ScriptCheckerASTStyleToGuideHyperlink(console);
			console.addHyperlink(guideLink, guideLink.computeOffset(offset, length,
					console), guideLink.computeLength(offset, length, console));
		} catch (BadLocationException e) {
		}
	}

	public String getPattern() {
		return "((\\w:)?[^:]+):(\\d+):(.*):\\s*\\((.*)\\)(.*)";
	}
}
