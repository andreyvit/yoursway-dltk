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
package org.eclipse.dltk.ui.viewsupport;

import org.eclipse.dltk.ui.actions.MemberFilterActionGroup;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.PlatformUI;


/**
 * Action used to enable / disable method filter properties
 */
public class MemberFilterAction extends Action {

	private ModelElementFilter fFilter;

	private MemberFilterActionGroup fFilterActionGroup;

	/**
	 * Construct an action for MemberFilterActioGroup
	 * @param actionGroup object of MemberFilterActioGroup
	 * @param title title of action
	 * @param property One of MemberFilter.FILTER_*
	 * @param contextHelpId context id for help
	 * @param initValue initial state of filter
	 */
	public MemberFilterAction(MemberFilterActionGroup actionGroup,
			String title, ModelElementFilter filter, String contextHelpId, boolean initValue) {
		super(title);
		fFilterActionGroup = actionGroup;
		fFilter = filter;

		PlatformUI.getWorkbench().getHelpSystem().setHelp(this, contextHelpId);

		setChecked(initValue);
	}

	/**
	 * Returns this action's filter property.
	 */
	public ModelElementFilter getFilter() {
		return fFilter;
	}

	public void run() {		
		fFilterActionGroup.processMemberFilterAction(this);		
	}
}
