/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.launching;


import org.eclipse.debug.core.DebugPlugin;

/**
 * The execution arguments for running a Script Interpreter. The execution arguments are
 * separated into two parts: arguments to the Interpreter itself, and arguments to the Script 
 * main program. This class provides convenience methods for parsing a string
 * of arguments into separate components.
 * <p>
 * Clients may instantiate this class; it is not intended to be subclassed.
 * </p>
 */
public class ExecutionArguments {
	private String fInterpreterArgs;
	private String fProgramArgs;
		
	/**
	 * Creates a new execution arguments object.
	 *
	 * @param InterpreterArgs command line argument string passed to the Interpreter
	 * @param programArgs command line argument string passed to the program
	 */
	public ExecutionArguments(String interpreterArgs, String scriptArgs) {
		if (interpreterArgs == null || scriptArgs == null)
			throw new IllegalArgumentException();
		fInterpreterArgs= interpreterArgs;
		fProgramArgs= scriptArgs;
	}
	
	/**
	 * Returns the Interpreter arguments as one string.
	 *
	 * @return the Interpreter arguments as one string
	 */
	public String getInterpreterArguments() {
		return fInterpreterArgs;
	}
	
	/**
	 * Returns the program arguments as one string.
	 *
	 * @return the program arguments as one string
	 */
	public String getScriptArguments() {
		return fProgramArgs;
	}
	
	/**
	 * Returns the Interpreter arguments as an array of individual arguments.
	 *
	 * @return the Interpreter arguments as an array of individual arguments
	 */
	public String[] getInterpreterArgumentsArray() {
		return DebugPlugin.parseArguments(fInterpreterArgs);
	}
	
	/**
	 * Returns the program arguments as an array of individual arguments.
	 *
	 * @return the program arguments as an array of individual arguments
	 */
	public String[] getScriptArgumentsArray() {
		return DebugPlugin.parseArguments(fProgramArgs);
	}	
			
}
