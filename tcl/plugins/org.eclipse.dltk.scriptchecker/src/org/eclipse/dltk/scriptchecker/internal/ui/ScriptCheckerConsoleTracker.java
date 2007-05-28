package org.eclipse.dltk.scriptchecker.internal.ui;

import org.eclipse.ui.console.IPatternMatchListener;
import org.eclipse.ui.console.TextConsole;

public abstract class ScriptCheckerConsoleTracker implements IPatternMatchListener {

	protected TextConsole console;

	public ScriptCheckerConsoleTracker() {
		super();
	}

	public void connect(TextConsole console) {
		this.console = console;
	}

	public void disconnect() {
		console = null;
	}

	protected TextConsole getConsole() {
		return console;
	}

	public int getCompilerFlags() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getLineQualifier() {
		return null;
	}

}