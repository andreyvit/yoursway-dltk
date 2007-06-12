/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.launching;

import org.eclipse.osgi.util.NLS;

// TODO: move to internal package
public class LaunchingMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.launching.LaunchingMessages";//$NON-NLS-1$
	
	public static String StandardInterpreterRunner__0__at_localhost__1__1;

	public static String AbstractScriptLaunchConfigurationDelegate_Script_project_not_specified_9;
	public static String AbstractScriptLaunchConfigurationDelegate_InterpreterEnvironment_home_directory_for__0__does_not_exist___1__6;
	public static String AbstractScriptLaunchConfigurationDelegate_InterpreterEnvironment_home_directory_not_specified_for__0__5;
	public static String AbstractScriptLaunchConfigurationDelegate_Main_type_not_specified_11;
	public static String AbstractScriptLaunchConfigurationDelegate_Project_does_not_exist_or_is_not_a_Script_project_10;
	public static String AbstractScriptLaunchConfigurationDelegate_The_specified_InterpreterEnvironment_installation_does_not_exist_4;
	public static String AbstractScriptLaunchConfigurationDelegate_Working_directory_does_not_exist___0__12;

	public static String DefaultProjectBuildpathEntry_2;
	public static String DefaultProjectBuildpathEntry_3;
	public static String DefaultProjectBuildpathEntry_4;

	public static String ScriptLaunchConfigurationDelegate_Verifying_launch_attributes____1;
	public static String ScriptLaunchConfigurationDelegate_Creating_source_locator____2;
	public static String ScriptLaunchConfigurationDelegate_0;

	public static String ScriptRuntime_badFormat;
	public static String ScriptRuntime_exceptionsOccurred;
	public static String ScriptRuntime_Specified_Interpreter_install_type_does_not_exist___0__2;
	public static String ScriptRuntime_Specified_Interpreter_install_not_found__type__0___name__1__2;
	public static String ScriptRuntime_Launch_configuration__0__references_non_existing_project__1___1;
	public static String ScriptRuntime_Could_not_resolve_classpath_container___0__1;
	public static String ScriptRuntime_Buildpath_references_non_existant_project___0__3;
	public static String ScriptRuntime_Buildpath_references_non_existant_archive___0__4;
	public static String ScriptRuntime_26;

	public static String ScriptRuntime_28;
	public static String ScriptRuntime_31;
	public static String ScriptRuntime_32;
	
	public static String LaunchingPlugin_32;
	public static String LaunchingPlugin_33;
	public static String LaunchingPlugin_34;
	public static String LaunchingPlugin_0;
	public static String LaunchingPlugin_1;

	public static String libraryLocation_assert_libraryNotNull;


	public static String InterpreterInstall_assert_idNotNull;
	public static String InterpreterInstall_assert_typeNotNull;

	public static String InterpreterInstallType_duplicateInterpreter;

	public static String InterpreterRunnerConfig_assert_classNotNull;
	//public static String InterpreterRunnerConfig_assert_classPathNotNull;
	//public static String InterpreterRunnerConfig_assert_programArgsNotNull;
	//public static String InterpreterRunnerConfig_assert_InterpreterArgsNotNull;

	public static String RuntimeBuildpathEntry_An_exception_occurred_generating_runtime_classpath_memento_8;
	public static String RuntimeBuildpathEntry_Unable_to_recover_runtime_class_path_entry_type_2;
	public static String RuntimeBuildpathEntry_Unable_to_recover_runtime_class_path_entry_location_3;
	public static String RuntimeBuildpathEntry_Unable_to_recover_runtime_class_path_entry___missing_project_name_4;
	public static String RuntimeBuildpathEntry_Unable_to_recover_runtime_class_path_entry___missing_archive_path_5;
	public static String RuntimeBuildpathEntry_Unable_to_recover_runtime_class_path_entry___missing_variable_name_6;
	public static String RuntimeBuildpathEntry_Illegal_classpath_entry__0__1;

	public static String InterpreterEnvironmentContainer_InterpreterEnvironment_System_Library_1;

	public static String InterpreterEnvironmentContainerInitializer_InterpreterEnvironment_referenced_by_classpath_container__0__does_not_exist__1;
	public static String InterpreterEnvironmentContainerInitializer_Buildpath_entry__0__does_not_refer_to_an_existing_library__2;
	public static String InterpreterEnvironmentContainerInitializer_Buildpath_entry__0__does_not_refer_to_a_library__3;
	public static String InterpreterEnvironmentContainerInitializer_Default_System_Library_1;

	public static String ArchiveSourceLocation_Unable_to_create_memento_for_archive_source_location__0__1;
	public static String ArchiveSourceLocation_Unable_to_initialize_source_location___missing_archive_path__3;
	public static String ArchiveSourceLocation_Exception_occurred_initializing_source_location__5;
	public static String ArchiveSourceLocation_Unable_to_locate_source_element_in_archive__0__1;
	public static String ArchiveSourceLocation_Exception_occurred_while_detecting_root_source_directory_in_archive__0__1;
	public static String ArchiveSourceLocation_Exception_occurred_while_detecting_root_source_directory_in_archive__0__2;

	public static String DirectorySourceLocation_Unable_to_create_memento_for_directory_source_location__0__1;
	public static String DirectorySourceLocation_Unable_to_initialize_source_location___missing_directory_path_3;
	public static String DirectorySourceLocation_Unable_to_initialize_source_location___directory_does_not_exist___0__4;
	public static String DirectorySourceLocation_Exception_occurred_initializing_source_location__5;

	public static String ScriptProjectSourceLocation_Unable_to_create_memento_for_Script_project_source_location__0__1;
	public static String ScriptProjectSourceLocation_Unable_to_initialize_source_location___missing_project_name_3;
	public static String ScriptProjectSourceLocation_Exception_occurred_initializing_source_location__4;


	public static String ProjectFragmentSourceLocation_Unable_to_create_memento_for_package_fragment_root_source_location__0__5;
	public static String ProjectFragmentSourceLocation_Unable_to_initialize_source_location___missing_handle_identifier_for_package_fragment_root__6;
	public static String ProjectFragmentSourceLocation_Unable_to_initialize_source_location___package_fragment_root_does_not_exist__7;
	public static String ProjectFragmentSourceLocation_Exception_occurred_initializing_source_location__8;
	
	
	public static String AbstractInterpreterRunner_0;
	public static String AbstractScriptLaunchConfigurationDelegate_20;

	public static String ProjectFragmentSourceContainerTypeDelegate_6;
	public static String ProjectFragmentSourceContainerTypeDelegate_7;
	public static String ProjectFragmentSourceContainerTypeDelegate_8;

	static {
		NLS.initializeMessages(BUNDLE_NAME, LaunchingMessages.class);
	}

	public static String StandardInterpreterRunner_Starting;
	public static String StandardInterpreterRunner;
	public static String StandardInterpreterRunner_Unable_to_locate_executable_for;
	
	public static String StandardInterpreterRunner_Launching_Interpreter;
	
	public static String NoDefaultInterpreterStatusHandler_title;
	public static String NoDefaultInterpreterStatusHandler_message;
	
	public static String ScriptRuntime_notDefaultInterpreter;
	
	public static String BadInterpreterStatusHandler_title;
	
	public static String ScriptLaunchShortcut_0;
	public static String ScriptLaunchShortcut_1;
	public static String ScriptLaunchShortcut_3;	
	public static String ScriptLaunchShortcut_Choose_a_main_script_to_launch;

	public static String LaunchShortcut_selectScriptToLaunch;
	public static String LaunchShortcut_selectionContainsNoScript;
	public static String LaunchShortcut_searchingForScripts;

	public static String StandardInterpreterRunner_Specified_working_directory_does_not_exist_or_is_not_a_directory___0__3;


	public static String StandardInterpreterRunner_Constructing_command_line____2;

	public static String ScriptRuntime_errNoContainerId;



}
