/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.tests.eval.generated;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {

		TestSuite suite = new TestSuite(
				"Test for org.eclipse.dltk.python.tests.eval.generated");
		//$JUnit-BEGIN$
//		suite.addTest(genTestclass2.suite());
		suite.addTest(genTestclass1.suite());
//		suite.addTest(genTestimport1.suite());
//		suite.addTest(genTestmethod0.suite());
		suite.addTest(genTestbinops.suite());
//		suite.addTest(genTestclass_import0.suite());
//		suite.addTest(genTestfunc0.suite());
//		suite.addTest(genTestimport0.suite());
//		suite.addTest(genTestfromimport0.suite());
//		suite.addTest(genTestclass0.suite());
		//$JUnit-END$
		return suite;
	}

}
