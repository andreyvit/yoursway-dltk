/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.debug.core;

public class DebugPreferenceConstants {
	public static final int DBGP_DEFAULT_PORT = 9000;
	public static final int DBGP_AVAILABLE_PORT = -1;

	public static final String PREF_DBGP_BREAK_ON_FIRST_LINE = "dbgp_break_on_first_line";
	
	public static final String PREF_DBGP_ENABLE_LOGGING = "dbgp_enable_logging";

	public static final String PREF_DBGP_PORT = "dbgp_port";

	public static final String PREF_DBGP_CONNECTION_TIMEOUT = "dbgp_connection_timeout";

	public static final String PREF_DBGP_RESPONSE_TIMEOUT = "dbgp_response_timeout";

	public static final String PREF_DBGP_SHOW_SCOPE_PREFIX = "dbgp_show_scope_";
	
	public static final String PREF_DBGP_SHOW_SCOPE_LOCAL = PREF_DBGP_SHOW_SCOPE_PREFIX + "local";

	public static final String PREF_DBGP_SHOW_SCOPE_GLOBAL = PREF_DBGP_SHOW_SCOPE_PREFIX + "global";

	public static final String PREF_DBGP_SHOW_SCOPE_CLASS = PREF_DBGP_SHOW_SCOPE_PREFIX + "class";
}
