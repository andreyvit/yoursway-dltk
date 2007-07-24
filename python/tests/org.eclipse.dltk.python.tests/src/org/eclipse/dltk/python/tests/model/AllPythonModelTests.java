/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.tests.model;

import junit.framework.Test;
import junit.framework.TestSuite;


public class AllPythonModelTests {
	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.eclipse.dltk.python.tests.model");
		//$JUnit-BEGIN$
		suite.addTest(ModelPythonTests.suite());
		suite.addTest(WorkingCopyTests.suite());
		
		suite.addTest(GeneratedModelTests0.suite());
		suite.addTest(GeneratedModelTests1.suite());
		suite.addTest(GeneratedModelTests2.suite());
		suite.addTest(GeneratedModelTests3.suite());
		suite.addTest(GeneratedModelTests4.suite());
		suite.addTest(GeneratedModelTests5.suite());
		suite.addTest(GeneratedModelTests6.suite());

		suite.addTest(CopyMoveResourcesTests.suite());
		suite.addTest(DeleteResourcesTests.suite());
		//$JUnit-END$
		return suite;
	}
}
