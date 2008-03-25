package org.eclipse.dltk.console.ui;

import org.eclipse.dltk.console.ScriptConsolePrompt;
import org.eclipse.swt.custom.StyleRange;

public interface IConsoleStyleProvider {

	StyleRange[] createPromptStyle (ScriptConsolePrompt prompt, int offset);
	
	StyleRange[] createUserInputStyle (String content, int offset);
	
	StyleRange[] createInterpreterOutputStyle (String content, int offset);
	
}
