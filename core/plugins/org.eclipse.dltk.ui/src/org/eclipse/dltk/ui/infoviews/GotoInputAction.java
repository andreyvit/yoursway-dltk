/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.infoviews;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.dltk.ui.actions.OpenAction;
import org.eclipse.jface.action.Action;

class GotoInputAction extends Action {

	private AbstractInfoView fInfoView;

	public GotoInputAction(AbstractInfoView infoView) {
		Assert.isNotNull(infoView);
		fInfoView= infoView;

		DLTKPluginImages.setLocalImageDescriptors(this, "goto_input.gif"); //$NON-NLS-1$
		setText(InfoViewMessages.GotoInputAction_label);
		setToolTipText(InfoViewMessages.GotoInputAction_tooltip);
		setDescription(InfoViewMessages.GotoInputAction_description);

		//PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IJavaHelpContextIds.OPEN_INPUT_ACTION);
		System.err.println("TODO: add help support here"); //$NON-NLS-1$
	}

	public void run() {
		Object input = fInfoView.getInput();
		if (input instanceof IModelElement) {
			IModelElement inputElement= (IModelElement) input;
			new OpenAction(fInfoView.getViewSite()).run(new Object[] { inputElement });
		} 
	}
}
