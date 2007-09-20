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
package org.eclipse.dltk.internal.testing;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;

import org.eclipse.core.expressions.PropertyTester;

import org.eclipse.core.resources.IResource;

import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.IType;


/**
 * JUnitPropertyTester provides propertyTester(s) for IResource types
 * for use in XML Expression Language syntax.
 */
public class DLTKTestingPropertyTester extends PropertyTester {

	private static final String PROPERTY_IS_TEST= "isTest"; //$NON-NLS-1$

	private static final String PROPERTY_CAN_LAUNCH_AS_JUNIT_TEST= "canLaunchAsJUnit"; //$NON-NLS-1$

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.internal.corext.refactoring.participants.properties.IPropertyEvaluator#test(java.lang.Object, java.lang.String, java.lang.String)
	 */
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		if (!(receiver instanceof IAdaptable)) {
			throw new IllegalArgumentException("Element must be of type 'IAdaptable', is " + receiver == null ? "null" : receiver.getClass().getName()); //$NON-NLS-1$ //$NON-NLS-2$
		}

		IModelElement element;
		if (receiver instanceof IModelElement) {
			element= (IModelElement) receiver;
		} else if (receiver instanceof IResource) {
			element= DLTKCore.create((IResource) receiver);
			if (element == null) {
				return false;
			}
		} else { // is IAdaptable
			element= (IModelElement) ((IAdaptable) receiver).getAdapter(IModelElement.class);
			if (element == null) {
				IResource resource= (IResource) ((IAdaptable) receiver).getAdapter(IResource.class);
				element= DLTKCore.create(resource);
				if (element == null) {
					return false;
				}
				return false;
			}
		}
		if (PROPERTY_IS_TEST.equals(property)) {
			return isTest(element);
		} else if (PROPERTY_CAN_LAUNCH_AS_JUNIT_TEST.equals(property)) {
			return canLaunchAsTest(element);
		}
		throw new IllegalArgumentException("Unknown test property '" + property + "'"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	private boolean canLaunchAsTest(IModelElement element) {
		switch (element.getElementType()) {
			case IModelElement.SCRIPT_PROJECT:
			case IModelElement.PROJECT_FRAGMENT:
			case IModelElement.SCRIPT_FOLDER:
				return true; // can run, let test runner detect if there are tests
			case IModelElement.SOURCE_MODULE:
			case IModelElement.TYPE:
			case IModelElement.METHOD:
				return isTest(element);
			default:
				return false;
		}
	}

	/*
	 * Return whether the target resource is a JUnit test.
	 */
	private boolean isTest(IModelElement element) {
		try {
			IType testType= null;
			if (element instanceof ISourceModule) {
				IType[] allTypes= (((ISourceModule) element)).getAllTypes();
				if (allTypes.length > 0) {
					testType= allTypes[0];
				}
			} else if (element instanceof IType) {
				testType= (IType) element;
			} else if (element instanceof IMember) {
				testType= ((IMember) element).getDeclaringType();
			}
			if (testType != null && testType.exists()) {
//				return TestSearchEngine.isTestOrTestSuite(testType);
				//TODO: Add correct checking code here.
				return true;
			}
		} catch (CoreException e) {
			// ignore, return false
		}
		return false;
	}
}
