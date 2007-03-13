/***************************************************************************************************
 * Copyright (c) 2003, 2006 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.dltk.internal.ui.navigator;

import org.eclipse.dltk.internal.ui.navigator.IExtensionStateConstants.Values;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.GenerateBuildPathActionGroup;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.actions.OpenViewActionGroup;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;
import org.eclipse.ui.navigator.IExtensionStateModel;

public class ScriptExplorerActionProvider extends CommonActionProvider {

	private static final int HIERARCHICAL_LAYOUT = 1;

	private static final int FLAT_LAYOUT = 2;

	private static final String TAG_LAYOUT = "org.eclipse.dltk.internal.ui.navigator.layout"; //$NON-NLS-1$

	private CommonLayoutActionGroup fLayoutActionGroup;
	private OpenViewActionGroup fOpenViewGroup;
	private GenerateBuildPathActionGroup fBuildPathGroup;

	private IExtensionStateModel fStateModel;

	private boolean fInViewPart = false;
	private boolean fHasFilledViewMenu = false;

	public ScriptExplorerActionProvider() {
	}

	public void fillActionBars(IActionBars actionBars) {
		if (!fHasFilledViewMenu) {
			fLayoutActionGroup.fillActionBars(actionBars);
			fHasFilledViewMenu = true;
		}

		if (fInViewPart) {
			fOpenViewGroup.fillActionBars(actionBars);
			fBuildPathGroup.fillActionBars(actionBars);
		}
	}

	public void fillContextMenu(IMenuManager menu) {
		if (fInViewPart) {
			fOpenViewGroup.fillContextMenu(menu);
			fBuildPathGroup.fillContextMenu(menu);
		}
	}

	public void init(ICommonActionExtensionSite site) {
		ICommonViewerWorkbenchSite workbenchSite = null;

		if (site.getViewSite() instanceof ICommonViewerWorkbenchSite) {
			workbenchSite = (ICommonViewerWorkbenchSite) site.getViewSite();
		}

		fStateModel = site.getExtensionStateModel();

		fLayoutActionGroup = new CommonLayoutActionGroup(site
				.getStructuredViewer(), fStateModel);

		if (workbenchSite != null) {
			if (workbenchSite.getPart() != null
					&& workbenchSite.getPart() instanceof IViewPart) {
				IViewPart viewPart = (IViewPart) workbenchSite.getPart();

				fOpenViewGroup = new OpenViewActionGroup(viewPart, site
						.getStructuredViewer()) {
					protected boolean getShowProperties() {
						return false;
					}
				};
				fBuildPathGroup = new GenerateBuildPathActionGroup(viewPart);
				fInViewPart = true;
			}
		}
	}

	public void setContext(ActionContext context) {
		super.setContext(context);
		if (fInViewPart) {
			fOpenViewGroup.setContext(context);
			fBuildPathGroup.setContext(context);
		}
	}

	public void restoreState(IMemento memento) {
		super.restoreState(memento);
		restoreLayoutState(memento);
	}

	private void restoreLayoutState(IMemento memento) {
		boolean isCurrentLayoutFlat = true;
		Integer state = null;
		if (memento != null)
			state = memento.getInteger(TAG_LAYOUT);

		// If no memento try an restore from preference store
		if (state == null) {
			IPreferenceStore store = DLTKUIPlugin.getDefault()
					.getPreferenceStore();
			state = new Integer(store.getInt(TAG_LAYOUT));
		}

		if (state.intValue() == FLAT_LAYOUT)
			isCurrentLayoutFlat = true;
		else if (state.intValue() == HIERARCHICAL_LAYOUT)
			isCurrentLayoutFlat = false;

		fStateModel.setBooleanProperty(Values.IS_LAYOUT_FLAT,
				isCurrentLayoutFlat);
		fLayoutActionGroup.setFlatLayout(isCurrentLayoutFlat);
	}

	public void saveState(IMemento aMemento) {
		super.saveState(aMemento);

		IPreferenceStore store = DLTKUIPlugin.getDefault().getPreferenceStore();
		if (fStateModel.getBooleanProperty(Values.IS_LAYOUT_FLAT))
			store.setValue(TAG_LAYOUT, FLAT_LAYOUT);
		else
			store.setValue(TAG_LAYOUT, HIERARCHICAL_LAYOUT);
	}
}
