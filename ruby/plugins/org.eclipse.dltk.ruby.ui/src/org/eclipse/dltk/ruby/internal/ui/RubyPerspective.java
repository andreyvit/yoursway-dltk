/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.ui;

import org.eclipse.dltk.ruby.internal.ui.wizards.RubyNewClassWizard;
import org.eclipse.dltk.ruby.internal.ui.wizards.RubyNewFileWizard;
import org.eclipse.dltk.ruby.internal.ui.wizards.RubyNewModuleWizard;
import org.eclipse.dltk.ruby.internal.ui.wizards.RubyNewProjectWizard;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.progress.IProgressConstants;

public class RubyPerspective implements IPerspectiveFactory {
	
	public static final String PERSPECTIVE_ID = "org.eclipse.dltk.ruby.ui.RubyPerspective";

	public void createInitialLayout(IPageLayout layout) {
		addFolders(layout);
		addActionSets(layout);
		addViews(layout);
		addShowViewShortcuts(layout);
		addNewWizardShortcuts(layout);
		addPerspectiveShotcuts(layout);
	}
	
	protected void addFolders(IPageLayout layout) {
		IFolderLayout leftFolder = layout.createFolder("left", IPageLayout.LEFT, (float) 0.2, layout.getEditorArea()); //$NON-NLS-1$
		leftFolder.addView("org.eclipse.dltk.ui.ScriptExplorer");
		leftFolder.addPlaceholder(IPageLayout.ID_BOOKMARKS);

		IFolderLayout bottomFolder = layout.createFolder("bottom", IPageLayout.BOTTOM, (float) 0.75, layout.getEditorArea()); //$NON-NLS-1$
		bottomFolder.addView(IPageLayout.ID_PROBLEM_VIEW);
		bottomFolder.addView(IPageLayout.ID_TASK_LIST);
		bottomFolder.addView("org.eclipse.dltk.ruby.ui.RubyDocumentationView");
		bottomFolder.addView(IConsoleConstants.ID_CONSOLE_VIEW);		
		bottomFolder.addPlaceholder(IPageLayout.ID_BOOKMARKS);
		bottomFolder.addPlaceholder(IProgressConstants.PROGRESS_VIEW_ID);
	}
	
	protected void addActionSets(IPageLayout layout) {
		layout.addActionSet(IPageLayout.ID_NAVIGATE_ACTION_SET);
		layout.addActionSet("org.eclipse.dltk.ruby.ui.RubyActionSet");
	}
	
	protected void addViews(IPageLayout layout) {
		layout.addView(IPageLayout.ID_OUTLINE, IPageLayout.RIGHT, (float) 0.75,
				layout.getEditorArea());
	}
	
	protected void addShowViewShortcuts(IPageLayout layout) {
		layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
		layout.addShowViewShortcut(IPageLayout.ID_PROBLEM_VIEW);
		layout.addShowViewShortcut("org.eclipse.dltk.ui.ScriptExplorer");
		layout.addShowViewShortcut(IPageLayout.ID_TASK_LIST);
		layout.addShowViewShortcut(IProgressConstants.PROGRESS_VIEW_ID);
	}

	protected void addNewWizardShortcuts(IPageLayout layout) {
		// Ruby
		layout.addNewWizardShortcut(RubyNewProjectWizard.WIZARD_ID);
		layout.addNewWizardShortcut(RubyNewClassWizard.WIZARD_ID);
		layout.addNewWizardShortcut(RubyNewModuleWizard.WIZARD_ID);
		layout.addNewWizardShortcut(RubyNewFileWizard.WIZARD_ID);

		// General
		layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.folder");//$NON-NLS-1$
		layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.file");//$NON-NLS-1$
		layout.addNewWizardShortcut("org.eclipse.ui.editors.wizards.UntitledTextFileWizard");//$NON-NLS-1$
	}
	
	protected void addPerspectiveShotcuts(IPageLayout layout) {
		layout.addPerspectiveShortcut("org.eclipse.debug.ui.DebugPerspective");
	}
}
