package org.eclipse.dltk.console.ui.internal;

import org.eclipse.dltk.console.ui.IScriptConsoleInput;

public class ScriptConsoleInput implements IScriptConsoleInput {

	private ScriptConsolePage page;
	
	public ScriptConsoleInput(ScriptConsolePage page){
		this.page = page;
	}
	
	public void insertText(String line) {
		page.insertText(line);
	}
}
 