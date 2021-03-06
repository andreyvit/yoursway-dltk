/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.internal.launching;

import org.eclipse.osgi.util.NLS;

public class PythonInterpreterMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.dltk.python.internal.launching.PythonInterpreterMessages";//$NON-NLS-1$

	private PythonInterpreterMessages() {
		// dont instatiate
	}

	static {
		// load message values from bundle file
		NLS.initializeMessages(BUNDLE_NAME, PythonInterpreterMessages.class);
	}

}
