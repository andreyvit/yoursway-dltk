/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.debug.dbgp.tests;

import java.io.IOException;
import java.text.MessageFormat;

import org.eclipse.dltk.dbgp.IDbgpStackLevel;
import org.eclipse.dltk.dbgp.commands.IDbgpStatckCommands;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.dbgp.internal.DbgpRequest;
import org.eclipse.dltk.dbgp.internal.commands.DbgpStackCommands;
import org.eclipse.dltk.dbgp.internal.commands.IDbgpCommunicator;
import org.w3c.dom.Element;

public class DbgpStackCommandsTests extends DbgpProtocolTests {
	protected Element getStackDepthResponse(int transaction_id, int depth)
			throws IOException {
		String xml = getResourceAsString("get_stack_depth.xml");

		xml = MessageFormat.format(xml, new Object[] {
				Integer.toString(transaction_id), Integer.toString(depth) });

		return parseResponse(xml);
	}

	protected Element getStackLevelsResponse() {
		return null;
	}

	public void testStackDepth() throws Exception {
		final Element response = getStackDepthResponse(324, 3);

		IDbgpStatckCommands commands = new DbgpStackCommands(
				new IDbgpCommunicator() {
					public Element communicate(DbgpRequest request)
							throws DbgpException {

						assertEquals(1, request.optionCount());
						assertTrue(request.hasOption("-i"));
						assertEquals(Integer.toString(324), request
								.getOption("-i"));

						return response;
					}
				});

		int depth = commands.getStackDepth();
		assertEquals(3, depth);
	}

	public void testGetStackLevel() throws Exception {
		final Element response = null;

		IDbgpStatckCommands commands = new DbgpStackCommands(
				new IDbgpCommunicator() {

					public Element communicate(DbgpRequest request)
							throws DbgpException {

						assertEquals(2, request.optionCount());

						assertTrue(request.hasOption("-i"));
						
						assertTrue(request.hasOption("-d"));
						assertEquals(Integer.toString(2), request
								.getOption("-d"));

						return response;
					}
				});
		
		IDbgpStackLevel level = commands.getStackLevel(2);
		assertEquals(2, level.getLevel());
		
		
	}

	public void testGetStackLevels() throws Exception {

	}
}
