/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.dbgp.internal.commands;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.dbgp.breakpoints.DbgpBreakpointConfig;
import org.eclipse.dltk.dbgp.breakpoints.IDbgpBreakpoint;
import org.eclipse.dltk.dbgp.commands.IDbgpBreakpointCommands;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.dbgp.internal.DbgpRequest;
import org.eclipse.dltk.dbgp.internal.utils.DbgpXmlEntityParser;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DbgpBreakpointCommands extends DbgpBaseCommands implements
		IDbgpBreakpointCommands {

	private static final String BREAKPOINT_SET_COMMAND = "breakpoint_set"; //$NON-NLS-1$

	private static final String BREAKPOINT_GET_COMMAND = "breakpoint_get"; //$NON-NLS-1$

	private static final String BREAKPOINT_UPDATE_COMMAND = "breakpoint_update"; //$NON-NLS-1$

	private static final String BREAKPOINT_REMOVE_COMMAND = "breakpoint_remove"; //$NON-NLS-1$

	private static final String BREAKPOINT_LIST_COMMAND = "breakpoint_list"; //$NON-NLS-1$

	private static final String BREAKPOINT_TAG = "breakpoint"; //$NON-NLS-1$

	final String LINE_BREAKPOINT = "line"; //$NON-NLS-1$

	final String CALL_BREAKPOINT = "call"; //$NON-NLS-1$

	final String RETURN_BREAKPOINT = "return"; //$NON-NLS-1$

	final String EXCEPTION_BREAKPOINT = "exception"; //$NON-NLS-1$

	final String CONDITIONAL_BREAKPOINT = "conditional"; //$NON-NLS-1$

	final String WATCH_BREAKPOINT = "watch"; //$NON-NLS-1$

	protected IDbgpBreakpoint[] parseBreakpointsResponse(Element response) {
		List list = new ArrayList();

		NodeList breakpoints = response.getElementsByTagName(BREAKPOINT_TAG);
		for (int i = 0; i < breakpoints.getLength(); ++i) {
			list.add(DbgpXmlEntityParser.parseBreakpoint((Element) breakpoints
					.item(i)));
		}

		return (IDbgpBreakpoint[]) list
				.toArray(new IDbgpBreakpoint[list.size()]);
	}

	protected String parseSetBreakpointResponse(Element response)
			throws DbgpException {
		return response.getAttribute("id"); //$NON-NLS-1$
	}

	protected String setBreakpoint(String type, URI uri, Integer lineNumber,
			String function, String exception, DbgpBreakpointConfig info)
			throws DbgpException {

		DbgpRequest request = createRequest(BREAKPOINT_SET_COMMAND);
		request.addOption("-t", type); //$NON-NLS-1$

		if (uri != null) {
			request.addOption("-f", uri.toASCIIString()); //$NON-NLS-1$
		}

		if (lineNumber != null) {
			request.addOption("-n", lineNumber.toString()); //$NON-NLS-1$
		}

		if (function != null) {
			request.addOption("-m", function); //$NON-NLS-1$
		}

		if (exception != null) {
			request.addOption("-x", exception); //$NON-NLS-1$
		}

		if (info != null) {
			request.addOption("-s", info.getStateString()); //$NON-NLS-1$
			request.addOption("-r", info.getTemporaryString()); //$NON-NLS-1$

			if (info.getHitValue() != -1 && info.getHitCondition() != -1) {
				request.addOption("-h", info.getHitValue()); //$NON-NLS-1$
				request.addOption("-o", info.getHitConditionString()); //$NON-NLS-1$
			}

			String expression = info.getExpression();
			if (expression != null) {
				request.setData(expression);
			}
		}

		return parseSetBreakpointResponse(communicate(request));
	}

	public DbgpBreakpointCommands(IDbgpCommunicator communicator) {
		super(communicator);
	}

	public String setLineBreakpoint(URI uri, int lineNumber,
			DbgpBreakpointConfig info) throws DbgpException {
		return setBreakpoint(LINE_BREAKPOINT, uri, new Integer(lineNumber),
				null, null, info);
	}

	public String setCallBreakpoint(URI uri, String function, DbgpBreakpointConfig info)
			throws DbgpException {
		return setBreakpoint(CALL_BREAKPOINT, uri, null, function, null, info);
	}

	public String setReturnBreakpoint(URI uri, String function,
			DbgpBreakpointConfig info) throws DbgpException {
		return setBreakpoint(RETURN_BREAKPOINT, uri, null, function,
				null, info);
	}

	public String setExceptionBreakpoint(String exception,
			DbgpBreakpointConfig info) throws DbgpException {
		return setBreakpoint(EXCEPTION_BREAKPOINT, null, null, null, exception,
				info);
	}

	public String setConditionalBreakpoint(URI uri, DbgpBreakpointConfig info)
			throws DbgpException {
		return setBreakpoint(CONDITIONAL_BREAKPOINT, uri, null, null, null,
				info);
	}

	public String setConditionalBreakpoint(URI uri, int lineNumber,
			DbgpBreakpointConfig info) throws DbgpException {
		return setBreakpoint(CONDITIONAL_BREAKPOINT, uri, new Integer(
				lineNumber), null, null, info);
	}

	public String setWatchBreakpoint(URI uri, int line,
			DbgpBreakpointConfig info) throws DbgpException {
		return setBreakpoint(WATCH_BREAKPOINT, uri, new Integer(line), null,
				null, info);
	}

	public IDbgpBreakpoint getBreakpoint(String id) throws DbgpException {
		DbgpRequest request = createRequest(BREAKPOINT_GET_COMMAND);
		request.addOption("-d", id); //$NON-NLS-1$
		IDbgpBreakpoint[] breakpoints = parseBreakpointsResponse(communicate(request));
		if (breakpoints.length > 0) {
			return breakpoints[0];
		}
		return null;
	}

	public void removeBreakpoint(String id) throws DbgpException {
		if (id == null) {
			return;
		}

		DbgpRequest request = createRequest(BREAKPOINT_REMOVE_COMMAND);
		request.addOption("-d", id); //$NON-NLS-1$
		communicate(request);
	}

	public void updateBreakpoint(String id, DbgpBreakpointConfig config)
			throws DbgpException {
		DbgpRequest request = createRequest(BREAKPOINT_UPDATE_COMMAND);
		request.addOption("-d", id); //$NON-NLS-1$
		request.addOption("-s", config.getStateString()); //$NON-NLS-1$

		if (config.getHitValue() != -1) {
			request.addOption("-h", config.getHitValue()); //$NON-NLS-1$
		}

		if (config.getHitCondition() != -1) {
			request.addOption("-o", config.getHitConditionString()); //$NON-NLS-1$
		}
		
		if (config.getLineNo() != -1) {
			request.addOption("-n", config.getLineNo()); //$NON-NLS-1$			
		}
		
		// not sure that this is correct but it looks that this is possible
		// TODO review it
		String conditionExpression = config.getExpression();
		if (conditionExpression != null) {
			request.setData(conditionExpression);
		}
		communicate(request);
	}

	public IDbgpBreakpoint[] getBreakpoints() throws DbgpException {
		return parseBreakpointsResponse(communicate(createRequest(BREAKPOINT_LIST_COMMAND)));
	}
}
