/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.eclipse.dltk.internal.debug.ui.handlers;

import org.eclipse.osgi.util.NLS;

public class HandlerMessages {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.internal.debug.ui.handlers.HandlerMessages"; //$NON-NLS-1$

	static {
		NLS.initializeMessages(BUNDLE_NAME, HandlerMessages.class);
	}

	public static String DebuggingEngineNotConfiguredTitle;
	public static String DebuggingEngineNotConfiguredQuestion;

	public static String InterpreterRunnerNotFound;

	public static String NoDefaultDebuggingEngineTitle;
	public static String NoDefaultDebuggingEngineQuestion;

	public static String NoDefaultInterpreterTitle;
	public static String NoDefaultInterpreterQuestion;
}
