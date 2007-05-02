/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.debug.dbgp.tests;

import java.net.URI;

import junit.framework.TestCase;

import org.eclipse.dltk.dbgp.IDbgpStackLevel;
import org.eclipse.dltk.dbgp.internal.DbgpStackLevel;

public class DbgpStackLevelTests extends TestCase {
	private static final String uri = "";

	private IDbgpStackLevel level;

	protected void setUp() throws Exception {
		super.setUp();

		level = new DbgpStackLevel(new URI(uri), "", 2, 56, 0, 80);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testEquals() throws Exception {
		IDbgpStackLevel l = new DbgpStackLevel(new URI(uri), "", 2, 56, 0, 80);

		assertEquals(l, level);
	}
}
