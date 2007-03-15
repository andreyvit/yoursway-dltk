/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.javascript.ui.internal.actions;

import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorActionBarContributor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.MultiPageEditorActionBarContributor;
import org.eclipse.wst.javascript.ui.internal.editor.JSEditor;


public class MultiPageEditorActionBarContributorJS extends MultiPageEditorActionBarContributor {

	private ActionContributorJS sourceViewerActionContributor = null;
	private IActionBars fActionBars = null;

	public MultiPageEditorActionBarContributorJS() {
		super();

		sourceViewerActionContributor = new ActionContributorJS();
	}

	public void init(IActionBars actionBars) {
		fActionBars = actionBars;

		if (actionBars != null) {
			if (sourceViewerActionContributor != null)
				sourceViewerActionContributor.init(actionBars, getPage());
		}

		super.init(actionBars);
	}

	public void dispose() {
		if (sourceViewerActionContributor != null)
			sourceViewerActionContributor.dispose();
	}

	public void setActivePage(IEditorPart activeEditor) {
		if (sourceViewerActionContributor != null) {
			if (activeEditor != null && activeEditor instanceof JSEditor)
				sourceViewerActionContributor.setActiveEditor(activeEditor);
			else
				sourceViewerActionContributor.setActiveEditor(null);
		}

		IActionBars actionBars = getActionBars();
		if (actionBars != null) {
			// update menu bar and tool bar
			actionBars.updateActionBars();
		}
	}

	public IActionBars getActionBars() {
		return fActionBars;
	}
	
	// needed by JSMultiPageEditorPart
	public IEditorActionBarContributor getSourceViewerActionContributor() {
		return sourceViewerActionContributor;
	}
}
