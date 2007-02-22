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
package org.eclipse.dltk.internal.ui.scriptview;

import org.eclipse.osgi.util.NLS;

public final class ScriptMessages extends NLS {

	private static final String BUNDLE_NAME= "org.eclipse.dltk.internal.ui.scriptview.ScriptMessages";//$NON-NLS-1$

	private ScriptMessages() {
		// Do not instantiate
	}

	public static String BuildGroup_buildProject;
	public static String BuildGroup_rebuildProject;
	public static String DragAdapter_deleting;
	public static String DragAdapter_problem;
	public static String DragAdapter_problemTitle;
	public static String DragAdapter_refreshing;
	public static String DropAdapter_alreadyExists;
	public static String DropAdapter_errorSame;
	public static String DropAdapter_errorSubfolder;
	public static String DropAdapter_errorTitle;
	public static String DropAdapter_errorMessage;
	public static String DropAdapter_question;
	public static String FilterSelectionAction_apply_label;
	public static String FilterSelectionAction_apply_toolTip;
	public static String FilterSelectionAction_dialog_title;
	public static String GotoPackage_action_label;
	public static String GotoPackage_dialog_message;
	public static String GotoPackage_dialog_title;
	public static String GotoPackage_action_description;
	public static String GotoRequiredProjectAction_label;
	public static String GotoRequiredProjectAction_description;
	public static String GotoRequiredProjectAction_tooltip;
	public static String GotoType_action_label;
	public static String GotoType_action_description;
	public static String GotoType_dialog_message;
	public static String GotoType_dialog_title;
	public static String GotoType_error_message;
	public static String GotoResource_action_label;
	public static String GotoResource_dialog_title;
	public static String OpenResource_action_description;
	public static String OpenResource_action_label;
	public static String OpenResource_error_message;
	public static String OpenResource_error_messageArgs;
	public static String OpenResource_error_messageProblems;
	public static String OpenResource_error_title;
	public static String Sorter_expectPackage;
	public static String ShowLibraries_hideReferencedLibs;
	public static String ShowLibraries_showReferencedLibs;
	public static String ShowBinaries_hideBinaryProjects;
	public static String ShowBinaries_showBinaryProjects;
	public static String ShowInNavigator_description;
	public static String ShowInNavigator_error;
	public static String ShowInNavigator_label;
	public static String PackageExplorer_filters;
	public static String PackageExplorer_gotoTitle;
	public static String PackageExplorer_openPerspective;
	public static String PackageExplorer_refactoringTitle;
	public static String PackageExplorer_referencedLibs;
	public static String PackageExplorer_binaryProjects;
	public static String PackageExplorer_title;
	public static String PackageExplorer_toolTip;
	public static String PackageExplorer_toolTip2;
	public static String PackageExplorer_toolTip3;
	public static String PackageExplorer_openWith;
	public static String PackageExplorer_element_not_present;
	public static String PackageExplorer_filteredDialog_title;
	public static String PackageExplorer_notFound;
	public static String PackageExplorer_removeFilters;
	public static String SelectionTransferDropAdapter_error_title;
	public static String SelectionTransferDropAdapter_error_message;
	public static String SelectionTransferDropAdapter_dialog_title;
	public static String SelectionTransferDropAdapter_dialog_preview_label;
	public static String SelectionTransferDropAdapter_dialog_question;
	public static String CollapseAllAction_label;
	public static String CollapseAllAction_tooltip;
	public static String CollapseAllAction_description;
	
	public static String LayoutActionGroup_label;
	public static String LayoutActionGroup_flatLayoutAction_label;
	public static String LayoutActionGroup_hierarchicalLayoutAction_label;
	public static String BuildPathContainer_unbound_label;
	public static String BuildPathContainer_unknown_label;
	public static String PackageExplorerPart_workspace;
	public static String PackageExplorerPart_workingSetModel;

	static {
		NLS.initializeMessages(BUNDLE_NAME, ScriptMessages.class);
	}
}
