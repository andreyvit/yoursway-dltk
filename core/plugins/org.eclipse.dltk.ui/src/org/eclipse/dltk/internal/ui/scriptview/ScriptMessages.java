/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.scriptview;

import org.eclipse.osgi.util.NLS;

public final class ScriptMessages extends NLS {

	private static final String BUNDLE_NAME= "org.eclipse.dltk.internal.ui.scriptview.ScriptMessages";//$NON-NLS-1$

	private ScriptMessages() {
		// Do not instantiate
	}

	public static String DragAdapter_deleting;
	public static String DragAdapter_problem;
	public static String DragAdapter_problemTitle;
	public static String DragAdapter_refreshing;
	public static String DropAdapter_errorTitle;
	public static String DropAdapter_errorMessage;
	public static String PackageExplorer_title;
	public static String PackageExplorer_toolTip;
	public static String PackageExplorer_toolTip2;
	public static String PackageExplorer_toolTip3;
	public static String PackageExplorer_filteredDialog_title;
	public static String PackageExplorer_notFound;
	public static String PackageExplorer_removeFilters;
	public static String SelectionTransferDropAdapter_error_title;
	public static String SelectionTransferDropAdapter_error_message;
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
