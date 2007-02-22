package org.eclipse.dltk.console.ui.internal;

import java.io.IOException;

public interface ICommandHandler {
	String handleCommand(String userInput) throws IOException;	
}
