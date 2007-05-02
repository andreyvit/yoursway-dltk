/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.navigator;

import org.eclipse.dltk.internal.ui.actions.MultiActionGroup;
import org.eclipse.dltk.internal.ui.navigator.IExtensionStateConstants.Values;
import org.eclipse.dltk.internal.ui.scriptview.ScriptMessages;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.navigator.IExtensionStateModel;

/**
 * Adds view menus to switch between flat and hierarchical layout.
 */
public class CommonLayoutActionGroup extends MultiActionGroup {

	private IExtensionStateModel fStateModel;
	private StructuredViewer fStructuredViewer;

	private boolean fHasContributedToViewMenu = false;

	private IAction fHierarchicalLayout = null;
	private IAction fFlatLayoutAction = null;
	private IAction[] actions;

	private class CommonLayoutAction extends Action implements IAction {

		private final boolean fIsFlatLayout;

		public CommonLayoutAction(boolean flat) {
			super("", AS_RADIO_BUTTON); //$NON-NLS-1$
			fIsFlatLayout = flat;

			// TODO: Help support here
			// if (fIsFlatLayout)
			// PlatformUI.getWorkbench().getHelpSystem().setHelp(this,
			// IScriptHelpContextIds.LAYOUT_FLAT_ACTION);
			// else
			// PlatformUI.getWorkbench().getHelpSystem().setHelp(this,
			// IScriptHelpContextIds.LAYOUT_HIERARCHICAL_ACTION);
		}

		public void run() {
			if (fStateModel.getBooleanProperty(Values.IS_LAYOUT_FLAT) != fIsFlatLayout) {
				fStateModel.setBooleanProperty(Values.IS_LAYOUT_FLAT,
						fIsFlatLayout);

				fStructuredViewer.getControl().setRedraw(false);

				try {
					fStructuredViewer.refresh();
				} finally {
					fStructuredViewer.getControl().setRedraw(true);
				}
			}
		}
	}

	public CommonLayoutActionGroup(StructuredViewer structuredViewer,
			IExtensionStateModel stateModel) {
		super();
		fStateModel = stateModel;
		fStructuredViewer = structuredViewer;
	}

	public void fillActionBars(IActionBars actionBars) {
		super.fillActionBars(actionBars);
		if (!fHasContributedToViewMenu) {
			synchronized (this) {
				if (!fHasContributedToViewMenu) {
					fHasContributedToViewMenu = true;
					contributeToViewMenu(actionBars.getMenuManager());
				}
			}
		}
	}

	private void contributeToViewMenu(IMenuManager viewMenu) {
		final String layoutGroupName = "layout"; //$NON-NLS-1$
		IMenuManager layoutSubMenu = new MenuManager(ScriptMessages.LayoutActionGroup_label);
		
		viewMenu.add(new Separator());							
		viewMenu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		viewMenu.add(new Separator(layoutGroupName));
		viewMenu.appendToGroup(layoutGroupName, layoutSubMenu);
		viewMenu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS + "-end"));//$NON-NLS-1$		
		
		addActions(layoutSubMenu);
	}

	private IAction[] createActions() {
		// FlatLayoutAction
		fFlatLayoutAction = new CommonLayoutAction(true);
		fFlatLayoutAction.setText(ScriptMessages.LayoutActionGroup_flatLayoutAction_label);
		DLTKPluginImages.setLocalImageDescriptors(fFlatLayoutAction, "flatLayout.gif"); //$NON-NLS-1$

		// HierarchicalLayout
		fHierarchicalLayout = new CommonLayoutAction(false);
		fHierarchicalLayout.setText(ScriptMessages.LayoutActionGroup_hierarchicalLayoutAction_label);
		DLTKPluginImages.setLocalImageDescriptors(fHierarchicalLayout, "hierarchicalLayout.gif"); //$NON-NLS-1$

		return new IAction[] { fFlatLayoutAction, fHierarchicalLayout };
	}

	public void setFlatLayout(boolean flatLayout) {
		if (actions == null) {
			actions = createActions();
			setActions(actions, flatLayout ? 0 : 1);
		}
		
		fHierarchicalLayout.setChecked(!flatLayout);
		fFlatLayoutAction.setChecked(flatLayout);
	}
}
