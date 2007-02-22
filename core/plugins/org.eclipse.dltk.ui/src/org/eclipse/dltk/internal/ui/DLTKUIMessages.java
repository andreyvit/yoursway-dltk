/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.ui;

import org.eclipse.osgi.util.NLS;

public final class DLTKUIMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.dltk.internal.ui.DLTKUIMessages";

	private DLTKUIMessages() {
	}

	static {
		NLS.initializeMessages(BUNDLE_NAME, DLTKUIMessages.class);
	}

	public static String ScriptPlugin_internal_error;
	public static String ScriptPlugin_initializing_ui;

	public static String ScriptElementProperties_name;

	public static String OpenTypeAction_description;
	public static String OpenTypeAction_tooltip;
	public static String OpenTypeAction_errorMessage;
	public static String OpenTypeAction_errorTitle;
	public static String OpenTypeAction_label;
	public static String OpenTypeAction_dialogTitle;
	public static String OpenTypeAction_dialogMessage;

	public static String ScriptUI_defaultDialogMessage;

	public static String MultiElementListSelectionDialog_pageInfoMessage;

	public static String MultiTypeSelectionDialog_dialogMessage;
	public static String MultiTypeSelectionDialog_dialogTitle;
	public static String MultiTypeSelectionDialog_errorMessage;
	public static String MultiTypeSelectionDialog_errorTitle;
	public static String MultiTypeSelectionDialog_error2Message;
	public static String MultiTypeSelectionDialog_error2Title;

	public static String TypeSelectionDialog_errorMessage;
	public static String TypeSelectionDialog_dialogMessage;
	public static String TypeSelectionDialog_errorTitle;
	public static String TypeSelectionDialog_lowerLabel;
	public static String TypeSelectionDialog_upperLabel;
	public static String TypeSelectionDialog_notypes_title;
	public static String TypeSelectionDialog_notypes_message;
	public static String TypeSelectionDialog_error3Message;
	public static String TypeSelectionDialog_error3Title;
	public static String TypeSelectionDialog_progress_consistency;
	public static String TypeSelectionDialog_error_type_doesnot_exist;

	public static String ExceptionDialog_seeErrorLogMessage;

	public static String MainTypeSelectionDialog_errorTitle;
	public static String MultiMainTypeSelectionDialog_errorTitle;

	public static String PackageSelectionDialog_error_title;
	public static String PackageSelectionDialog_nopackages_title;
	public static String PackageSelectionDialog_nopackages_message;

	public static String BuildPathDialog_title;

	public static String OverrideMethodDialog_groupMethodsByTypes;
	public static String OverrideMethodDialog_dialog_title;
	public static String OverrideMethodDialog_dialog_description;
	public static String OverrideMethodDialog_selectioninfo_more;
	public static String OverrideMethodDialog_link_tooltip;
	public static String OverrideMethodDialog_link_message;

	public static String GenerateHashCodeEqualsDialog_dialog_title;
	public static String GenerateHashCodeEqualsDialog_dialog_description;
	public static String GenerateHashCodeEqualsDialog_selectioninfo_more;
	public static String GenerateHashCodeEqualsDialog_no_entries;
	public static String GenerateHashCodeEqualsDialog_select_at_least_one_field;
	public static String GenerateHashCodeEqualsDialog_select_fields_to_include;

	public static String GetterSetterMethodDialog_link_tooltip;
	public static String GetterSetterMethodDialog_link_message;

	public static String GenerateConstructorDialog_link_tooltip;
	public static String GenerateConstructorDialog_link_message;

	public static String DelegateMethodDialog_link_tooltip;
	public static String DelegateMethodDialog_link_message;

	public static String ScriptImageLabelprovider_assert_wrongImage;

	public static String ScriptElementLabels_default_package;
	public static String ScriptElementLabels_anonym_type;
	public static String ScriptElementLabels_anonym;
	public static String ScriptElementLabels_import_container;
	public static String ScriptElementLabels_initializer;
	public static String ScriptElementLabels_category;
	public static String ScriptElementLabels_concat_string;
	public static String ScriptElementLabels_comma_string;
	public static String ScriptElementLabels_declseparator_string;
	public static String ScriptElementLabels_category_separator_string;

	public static String StatusBarUpdater_num_elements_selected;

	public static String OpenTypeHierarchyUtil_error_open_view;
	public static String OpenTypeHierarchyUtil_error_open_perspective;
	public static String OpenTypeHierarchyUtil_error_open_editor;
	public static String OpenTypeHierarchyUtil_selectionDialog_title;
	public static String OpenTypeHierarchyUtil_selectionDialog_message;

	public static String TypeInfoLabelProvider_default_package;

	public static String ScriptUIHelp_link_label;
	public static String ScriptUIHelpContext_javaHelpCategory_label;

	public static String ResourceTransferDragAdapter_cannot_delete_resource;
	public static String ResourceTransferDragAdapter_moving_resource;
	public static String ResourceTransferDragAdapter_cannot_delete_files;

	public static String Spelling_dictionary_file_extension;
	public static String Spelling_error_label;
	public static String Spelling_correct_label;
	public static String Spelling_add_info;
	public static String Spelling_add_label;
	public static String Spelling_ignore_info;
	public static String Spelling_ignore_label;
	public static String Spelling_case_label;
	public static String Spelling_error_case_label;

	public static String ScriptAnnotationHover_multipleMarkersAtThisLine;
	public static String ScriptEditor_codeassist_noCompletions;

	public static String HTMLTextPresenter_ellipsis;
	public static String HTML2TextReader_listItemPrefix;

	public static String OptionalMessageDialog_dontShowAgain;
	public static String ElementValidator_cannotPerform;
	public static String SelectionListenerWithASTManager_job_title;

	public static String ScriptOutlineControl_statusFieldText_hideInheritedMembers;
	public static String ScriptOutlineControl_statusFieldText_showInheritedMembers;

	public static String RenameSupport_not_available;
	public static String RenameSupport_dialog_title;

	public static String CoreUtility_job_title;
	public static String CoreUtility_buildall_taskname;
	public static String CoreUtility_buildproject_taskname;

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

	public static String InitializeAfterLoadJob_starter_job_name;

	public static String SortMembersMessageDialog_configure_preferences_tool_tip;

	public static String HistoryListAction_remove;
	public static String HistoryListAction_max_entries_constraint;
	public static String HistoryListAction_remove_all;

}
