/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.scriptview;

import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.internal.ui.actions.MultiActionGroup;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchActionConstants;


/**
 * Adds view menus to switch between flat and hierarchical layout.
 * 
	 *
 */
class LayoutActionGroup extends MultiActionGroup {

	LayoutActionGroup(ScriptExplorerPart packageExplorer) {
		super(createActions(packageExplorer), getSelectedState(packageExplorer));
	}

	/* (non-Javadoc)
	 * @see ActionGroup#fillActionBars(IActionBars)
	 */
	public void fillActionBars(IActionBars actionBars) {
		super.fillActionBars(actionBars);
		contributeToViewMenu(actionBars.getMenuManager());
	}
	
	private void contributeToViewMenu(IMenuManager viewMenu) {
		viewMenu.add(new Separator());

		// Create layout sub menu
		
		IMenuManager layoutSubMenu= new MenuManager(ScriptMessages.LayoutActionGroup_label); 
		final String layoutGroupName= "layout"; //$NON-NLS-1$
		Separator marker= new Separator(layoutGroupName);

		viewMenu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		viewMenu.add(marker);
		viewMenu.appendToGroup(layoutGroupName, layoutSubMenu);
		viewMenu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS+"-end"));//$NON-NLS-1$		
		addActions(layoutSubMenu);
	}

	static int getSelectedState(ScriptExplorerPart packageExplorer) {
		if (packageExplorer.isFlatLayout())
			return 0;
		else
			return 1;
	}
	
	static IAction[] createActions(ScriptExplorerPart packageExplorer) {
		IAction flatLayoutAction= new LayoutAction(packageExplorer, true);
		flatLayoutAction.setText(ScriptMessages.LayoutActionGroup_flatLayoutAction_label); 
		DLTKPluginImages.setLocalImageDescriptors(flatLayoutAction, "flatLayout.gif"); //$NON-NLS-1$
		IAction hierarchicalLayout= new LayoutAction(packageExplorer, false);
		hierarchicalLayout.setText(ScriptMessages.LayoutActionGroup_hierarchicalLayoutAction_label);	  
		DLTKPluginImages.setLocalImageDescriptors(hierarchicalLayout, "hierarchicalLayout.gif"); //$NON-NLS-1$
		
		return new IAction[]{flatLayoutAction, hierarchicalLayout};
	}
}

class LayoutAction extends Action implements IAction {

	private boolean fIsFlatLayout;
	private ScriptExplorerPart fPackageExplorer;

	public LayoutAction(ScriptExplorerPart packageExplorer, boolean flat) {
		super("", AS_RADIO_BUTTON); //$NON-NLS-1$

		fIsFlatLayout= flat;
		fPackageExplorer= packageExplorer;
		if (DLTKCore.DEBUG) {
			System.err.println("Add help support here...");
		}		
//		if (fIsFlatLayout)
//			PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IScriptHelpContextIds.LAYOUT_FLAT_ACTION);
//		else
//			PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IScriptHelpContextIds.LAYOUT_HIERARCHICAL_ACTION);
	}

	/*
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		if (fPackageExplorer.isFlatLayout() != fIsFlatLayout)
			fPackageExplorer.toggleLayout();
	}
}
