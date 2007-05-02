/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.debug.dbgp.tests;

import junit.framework.TestCase;

import org.eclipse.dltk.dbgp.IDbgpStatus;
import org.eclipse.dltk.dbgp.internal.DbgpStatus;

public class DbgpStatusTests extends TestCase {
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testConstruction() {
		IDbgpStatus s = DbgpStatus.parse("running", "ok");
		
		assertTrue(s.isRunning());
		assertTrue(s.reasonOk());
	}

	public void testEquals() {
		IDbgpStatus a = DbgpStatus.parse("running", "ok");
		IDbgpStatus b = DbgpStatus.parse("running", "ok");

		assertEquals(a, b);
	}
}
