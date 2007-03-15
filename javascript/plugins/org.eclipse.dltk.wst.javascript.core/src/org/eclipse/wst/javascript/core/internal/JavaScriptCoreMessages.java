/**********************************************************************
 * Copyright (c) 2005 IBM Corporation and others. All rights reserved.   This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 * IBM - Initial API and implementation
 **********************************************************************/
package org.eclipse.wst.javascript.core.internal;

import org.eclipse.osgi.util.NLS;

/**
 * Strings used by Javascript Core
 * 
 * @plannedfor 1.0
 */
public class JavaScriptCoreMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.wst.javascript.core.internal.JavaScriptCorePluginResources";//$NON-NLS-1$

	public static String __javascript_llex___not_fo_EXC_;
	public static String The_file___javascript_llex_EXC_;
	public static String _Unknown_token___EXC_;
	public static String RuntimeWrappedException_0;

	static {
		// load message values from bundle file
		NLS.initializeMessages(BUNDLE_NAME, JavaScriptCoreMessages.class);
	}
	
	private JavaScriptCoreMessages() {
		// cannot create new instance
	}
}