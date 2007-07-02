/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.debug.tests;

import org.eclipse.dltk.debug.dbgp.tests.DbgpBreakpointCommandsTests;
import org.eclipse.dltk.debug.dbgp.tests.DbgpContextCommandsTests;
import org.eclipse.dltk.debug.dbgp.tests.DbgpContinuationCommandsTests;
import org.eclipse.dltk.debug.dbgp.tests.DbgpFeatureCommandsTests;
import org.eclipse.dltk.debug.dbgp.tests.DbgpPropertyCommandsTests;
import org.eclipse.dltk.debug.dbgp.tests.DbgpRequestTests;
import org.eclipse.dltk.debug.dbgp.tests.DbgpStackCommandsTests;
import org.eclipse.dltk.debug.dbgp.tests.DbgpStackLevelTests;
import org.eclipse.dltk.debug.dbgp.tests.DbgpStatusCommandsTests;
import org.eclipse.dltk.debug.dbgp.tests.DbgpStreamCommandsTests;
import org.eclipse.dltk.debug.tests.core.BuildpathContainerTests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.eclipse.dltk.debug");
		// $JUnit-BEGIN$
		suite.addTestSuite(DbgpRequestTests.class);
		suite.addTestSuite(DbgpStackLevelTests.class);
		suite.addTestSuite(DbgpPropertyCommandsTests.class);
		suite.addTestSuite(DbgpFeatureCommandsTests.class);
		suite.addTestSuite(DbgpBreakpointCommandsTests.class);
		suite.addTestSuite(DbgpContextCommandsTests.class);
		suite.addTestSuite(DbgpContinuationCommandsTests.class);
		suite.addTestSuite(DbgpStackCommandsTests.class);
		suite.addTestSuite(DbgpStreamCommandsTests.class);
		suite.addTestSuite(DbgpStatusCommandsTests.class);
		
		suite.addTestSuite(BuildpathContainerTests.class);
		// $JUnit-END$
		return suite;
	}
}
