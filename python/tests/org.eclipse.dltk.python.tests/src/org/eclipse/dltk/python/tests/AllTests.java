/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {
	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.eclipse.dltk.python.tests.model");
		//$JUnit-BEGIN$
		suite.addTest(org.eclipse.dltk.python.tests.eval.generated.AllTests.suite());
		suite.addTest(org.eclipse.dltk.python.tests.buildpath.BuildpathTests.suite());
		suite.addTest(org.eclipse.dltk.python.tests.model.AllPythonModelTests.suite());
		//$JUnit-END$
		return suite;
	}
}
