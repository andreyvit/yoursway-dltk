/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.actions;

import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.jface.action.Action;




/**
 * This is an action template for actions that toggle whether
 * it links its selection to the active editor.
 * 
	 *
 */
public abstract class AbstractToggleLinkingAction extends Action {
	
	/**
	 * Constructs a new action.
	 */
	public AbstractToggleLinkingAction() {
		super(ActionMessages.ToggleLinkingAction_label); 
		setDescription(ActionMessages.ToggleLinkingAction_description); 
		setToolTipText(ActionMessages.ToggleLinkingAction_tooltip); 
		DLTKPluginImages.setLocalImageDescriptors(this, "synced.gif"); //$NON-NLS-1$
		//TODO: help! i need some body help! just not anybody help!
		//PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IDLTKHelpContextIds.LINK_EDITOR_ACTION);
	}

	/**
	 * Runs the action.
	 */
	public abstract void run();
}
