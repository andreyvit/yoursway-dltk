/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.search;

import org.eclipse.osgi.util.NLS;

public final class SearchMessages extends NLS {

	private static final String BUNDLE_NAME= "org.eclipse.dltk.internal.ui.search.SearchMessages";//$NON-NLS-1$

	private SearchMessages() {
		// Do not instantiate
	}

	public static String DLTKSearchResultPage_preferences_label;
	public static String DLTKSearchScopeFactory_undefined_projects;
	public static String DLTKSearchScopeFactory_undefined_selection;
	public static String DLTKSearchScopeFactory_undefined_workingsets;
	public static String SearchLabelProvider_exact_singular;
	public static String SearchLabelProvider_exact_noCount;
	public static String SearchLabelProvider_exact_and_potential_plural;
	public static String SearchLabelProvider_potential_singular;
	public static String SearchLabelProvider_potential_noCount;
	public static String SearchLabelProvider_potential_plural;
	public static String SearchLabelProvider_exact_plural;
	public static String group_search;
	public static String group_declarations;
	public static String group_references;
	public static String Search_Error_search_title;
	public static String Search_Error_search_message;
	public static String Search_Error_search_notsuccessful_message;
	public static String Search_Error_search_notsuccessful_title;
	public static String Search_Error_openEditor_title;
	public static String Search_Error_openEditor_message;
	public static String Search_Error_codeResolve;
	public static String SearchElementSelectionDialog_title;
	public static String SearchElementSelectionDialog_message;
	public static String SearchPage_searchFor_label;
	public static String SearchPage_searchFor_type;
	public static String SearchPage_searchFor_method;
	public static String SearchPage_searchFor_field;
	public static String SearchPage_limitTo_label;
	public static String SearchPage_limitTo_declarations;
	public static String SearchPage_limitTo_references;
	public static String SearchPage_limitTo_allOccurrences;
	public static String SearchPage_expression_label;
	public static String SearchPage_expression_caseSensitive;
	public static String SearchPage_searchInterpreterEnvironment_label;
	public static String SearchUtil_workingSetConcatenation;
	public static String Search_FindDeclarationAction_label;
	public static String Search_FindDeclarationAction_tooltip;
	public static String Search_FindDeclarationsInProjectAction_label;
	public static String Search_FindDeclarationsInProjectAction_tooltip;
	public static String Search_FindDeclarationsInWorkingSetAction_label;
	public static String Search_FindDeclarationsInWorkingSetAction_tooltip;
	public static String Search_FindHierarchyDeclarationsAction_label;
	public static String Search_FindHierarchyDeclarationsAction_tooltip;
	public static String Search_FindReferencesAction_label;
	public static String Search_FindReferencesAction_tooltip;
	public static String Search_FindReferencesInProjectAction_label;
	public static String Search_FindReferencesInProjectAction_tooltip;
	public static String Search_FindReferencesInWorkingSetAction_label;
	public static String Search_FindReferencesInWorkingSetAction_tooltip;
	public static String Search_FindHierarchyReferencesAction_label;
	public static String Search_FindHierarchyReferencesAction_tooltip;
	public static String DLTKSearchOperation_singularDeclarationsPostfix;
	public static String DLTKSearchOperation_singularReferencesPostfix;
	public static String DLTKSearchOperation_singularOccurrencesPostfix;
	public static String DLTKSearchOperation_pluralDeclarationsPostfix;
	public static String DLTKSearchOperation_pluralReferencesPostfix;
	public static String DLTKSearchOperation_pluralOccurrencesPostfix;
	public static String DLTKElementAction_typeSelectionDialog_title;
	public static String DLTKElementAction_typeSelectionDialog_message;
	public static String DLTKElementAction_error_open_message;
	public static String DLTKElementAction_operationUnavailable_title;
	public static String DLTKElementAction_operationUnavailable_generic;
	public static String WorkspaceScope;
	public static String WorkspaceScopeNoInterpreterEnvironment;
	public static String SingleWorkingSetScope;
	public static String SingleWorkingSetScopeNoInterpreterEnvironment;
	public static String DoubleWorkingSetScope;
	public static String DoubleWorkingSetScopeNoInterpreterEnvironment;
	public static String WorkingSetsScope;
	public static String WorkingSetsScopeNoInterpreterEnvironment;
	
