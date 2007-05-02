/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.typehierarchy;

import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.internal.corext.util.Messages;
import org.eclipse.dltk.ui.ScriptElementImageProvider;
import org.eclipse.dltk.ui.ScriptElementLabels;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * Action used for the type hierarchy forward / backward buttons
 */
public class HistoryAction extends Action {

	private TypeHierarchyViewPart fViewPart;
	private IModelElement fElement;
	
	public HistoryAction(TypeHierarchyViewPart viewPart, IModelElement element) {
        super("", AS_RADIO_BUTTON); //$NON-NLS-1$
		fViewPart= viewPart;
		fElement= element;		
		
		String elementName= ScriptElementLabels.getDefault().getElementLabel(element, ScriptElementLabels.ALL_POST_QUALIFIED | ScriptElementLabels.ALL_DEFAULT);
		setText(elementName);
		setImageDescriptor(getImageDescriptor(element));
				
		setDescription(Messages.format(TypeHierarchyMessages.HistoryAction_description, elementName)); 
		setToolTipText(Messages.format(TypeHierarchyMessages.HistoryAction_tooltip, elementName)); 
		//PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IJavaHelpContextIds.HISTORY_ACTION);
	}
	
	private ImageDescriptor getImageDescriptor(IModelElement elem) {
		ScriptElementImageProvider imageProvider= new ScriptElementImageProvider();
		ImageDescriptor desc= imageProvider.getBaseImageDescriptor(elem, 0);
		imageProvider.dispose();
		return desc;
	}
	
	/*
	 * @see Action#run()
	 */
	public void run() {
		fViewPart.gotoHistoryEntry(fElement);
	}
	
}
