/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM - Initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.debug.ui.messages;

import org.eclipse.osgi.util.NLS;

public class DLTKLaunchMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.debug.ui.messages.DLTKLaunchMessages";//$NON-NLS-1$
	public static String scriptLaunchShortcut2_title;
	public static String scriptLaunchShortcut2;
	public static String WorkingDirectoryBlock_12;
	public static String WorkingDirectoryBlock_18;
	public static String WorkingDirectoryBlock_19;
	public static String WorkingDirectoryBlock_0;
	public static String WorkingDirectoryBlock_1;
	public static String WorkingDirectoryBlock_17;
	public static String WorkingDirectoryBlock_7;
	public static String WorkingDirectoryBlock_4;
	public static String WorkingDirectoryBlock_10;
	public static String WorkingDirectoryBlock_20;
	public static String ArgumentsTab_Exception_occurred_reading_configuration___15;
	public static String WorkingDirectoryBlock_Working_Directory_8;
	public static String ArgumentsTab__Program_arguments__5;
	public static String ArgumentsTab_5;
	public static String ArgumentsTab__Arguments_16;
	public static String InterpreterTab__Interp_1;
	public static String InterpreterTab_Unable_to_initialize_defaults_for_selected_InterpreterEnvironment_1;
	public static String InterpreterTab_7;
	public static String InterpreterTab_8;
	public static String InterpreterTab_9;
	public static String InterpreterArgumentsTab_Interpreter_ar_guments__6;
	public static String InterpreterArgumentsBlock_4;
	public static String InterpreterArgumentsTab_Exception_occurred_reading_configuration___15;
	public static String InterpreterArgumentsBlock_Interpreter_Arguments;

	private DLTKLaunchMessages() {
	// dont instatiate
	}
	static {
		// load message values from bundle file
		NLS.initializeMessages(BUNDLE_NAME, DLTKLaunchMessages.class);
	}
}
