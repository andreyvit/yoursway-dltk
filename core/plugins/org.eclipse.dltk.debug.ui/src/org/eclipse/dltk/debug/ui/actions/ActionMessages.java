/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.debug.ui.actions;

import org.eclipse.osgi.util.NLS;

public class ActionMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.debug.ui.actions.ActionMessages";//$NON-NLS-1$

	public static String BreakpointAction_Breakpoint_configuration_1 ;

	public static String BreakpointAction_Exceptions_occurred_attempting_to_modify_breakpoint__2 ;
	
	// ScriptBreakpointPropertiesRulerAction
	public static String ScriptBreakpointPropertiesRulerAction_BreakpointProperties;
	
	

	static {
		NLS.initializeMessages(BUNDLE_NAME, ActionMessages.class);
	}
}
