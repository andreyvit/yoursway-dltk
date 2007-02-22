package org.eclipse.dltk.console.ui.internal;

import org.eclipse.dltk.console.ui.IScriptConsoleListener;
import org.eclipse.dltk.console.ui.IScriptConsoleSession;

public class ScriptConsoleSession implements IScriptConsoleListener,
		IScriptConsoleSession {
	private StringBuffer session;

	public ScriptConsoleSession() {
		this.session = new StringBuffer();
	}

	public void interpreterResponse(String text) {		
		if (text != null) {
			session.append("> ");
			session.append(text);
		}
	}

	public void userRequest(String text) {
		session.append("< ");
		session.append(text);
		session.append('\n');
	}

	public String toString() {
		return session.toString();
	}
}
