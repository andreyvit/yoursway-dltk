/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.search;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.Window;


public class FiltersDialogAction extends Action {
	private DLTKSearchResultPage fPage;
	
	public FiltersDialogAction(DLTKSearchResultPage page) {
		super(SearchMessages.FiltersDialogAction_label); 
		fPage= page;
	}

	public void run() {
		FiltersDialog dialog = new FiltersDialog(fPage);

		if (dialog.open() == Window.OK) {
			fPage.setFilters(dialog.getEnabledFilters());
			fPage.enableLimit(dialog.isLimitEnabled());
			fPage.setElementLimit(dialog.getElementLimit());
		}
	}

}
