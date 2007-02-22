package org.eclipse.dltk.console;

import java.io.IOException;
import java.util.List;

public interface IScriptConsoleShell {
	List getCompletions(String commandLine, int position) throws IOException;

	String getDescription(String commandLine, int position) throws IOException;

	String[] getNames(String type) throws IOException;

	void close() throws IOException;
}
