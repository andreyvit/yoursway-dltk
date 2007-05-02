/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.search;

import org.eclipse.dltk.internal.ui.actions.CompositeActionGroup;
import org.eclipse.dltk.internal.ui.actions.NavigateActionGroup;
import org.eclipse.dltk.internal.ui.actions.refactoring.RefactorActionGroup;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.actions.ActionGroup;


class NewSearchViewActionGroup extends CompositeActionGroup {
	NavigateActionGroup fNavigateActionGroup;
	
	public NewSearchViewActionGroup(IViewPart part) {
		setGroups(new ActionGroup[]{
			fNavigateActionGroup= new NavigateActionGroup(part),
//			new GenerateActionGroup(part), 
			new RefactorActionGroup(part),
//			new DLTKSearchActionGroup(part, toolkit) 
			});
	}
	
	public void handleOpen(OpenEvent event) {
		IAction openAction= fNavigateActionGroup.getOpenAction();
		if (openAction != null && openAction.isEnabled()) {
			openAction.run();
			return;
		}
	}
}

