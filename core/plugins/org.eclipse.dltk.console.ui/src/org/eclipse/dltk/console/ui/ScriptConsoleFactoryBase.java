package org.eclipse.dltk.console.ui;

import org.eclipse.ui.console.IConsoleFactory;


public abstract class ScriptConsoleFactoryBase implements IConsoleFactory {
	public ScriptConsoleFactoryBase(){	
	}
	
	protected void registerAndOpenConsole(ScriptConsole console){
		ScriptConsoleManager manager = ScriptConsoleManager.getInstance();
		manager.add(console);
		manager.showConsole(console);		
	}
			
	public void openConsole() {
		registerAndOpenConsole(createConsoleInstance());
	}
	
	protected abstract ScriptConsole createConsoleInstance();
}