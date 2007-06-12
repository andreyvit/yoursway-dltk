/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.dbgp.internal.commands;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.dltk.dbgp.commands.IDbgpDataTypeCommands;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.dbgp.internal.DbgpRequest;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DbgpDataTypeCommands extends DbgpBaseCommands implements
		IDbgpDataTypeCommands {
	private static final String TYPEMAP_GET_COMMAND = "typemap_get";

	private static final String ATTR_TYPE = "type";
	private static final String ATTR_NAME = "name";

	private static final String TAG_MAP = "map";

	private final Map converter;

	private Integer typeToInteger(String type) {
		return (Integer) converter.get(type);
	}

	public DbgpDataTypeCommands(IDbgpCommunicator communicator) {
		super(communicator);

		converter = new HashMap();
		converter.put("bool", new Integer(BOOL_TYPE));
		converter.put("int", new Integer(INT_TYPE));
		converter.put("float", new Integer(FLOAT_TYPE));
		converter.put("string", new Integer(STRING_TYPE));
		converter.put("null", new Integer(NULL_TYPE));
		converter.put("array", new Integer(ARRAY_TYPE));
		converter.put("hash", new Integer(HASH_TYPE));
		converter.put("object", new Integer(OBJECT_TYPE));
		converter.put("resource", new Integer(RESOURCE_TYPE));
	}

	public Map getTypeMap() throws DbgpException {
		DbgpRequest request = createRequest(TYPEMAP_GET_COMMAND);
		Element element = communicate(request);

		Map result = new HashMap();

		NodeList maps = element.getElementsByTagName(TAG_MAP);

		for (int i = 0; i < maps.getLength(); i++) {
			Element map = (Element) maps.item(i);

			String type = map.getAttribute(ATTR_TYPE);
			Integer intType = typeToInteger(type);

			if (intType == null) {
				throw new DbgpException("Invalid type attribute");
			}

			String name = map.getAttribute(ATTR_NAME);

			result.put(name, intType);
		}

		return result;
	}
}
