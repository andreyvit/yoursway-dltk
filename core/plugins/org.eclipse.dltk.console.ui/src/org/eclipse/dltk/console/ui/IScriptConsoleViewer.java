package org.eclipse.dltk.console.ui;

import org.eclipse.jface.text.ITextViewer;

public interface IScriptConsoleViewer extends ITextViewer {
	String getCommandLine();
	int getCommandLineOffset();
}
