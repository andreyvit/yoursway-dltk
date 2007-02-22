/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.scriptview;

import org.eclipse.dltk.internal.ui.actions.AbstractToggleLinkingAction;
import org.eclipse.dltk.ui.IScriptExplorerViewPart;


/**
 * This action toggles whether this package explorer links its selection to the active
 * editor.
 * 
	 *
 */
public class ToggleLinkingAction extends AbstractToggleLinkingAction {
	
	private IScriptExplorerViewPart fPackageExplorerPart;
	
	/**
	 * Constructs a new action.
	 */
	public ToggleLinkingAction(IScriptExplorerViewPart explorer) {
		setChecked(explorer.isLinkingEnabled());
		fPackageExplorerPart= explorer;
	}

	/**
	 * Runs the action.
	 */
	public void run() {
		fPackageExplorerPart.setLinkingEnabled(isChecked());
	}

}
