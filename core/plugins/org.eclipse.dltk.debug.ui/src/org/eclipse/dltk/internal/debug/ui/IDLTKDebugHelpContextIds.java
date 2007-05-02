/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.debug.ui;

import org.eclipse.dltk.debug.ui.DLTKDebugUIPlugin;


/**
 * Help context ids for the Script Debug UI.
 * <p>
 * This interface contains constants only; it is not intended to be implemented
 * or extended.
 * </p>
 * 
 */
public interface IDLTKDebugHelpContextIds {
	
	public static final String PREFIX= DLTKDebugUIPlugin.PLUGIN_ID + '.';

	// view parts
	public static final String DISPLAY_VIEW= PREFIX + "display_view_context"; //$NON-NLS-1$
	public static final String STACK_TRACE_CONSOLE = PREFIX + "stack_trace_console_context"; //$NON-NLS-1$

	//dialogs
	public static final String EDIT_INTERPRETER_DIALOG= PREFIX + "edit_interpreter_dialog_context"; //$NON-NLS-1$
	public static final String INTERPRETER_DETAILS_DIALOG= PREFIX + "interpreter_details_dialog_context"; //$NON-NLS-1$
	public static final String MAIN_TYPE_SELECTION_DIALOG= PREFIX + "main_type_selection_dialog_context"; //$NON-NLS-1$
	public static final String EDIT_DETAIL_FORMATTER_DIALOG= PREFIX + "edit_detail_formatter_dialog_context"; //$NON-NLS-1$
	public static final String INSTANCE_BREAKPOINT_SELECTION_DIALOG= PREFIX + "instance_breakpoint_selection_dialog_context"; //$NON-NLS-1$
	public static final String SNIPPET_IMPORTS_DIALOG= PREFIX + "snippet_imports_dialog_context"; //$NON-NLS-1$
	public static final String ADD_EXCEPTION_DIALOG= PREFIX + "add_exception_dialog_context"; //$NON-NLS-1$
	public static final String DETAIL_DISPLAY_OPTIONS_DIALOG= PREFIX + "detail_options_dialog_context"; //$NON-NLS-1$

	// Preference/Property pages
	public static final String INTERPRETER_PREFERENCE_PAGE= PREFIX + "interpreter_preference_page_context"; //$NON-NLS-1$
	public static final String INTERPRETER_PROFILES_PAGE= PREFIX + "interpreter_profiles_page_context"; //$NON-NLS-1$
	public static final String LAUNCH_INTERPRETER_PROPERTY_PAGE= PREFIX + "launch_interpreter_property_page_context"; //$NON-NLS-1$
	public static final String SCRIPT_DEBUG_PREFERENCE_PAGE= PREFIX + "script_debug_preference_page_context"; //$NON-NLS-1$	
	public static final String SCRIPT_STEP_FILTER_PREFERENCE_PAGE= PREFIX + "script_step_filter_preference_page_context"; //$NON-NLS-1$
	public static final String SCRIPT_BREAKPOINT_PREFERENCE_PAGE= PREFIX + "script_breakpoint_preference_page_context"; //$NON-NLS-1$
	public static final String SCRIPT_DETAIL_FORMATTER_PREFERENCE_PAGE= PREFIX + "script_detail_formatter_preference_page_context"; //$NON-NLS-1$
	public static final String SCRIPT_PRIMITIVES_PREFERENCE_PAGE= PREFIX + "script_primitives_preference_page_context"; //$NON-NLS-1$
	
	// reused ui-blocks
	public static final String SOURCE_ATTACHMENT_BLOCK= PREFIX + "source_attachment_context"; //$NON-NLS-1$
	public static final String WORKING_DIRECTORY_BLOCK= PREFIX + "working_directory_context"; //$NON-NLS-1$

	// application launch configuration dialog tabs
	public static final String LAUNCH_CONFIGURATION_DIALOG_ARGUMENTS_TAB= PREFIX + "launch_configuration_dialog_arguments_tab"; //$NON-NLS-1$
	public static final String LAUNCH_CONFIGURATION_DIALOG_CLASSPATH_TAB= PREFIX + "launch_configuration_dialog_classpath_tab"; //$NON-NLS-1$
	public static final String LAUNCH_CONFIGURATION_DIALOG_CONNECT_TAB= PREFIX + "launch_configuration_dialog_connect_tab"; //$NON-NLS-1$
	public static final String LAUNCH_CONFIGURATION_DIALOG_INTERPRETER_TAB= PREFIX + "launch_configuration_dialog_interpreter_tab"; //$NON-NLS-1$
	public static final String LAUNCH_CONFIGURATION_DIALOG_MAIN_TAB= PREFIX + "launch_configuration_dialog_main_tab"; //$NON-NLS-1$
	public static final String LAUNCH_CONFIGURATION_DIALOG_SOURCE_TAB= PREFIX + "launch_configuration_dialog_source_tab"; //$NON-NLS-1$
	
	// applet launch configuration dialog tabs
	public static final String LAUNCH_CONFIGURATION_DIALOG_APPLET_MAIN_TAB= PREFIX + "launch_configuration_dialog_applet_main_tab"; //$NON-NLS-1$
	public static final String LAUNCH_CONFIGURATION_DIALOG_APPLET_ARGUMENTS_TAB= PREFIX + "launch_configuration_dialog_applet_arguments_tab"; //$NON-NLS-1$

	//actions
	public static final String STEP_INTO_SELECTION_ACTION = PREFIX + "step_into_selection_action"; //$NON-NLS-1$
	public static final String SHOW_STATICS_ACTION = PREFIX + "show_static_action_context"; //$NON-NLS-1$
	public static final String SHOW_CONSTANTS_ACTION = PREFIX + "show_constants_action_context"; //$NON-NLS-1$
	public static final String CLEAR_DISPLAY_VIEW_ACTION = PREFIX + "clear_display_view_action_context"; //$NON-NLS-1$
	public static final String TERMINATE_SCRAPBOOK_Interpreter_ACTION = PREFIX + "terminate_scrapbook_Interpreter_action_context"; //$NON-NLS-1$
	public static final String SCRAPBOOK_IMPORTS_ACTION = PREFIX + "scrapbook_imports_action_context"; //$NON-NLS-1$
	
	public static final String NEW_SNIPPET_WIZARD_PAGE= PREFIX + "new_snippet_wizard_page_context"; //$NON-NLS-1$
}
