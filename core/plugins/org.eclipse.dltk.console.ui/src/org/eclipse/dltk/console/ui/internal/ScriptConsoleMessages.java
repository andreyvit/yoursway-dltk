/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.console.ui.internal;

import org.eclipse.osgi.util.NLS;

public class ScriptConsoleMessages extends NLS  {
		private static final String BUNDLE_NAME = "org.eclipse.dltk.console.ui.internal.ScriptConsoleMessages"; //$NON-NLS-1$

		public static String SaveSessionAction;

		public static String SaveSessionTooltip;

		public static String TerminateConsoleAction;
		
		public static String TerminateConsoleTooltip;

		static {
			NLS.initializeMessages(BUNDLE_NAME, ScriptConsoleMessages.class);
		}
}
