/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.core.util;

import java.text.MessageFormat;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.dltk.internal.core.util.messages";//$NON-NLS-1$

	public static String hierarchy_nullProject;
	public static String hierarchy_nullRegion;
	public static String hierarchy_nullFocusType;
	public static String hierarchy_creating;
	public static String hierarchy_creatingOnType;
	
	
	public static String build_readStateProgress;
	public static String build_wrongFileFormat;
	
	public static String buildpath_buildPath;
	public static String buildpath_cannotNestEntryInEntry;
	public static String buildpath_cannotNestEntryInEntryNoExclusion;
	public static String buildpath_cannotNestEntryInLibrary;
	public static String buildpath_cannotReadBuildpathFile;
	public static String buildpath_cannotReferToItself;
	public static String buildpath_closedProject;
	public static String buildpath_couldNotWriteBuildpathFile;
	public static String buildpath_cycle;
	public static String buildpath_disabledInclusionExclusionPatterns;
	public static String buildpath_duplicateEntryPath;
	public static String buildpath_illegalContainerPath;
	public static String buildpath_illegalEntryInBuildpathFile;
	public static String buildpath_illegalLibraryPath;
	public static String buildpath_illegalLibraryArchive;
	public static String buildpath_illegalExternalFolder;
	public static String buildpath_illegalProjectPath;
	public static String buildpath_illegalSourceFolderPath;
	public static String buildpath_invalidBuildpathInBuildpathFile;
	public static String buildpath_invalidContainer;
	public static String buildpath_mustEndWithSlash;
	public static String buildpath_unboundContainerPath;
	public static String buildpath_unboundProject;
	public static String buildpath_settingProgress;
	public static String buildpath_unboundSourceFolder;
	public static String buildpath_unknownKind;
	public static String buildpath_xmlFormatError;
	public static String buildpath_duplicateEntryExtraAttribute;
	
	//convention
	public static String convention_unit_nullName;
	public static String convention_unit_notScriptName;						
	
	//status
	public static String status_cannotUseDeviceOnPath;
	public static String status_coreException;
	public static String status_IOException;
	public static String status_indexOutOfBounds;
	public static String status_invalidContents;
	public static String status_invalidDestination;
	public static String status_invalidName;
	public static String status_invalidPackage;
	public static String status_invalidPath;
	public static String status_invalidProject;
	public static String status_invalidResource;
	public static String status_invalidResourceType;
	public static String status_invalidSibling;
	public static String status_nameCollision;
	public static String status_noLocalContents;
	public static String status_readOnly;
	public static String status_targetException;	
	public static String status_updateConflict;
	
	//
	public static String element_doesNotExist;
	public static String element_notOnClasspath;
	public static String element_reconciling;
	public static String element_nullName;
	
	
	// operations
	public static String operation_needElements;
	public static String operation_needName;
	public static String operation_needPath;
	public static String operation_needAbsolutePath;
	public static String operation_needString;
	public static String operation_notSupported;
	public static String operation_nullContainer;
	public static String operation_nullName;
	public static String operation_copyElementProgress;
	public static String operation_moveElementProgress;
	public static String operation_renameElementProgress;
	public static String operation_copyResourceProgress;
	public static String operation_moveResourceProgress;
	public static String operation_renameResourceProgress;
	public static String operation_createScriptFolderProgress;
	public static String operation_deleteResourceProgress;
	public static String operation_pathOutsideProject;
	public static String restrictedAccess_project;
	public static String restrictedAccess_library;
	public static String restrictedAccess_constructor_project;
	public static String restrictedAccess_constructor_library;
	public static String restrictedAccess_field_project;
	public static String restrictedAccess_field_library;
	public static String restrictedAccess_method_project;
	public static String restrictedAccess_method_library;
	
	public static String file_badFormat;
	public static String file_notFound;
	
	public static String path_mustBeAbsolute;

	public static String savedState_jobName;
	
	public static String workingCopy_commit;
	
	public static String cache_invalidLoadFactor;
	
	public static String manager_filesToIndex;
	public static String manager_indexingInProgress;
	
	public static String exception_wrongFormat;
	
	
	public static String engine_searching;
	public static String engine_searching_indexing;
	public static String engine_searching_matching;

	public static String process_name;
	
	
	public static String build_saveStateProgress;
	public static String build_saveStateComplete;
	public static String build_cannotSaveState;
	public static String build_cannotSaveStates;
	
	private Messages() {
		// Do not instantiate
	}
	
	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
	
	/**
	 * Bind the given message's substitution locations with the given string values.
	 * 
	 * @param message the message to be manipulated
	 * @return the manipulated String
	 */
	public static String bind(String message) {
		return bind(message, null);
	}
	
	/**
	 * Bind the given message's substitution locations with the given string values.
	 * 
	 * @param message the message to be manipulated
	 * @param binding the object to be inserted into the message
	 * @return the manipulated String
	 */
	public static String bind(String message, Object binding) {
		return bind(message, new Object[] {binding});
	}	
	/**
	 * Bind the given message's substitution locations with the given string values.
	 * 
	 * @param message the message to be manipulated
	 * @param binding1 An object to be inserted into the message
	 * @param binding2 A second object to be inserted into the message
	 * @return the manipulated String
	 */
	public static String bind(String message, Object binding1, Object binding2) {
		return bind(message, new Object[] {binding1, binding2});
	}

	/**
	 * Bind the given message's substitution locations with the given string values.
	 * 
	 * @param message the message to be manipulated
	 * @param bindings An array of objects to be inserted into the message
	 * @return the manipulated String
	 */
	public static String bind(String message, Object[] bindings) {
		return MessageFormat.format(message, bindings);
	}
}
