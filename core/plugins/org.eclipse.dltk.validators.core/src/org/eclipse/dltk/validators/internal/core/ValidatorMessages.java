/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.validators.internal.core;

import org.eclipse.osgi.util.NLS;

public class ValidatorMessages extends NLS {
	private static final String BUNDLE_NAME = ValidatorMessages.class.getName();//$NON-NLS-1$
	public static String ValidatorDefinitionsContainer_failedToLoadValidatorFromXml;
	public static String ValidatorRuntime_badFormat;
	

	static {
		// load message values from bundle file
		NLS.initializeMessages(BUNDLE_NAME, ValidatorMessages.class);
	}
}
