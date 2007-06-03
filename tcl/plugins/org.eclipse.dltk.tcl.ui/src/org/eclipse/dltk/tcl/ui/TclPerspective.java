/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.ui;

import org.eclipse.dltk.tcl.internal.ui.TclUI;
import org.eclipse.dltk.tcl.internal.ui.wizards.TclFileCreationWizard;
import org.eclipse.dltk.tcl.internal.ui.wizards.TclProjectCreationWizard;
import org.eclipse.dltk.ui.wizards.NewPackageCreationWizard;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.progress.IProgressConstants;

public class TclPerspective implements IPerspectiveFactory {
	// TODO: move to general class like ScriptPerspective
	private static final String SCRIPT_EXPLORER = "org.eclipse.dltk.ui.ScriptExplorer";

	private static final String NEW_FOLDER_WIZARD = "org.eclipse.ui.wizards.new.folder"; //$NON-NLS-1$ 

	private static final String NEW_FILE_WIZARD = "org.eclipse.ui.wizards.new.file"; //$NON-NLS-1$

	private static final String NEW_UNTITLED_TEXT_FILE_WIZARD = "org.eclipse.ui.editors.wizards.UntitledTextFileWizard"; //$NON-NLS-1$

	public static final String ID_NEW_SOURCE_WIZARD = "org.eclipse.dltk.tcl.ui.wizards.NewSourceFolderCreationWizard";

	protected void addNewWizardShortcuts(IPageLayout layout) {
		layout.addNewWizardShortcut(TclProjectCreationWizard.ID_WIZARD);
		layout.addNewWizardShortcut(TclFileCreationWizard.ID_WIZARD);

		layout.addNewWizardShortcut(ID_NEW_SOURCE_WIZARD);
		layout.addNewWizardShortcut(NewPackageCreationWizard.ID_WIZARD);

		layout.addNewWizardShortcut(NEW_FOLDER_WIZARD);
		layout.addNewWizardShortcut(NEW_FILE_WIZARD);
		layout.addNewWizardShortcut(NEW_UNTITLED_TEXT_FILE_WIZARD);
	}

	protected void addShowViewShortcuts(IPageLayout layout) {
		layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
		layout.addShowViewShortcut(IPageLayout.ID_PROBLEM_VIEW);
		layout.addShowViewShortcut(IConsoleConstants.ID_CONSOLE_VIEW);

		layout.addShowViewShortcut(IPageLayout.ID_TASK_LIST);
		layout.addShowViewShortcut(IProgressConstants.PROGRESS_VIEW_ID);

		layout.addShowViewShortcut(SCRIPT_EXPLORER);
	}

	protected void addActionSets(IPageLayout layout) {
		layout.addActionSet(IPageLayout.ID_NAVIGATE_ACTION_SET);
		layout.addActionSet(TclUI.ID_ACTION_SET);
	}

	protected void addViews(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		IFolderLayout folder = layout.createFolder("right", IPageLayout.RIGHT,
				(float) 0.75, editorArea);

		folder.addView(IPageLayout.ID_OUTLINE);
		folder
				.addView("org.eclipse.dltk.tcl.internal.ui.navigation.PackagesView");
	}

	protected void createFolders(IPageLayout layout) {
		final String editorArea = layout.getEditorArea();

		// Folder
		IFolderLayout folder = layout.createFolder(
				"left", IPageLayout.LEFT, (float) 0.2, editorArea); //$NON-NLS-1$		

		folder.addView(SCRIPT_EXPLORER);
		folder.addPlaceholder(IPageLayout.ID_BOOKMARKS);

		// Output folder
		IFolderLayout outputFolder = layout.createFolder(
				"bottom", IPageLayout.BOTTOM, (float) 0.75, editorArea); //$NON-NLS-1$

		outputFolder.addView(IPageLayout.ID_PROBLEM_VIEW);
		outputFolder.addView(IPageLayout.ID_TASK_LIST);
		outputFolder.addView(IConsoleConstants.ID_CONSOLE_VIEW);

		outputFolder.addPlaceholder(IConsoleConstants.ID_CONSOLE_VIEW);
		outputFolder.addPlaceholder(IPageLayout.ID_BOOKMARKS);
		outputFolder.addPlaceholder(IProgressConstants.PROGRESS_VIEW_ID);
	}

	protected void addPerspectiveShotcuts(IPageLayout layout) {
		layout.addPerspectiveShortcut("org.eclipse.debug.ui.DebugPerspective");
	}

	public void createInitialLayout(IPageLayout layout) {
		createFolders(layout);
		addViews(layout);
		addActionSets(layout);
		addShowViewShortcuts(layout);
		addNewWizardShortcuts(layout);
		addPerspectiveShotcuts(layout);
	}
}
