/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui;

import org.eclipse.osgi.util.NLS;

public final class DLTKUIMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.dltk.internal.ui.DLTKUIMessages"; //$NON-NLS-1$

	public static String FilteredTypesSelectionDialog_library_name_format;
	public static String FilteredTypeSelectionDialog_showContainerForDuplicatesAction;
	public static String FilteredTypesSelectionDialog_error_type_doesnot_exist;
	public static String FilteredTypesSelectionDialog_searchJob_taskName;
	public static String FilteredTypeSelectionDialog_titleFormat;
	public static String FilteredTypesSelectionDialog_dialogMessage;

	private DLTKUIMessages() {
	}

	static {
		NLS.initializeMessages(BUNDLE_NAME, DLTKUIMessages.class);
	}

	public static String ScriptPlugin_internal_error;
	public static String ScriptElementProperties_name;
	public static String OpenTypeAction_description;
	public static String OpenTypeAction_tooltip;
	public static String OpenTypeAction_errorMessage;
	public static String OpenTypeAction_errorTitle;
	public static String OpenTypeAction_label;
	public static String OpenTypeAction_dialogTitle;
	public static String OpenTypeAction_dialogMessage;
	public static String TypeSelectionDialog_dialogMessage;
	public static String TypeSelectionDialog_errorTitle;
	public static String TypeSelectionDialog_error3Message;
	public static String TypeSelectionDialog_error3Title;
	public static String TypeSelectionDialog_progress_consistency;
	public static String TypeSelectionDialog_error_type_doesnot_exist;

	public static String ExceptionDialog_seeErrorLogMessage;
	public static String StatusBarUpdater_num_elements_selected;
	public static String ScriptImageLabelprovider_assert_wrongImage;
	public static String OpenTypeHierarchyUtil_error_open_view;
	public static String OpenTypeHierarchyUtil_selectionDialog_title;
	public static String OpenTypeHierarchyUtil_selectionDialog_message;

	public static String TypeInfoLabelProvider_default_package;

	public static String ResourceTransferDragAdapter_cannot_delete_resource;
	public static String ResourceTransferDragAdapter_moving_resource;
	public static String ResourceTransferDragAdapter_cannot_delete_files;

	public static String CoreUtility_job_title;
	public static String CoreUtility_buildall_taskname;
	public static String CoreUtility_buildproject_taskname;
	
	public static String ScriptAnnotationHover_multipleMarkersAtThisLine;
	public static String ScriptEditor_codeassist_noCompletions;

	public static String HTMLTextPresenter_ellipsis;
	public static String HTML2TextReader_listItemPrefix;
	public static String OptionalMessageDialog_dontShowAgain;
	public static String RenameSupport_not_available;
	public static String RenameSupport_dialog_title;
	public static String TypeSelectionDialog2_title_format;

	public static String TypeSelectionComponent_label;
	public static String TypeSelectionComponent_menu;
	public static String TypeSelectionComponent_show_status_line_label;
	public static String TypeSelectionComponent_fully_qualify_duplicates_label;

	public static String TypeInfoViewer_job_label;
	public static String TypeInfoViewer_job_error;
	public static String TypeInfoViewer_job_cancel;
	public static String TypeInfoViewer_default_package;
	public static String TypeInfoViewer_progress_label;
	public static String TypeInfoViewer_searchJob_taskName;
	public static String TypeInfoViewer_syncJob_label;
	public static String TypeInfoViewer_syncJob_taskName;
	public static String TypeInfoViewer_progressJob_label;
	public static String TypeInfoViewer_remove_from_history;
	public static String TypeInfoViewer_separator_message;
	public static String TypeInfoViewer_library_name_format;
	
	public static String HistoryListAction_remove;
	public static String HistoryListAction_max_entries_constraint;
	public static String HistoryListAction_remove_all;
}
