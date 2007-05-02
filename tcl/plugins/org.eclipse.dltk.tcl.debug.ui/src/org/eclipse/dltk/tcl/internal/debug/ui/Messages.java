/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.debug.ui;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.tcl.internal.debug.ui.messages"; //$NON-NLS-1$
	
	public static String NoDebuggingEngine_title;
	public static String NoDebuggingEngine_message;
	
	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
