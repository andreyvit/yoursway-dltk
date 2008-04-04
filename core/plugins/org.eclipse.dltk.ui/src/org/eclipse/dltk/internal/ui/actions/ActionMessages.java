/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.actions;

import org.eclipse.osgi.util.NLS;

public final class ActionMessages extends NLS {

	private static final String BUNDLE_NAME= "org.eclipse.dltk.internal.ui.actions.ActionMessages";//$NON-NLS-1$

	public static String DefaultEditorDescription_name;

	public static String OpenWithMenu_label;

	public static String RefactorActionGroup_noRefactoringAvailable;
	public static String RefactorMenu_label;
	public static String BuildPath_label;
	public static String BuildAction_label;
	public static String SelectionConverter_codeResolve_failed;
	public static String OpenAction_label;
	public static String OpenAction_tooltip;
	public static String OpenAction_description;
	public static String OpenAction_declaration_label;
	public static String OpenAction_select_element;
	public static String OpenAction_error_title;
	public static String OpenAction_error_message;
	public static String OpenAction_error_messageArgs;
	public static String OpenAction_error_messageProblems;
	public static String OpenAction_error_messageBadSelection;
	
	public static String NewWizardsActionGroup_new;
	public static String OpenProjectAction_dialog_title;
	public static String OpenProjectAction_dialog_message;
	public static String OpenProjectAction_error_message;

	public static String OpenTypeInHierarchyAction_label;
	public static String OpenTypeInHierarchyAction_description;
	public static String OpenTypeInHierarchyAction_tooltip;
	public static String OpenTypeInHierarchyAction_dialogMessage;
	public static String OpenTypeInHierarchyAction_dialogTitle;
	public static String RefreshAction_label;
	public static String RefreshAction_toolTip;
	public static String RefreshAction_progressMessage;
	public static String RefreshAction_error_title;
	public static String RefreshAction_error_message;
	public static String RefreshAction_locationDeleted_title;
	public static String RefreshAction_locationDeleted_message;
	public static String ActionUtil_notOnBuildPath_title;
	public static String ActionUtil_notOnBuildPath_message;
	public static String ActionUtil_notOnBuildPath_resource_message;
	public static String ActionUtil_not_possible;
	public static String ActionUtil_no_linked;
	public static String SelectAllAction_label;
	public static String SelectAllAction_tooltip;
	
	public static String ToggleLinkingAction_label;
	public static String ToggleLinkingAction_tooltip;
	public static String ToggleLinkingAction_description;

	static {
		NLS.initializeMessages(BUNDLE_NAME, ActionMessages.class);
	}

	public static String OpenNewSourceFolderWizardAction_text2;
	public static String OpenNewSourceFolderWizardAction_description;
	public static String OpenNewSourceFolderWizardAction_tooltip;
}
