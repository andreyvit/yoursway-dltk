/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.core.tests.model;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.dltk.core.tests.buildpath.BuildpathTests;
import org.eclipse.dltk.core.tests.ddp.DDPTests;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for org.eclipse.dltk.core.tests.model");
		// $JUnit-BEGIN$
		suite.addTest(BufferTests.suite());
		suite.addTest(ModelMembersTests.suite());
		suite.addTest(BuildpathTests.suite());
		suite.addTest(WorkingCopyTests.suite());
		suite.addTest(DDPTests.suite());
		// $JUnit-END$
		return suite;
	}
}
