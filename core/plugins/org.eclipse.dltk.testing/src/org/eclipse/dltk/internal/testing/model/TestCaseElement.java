/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.dltk.internal.testing.model;

import org.eclipse.core.runtime.Assert;

import org.eclipse.dltk.testing.model.ITestCaseElement;



public class TestCaseElement extends TestElement implements ITestCaseElement {

	private boolean fIgnored;

	public TestCaseElement(TestSuiteElement parent, String id, String testName) {
		super(parent, id, testName);
		Assert.isNotNull(parent);
	}
	
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.dltk.testing.model.ITestCaseElement#getTestMethodName()
	 * @see org.eclipse.jdt.internal.junit.runner.MessageIds#TEST_IDENTIFIER_MESSAGE_FORMAT
	 * @see org.eclipse.jdt.internal.junit.runner.MessageIds#IGNORED_TEST_PREFIX
	 */
	public String getTestMethodName() {
		String testName= getTestName();
		int index= testName.indexOf('(');
		if (index > 0)
			return testName.substring(0, index);
		index= testName.indexOf('@');
		if (index > 0)
			return testName.substring(0, index);
		return testName;
	}
	
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.dltk.testing.model.ITestCaseElement#getTestClassName()
	 */
	public String getTestClassName() {
		return getClassName();
	}
	
	public void setIgnored(boolean ignored) {
		fIgnored= ignored;
	}
	
	public boolean isIgnored() {
		return fIgnored;
	}
	
	public String toString() {
		return "TestCase: " + getTestClassName() + "." + getTestMethodName() + " : " + super.toString(); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}
}
