/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.dbgp.commands;

import java.net.URI;

import org.eclipse.dltk.dbgp.breakpoints.DbgpBreakpointConfig;
import org.eclipse.dltk.dbgp.breakpoints.IDbgpBreakpoint;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;

public interface IDbgpBreakpointCommands {
	// Line breakpoint
	String setLineBreakpoint(URI uri, int lineNumber,
			DbgpBreakpointConfig config) throws DbgpException;

	// Call breakpoint
	String setCallBreakpoint(String function, DbgpBreakpointConfig config)
			throws DbgpException;

	// Return breakpoint
	String setReturnBreakpoint(URI uri, String function,
			DbgpBreakpointConfig config) throws DbgpException;

	// Exception breakpoint
	String setExceptionBreakpoint(String exception, DbgpBreakpointConfig config)
			throws DbgpException;

	// Conditional breakpoint
	// String setConditionalBreakpoint(URI uri, int lineNumber,
	// DbgpBreakpointConfig config) throws DbgpException;

	String setConditionalBreakpoint(URI uri, int lineNumber,
			DbgpBreakpointConfig config) throws DbgpException;

	String setConditionalBreakpoint(URI uri, DbgpBreakpointConfig config)
			throws DbgpException;

	// Watch breakpoint
	String setWatchBreakpoint(URI uri, int line,
			DbgpBreakpointConfig config) throws DbgpException;

	// Remove
	void removeBreakpoint(String id) throws DbgpException;

	// Update
	void updateBreakpoint(String id, DbgpBreakpointConfig config)
			throws DbgpException;

	// Get
	IDbgpBreakpoint getBreakpoint(String id) throws DbgpException;

	IDbgpBreakpoint[] getBreakpoints() throws DbgpException;
}