	public static String SelectionScope;
	public static String SelectionScopeNoInterpreterEnvironment;
	public static String SingleSelectionScope;
	public static String SingleSelectionScopeNoInterpreterEnvironment;
	public static String DoubleSelectionScope;
	public static String DoubleSelectionScopeNoInterpreterEnvironment;
	public static String EnclosingProjectsScope;
	public static String EnclosingProjectsScopeNoInterpreterEnvironment;
	public static String EnclosingProjectsScope2;
	public static String EnclosingProjectsScope2NoInterpreterEnvironment;
	public static String EnclosingProjectScope;
	public static String EnclosingProjectScopeNoInterpreterEnvironment;
	public static String ProjectScope;
	public static String ProjectScopeNoInterpreterEnvironment;
	public static String HierarchyScope;
	public static String DLTKSearchResultPage_sortByName;
	public static String DLTKSearchResultPage_sortByPath;
	public static String DLTKSearchResultPage_open_editor_error_title;
	public static String DLTKSearchResultPage_open_editor_error_message;
	public static String DLTKSearchResultPage_sortByParentName;
	public static String DLTKSearchResultPage_filtered_message;
	public static String DLTKSearchResultPage_sortBylabel;
	public static String DLTKSearchResultPage_error_marker;
	public static String DLTKSearchResultPage_groupby_project;
	public static String DLTKSearchResultPage_groupby_project_tooltip;
	public static String DLTKSearchResultPage_groupby_package;
	public static String DLTKSearchResultPage_groupby_package_tooltip;
	public static String DLTKSearchResultPage_filteredWithCount_message;
	public static String DLTKSearchResultPage_groupby_file;
	public static String DLTKSearchResultPage_groupby_file_tooltip;
	public static String DLTKSearchResultPage_groupby_type;
	public static String DLTKSearchResultPage_groupby_type_tooltip;
	public static String DLTKSearchQuery_task_label;
	public static String DLTKSearchQuery_label;
	public static String DLTKSearchQuery_error_unsupported_pattern;
	public static String DLTKSearchQuery_status_ok_message;
	public static String DLTKSearchQuery_error_participant_estimate;
	public static String DLTKSearchQuery_error_participant_search;
	public static String SearchParticipant_error_noID;
	public static String SearchParticipant_error_noNature;
	public static String SearchParticipant_error_noClass;
	public static String SearchParticipant_error_classCast;
	public static String MatchFilter_WriteFilter_name;
	public static String MatchFilter_WriteFilter_actionLabel;
	public static String MatchFilter_WriteFilter_description;
	public static String MatchFilter_ReadFilter_name;
	public static String MatchFilter_ReadFilter_actionLabel;
	public static String MatchFilter_ReadFilter_description;
	public static String MatchFilter_DLTKdocFilter_name;
	public static String MatchFilter_DLTKdocFilter_actionLabel;
	public static String MatchFilter_DLTKdocFilter_description;
	public static String TextSearchLabelProvider_matchCountFormat;
	public static String FiltersDialog_title;
	public static String FiltersDialog_filters_label;
	public static String FiltersDialog_description_label;
	public static String FiltersDialog_limit_label;
	public static String FiltersDialog_limit_error;
	public static String FiltersDialogAction_label;

	static {
		NLS.initializeMessages(BUNDLE_NAME, SearchMessages.class);
	}

	public static String DLTKSearchQuery_error_element_does_not_exist;
	public static String MatchFilter_PotentialFilter_name;
	public static String MatchFilter_PotentialFilter_actionLabel;
	public static String MatchFilter_PotentialFilter_description;
}
