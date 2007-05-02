/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.console;

import java.io.IOException;
import java.util.List;

public interface IScriptConsoleShell {
	List getCompletions(String commandLine, int position) throws IOException;

	String getDescription(String commandLine, int position) throws IOException;

	String[] getNames(String type) throws IOException;

	void close() throws IOException;
}
