/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.eclipse.dltk.ui.tests.navigator.scriptexplorer;

import junit.framework.Test;
import junit.framework.TestSuite;

public class PackageExplorerTests {

	public static Test suite() {
		TestSuite suite= new TestSuite("Test fororg.eclipse.dltk.ui.tests.packageview");
		//$JUnit-BEGIN$
		suite.addTest(ContentProviderTests1.suite());
		suite.addTest(ContentProviderTests2.suite());
		suite.addTest(ContentProviderTests3.suite());
		suite.addTest(ContentProviderTests4.suite());
		suite.addTest(ContentProviderTests5.suite());
		suite.addTest(PackageExplorerShowInTests.suite());
		//suite.addTestSuite(WorkingSetDropAdapterTest.class);
		//$JUnit-END$
		return suite;
	}
}
