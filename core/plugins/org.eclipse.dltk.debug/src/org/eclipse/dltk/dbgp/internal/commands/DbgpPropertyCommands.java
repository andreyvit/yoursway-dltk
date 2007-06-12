/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.dbgp.internal.commands;

import org.eclipse.dltk.dbgp.IDbgpProperty;
import org.eclipse.dltk.dbgp.commands.IDbgpPropertyCommands;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.dbgp.internal.DbgpRequest;
import org.eclipse.dltk.dbgp.internal.utils.DbgpXmlEntityParser;
import org.eclipse.dltk.dbgp.internal.utils.DbgpXmlParser;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DbgpPropertyCommands extends DbgpBaseCommands implements
		IDbgpPropertyCommands {
	private static final String PROPERTY_GET_COMMAND = "property_get";

	private static final String PROPERTY_SET_COMMAND = "property_set";

	// private static final String PROPERTY_VALUE_COMMAND = "property_value";

	private static final String PROPERTY_TAG = "property";

	protected IDbgpProperty parsePropertyResponse(Element response)
			throws DbgpException {
		// TODO: check length!!!
		NodeList properties = response.getElementsByTagName(PROPERTY_TAG);
		return DbgpXmlEntityParser.parseProperty((Element) properties.item(0));
	}

	public DbgpPropertyCommands(IDbgpCommunicator communicator) {
		super(communicator);
	}

	protected IDbgpProperty getProperty(String name, Integer stackDepth,
			Integer contextId) throws DbgpException {
		DbgpRequest request = createRequest(PROPERTY_GET_COMMAND);
		request.addOption("-n", name);

		if (stackDepth != null) {
			request.addOption("-d", stackDepth);
		}

		if (contextId != null) {
			request.addOption("-c", contextId);
		}

		return parsePropertyResponse(communicate(request));
	}

	public IDbgpProperty getPropertyByKey(String name, String key)
			throws DbgpException {
		DbgpRequest request = createRequest(PROPERTY_GET_COMMAND);
		request.addOption("-n", name);
		request.addOption("-k", key);
		return parsePropertyResponse(communicate(request));
	}

	public IDbgpProperty getProperty(String name) throws DbgpException {
		return getProperty(name, null, null);
	}

	public IDbgpProperty getProperty(String name, int stackDepth)
			throws DbgpException {
		return getProperty(name, new Integer(stackDepth), null);
	}

	public IDbgpProperty getProperty(String name, int stackDepth, int contextId)
			throws DbgpException {
		return getProperty(name, new Integer(stackDepth),
				new Integer(contextId));
	}

	public IDbgpProperty getProperty(String name, int stackDepth,
			int contextId, String dataType, String dataPage)
			throws DbgpException {
		return null;
	}

	public boolean setProperty(IDbgpProperty property) throws DbgpException {
		DbgpRequest request = createRequest(PROPERTY_SET_COMMAND);
		request.addOption("-n", property.getName());
		request.setData(property.getValue());
		return DbgpXmlParser.parseSuccess(communicate(request));
	}

	public boolean setProperty(String name, int stackDepth, String value)
			throws DbgpException {
		DbgpRequest request = createRequest(PROPERTY_SET_COMMAND);
		request.addOption("-n", name);
		request.addOption("-d", stackDepth);
		request.setData(value);
		return DbgpXmlParser.parseSuccess(communicate(request));
	}
}
