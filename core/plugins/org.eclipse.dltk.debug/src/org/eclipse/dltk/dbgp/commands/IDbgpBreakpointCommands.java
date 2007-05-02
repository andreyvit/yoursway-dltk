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
import java.util.List;

import org.eclipse.dltk.dbgp.breakpoints.DbgpBreakpointConfig;
import org.eclipse.dltk.dbgp.breakpoints.IDbgpBreakpoint;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;

public interface IDbgpBreakpointCommands {
	String setLineBreakpoint(URI uri, int lineNumber,
			DbgpBreakpointConfig config) throws DbgpException;

	String setCallBreakpoint(String function, DbgpBreakpointConfig config)
			throws DbgpException;

	String setReturnBreakpoint(URI uri, String function, DbgpBreakpointConfig config)
			throws DbgpException;

	String setExceptionBreakpoint(String exception, DbgpBreakpointConfig config)
			throws DbgpException;

	String setConditionalBreakpoint(URI uri, int lineNumber, String expression,
			DbgpBreakpointConfig config) throws DbgpException;

	String setWatchBreakpoint(String expression, DbgpBreakpointConfig config)
			throws DbgpException;

	IDbgpBreakpoint getBreakpoint(String id) throws DbgpException;

	void removeBreakpoint(String id) throws DbgpException;

	void updateBreakpoint(String id, DbgpBreakpointConfig config)
			throws DbgpException;

	List getBreakpoints() throws DbgpException;
}
