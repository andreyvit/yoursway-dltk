/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.search;

import org.eclipse.core.resources.IResource;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.ui.ScriptElementLabels;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.graphics.Image;


public class SortingLabelProvider extends SearchLabelProvider implements IColorProvider {
	public static final int SHOW_ELEMENT_CONTAINER= 1; // default
	public static final int SHOW_CONTAINER_ELEMENT= 2;
	public static final int SHOW_PATH= 3;
	
	public SortingLabelProvider(DLTKSearchResultPage page) {
		super(page);
	}	

	public Image getImage(Object element) {
		Image image= null;
		if (element instanceof IModelElement || element instanceof IResource)
			image= super.getImage(element);
		if (image != null)
			return image;
		return getParticipantImage(element);
	}
		
	public final String getText(Object element) {
		return getLabelWithCounts(element, internalGetText(element));
	}

	private String internalGetText(Object o) {
//		if (o instanceof IImportDeclaration)
//			o= ((IImportDeclaration)o).getParent().getParent();

		String text= super.getText(o);
		if (text != null && (text.length() > 0))
			return text;
		return getParticipantText(o);	
	}

	public void setOrder(int orderFlag) {
		long flags= DEFAULT_SEARCH_TEXTFLAGS;
		if (orderFlag == SHOW_ELEMENT_CONTAINER)
			flags |= ScriptElementLabels.F_POST_QUALIFIED | ScriptElementLabels.M_POST_QUALIFIED | ScriptElementLabels.I_POST_QUALIFIED | ScriptElementLabels.M_PARAMETER_TYPES
							| ScriptElementLabels.T_POST_QUALIFIED | ScriptElementLabels.D_POST_QUALIFIED | ScriptElementLabels.CF_POST_QUALIFIED  | ScriptElementLabels.CU_POST_QUALIFIED;
			
		else if (orderFlag == SHOW_CONTAINER_ELEMENT)
			flags |= ScriptElementLabels.F_FULLY_QUALIFIED | ScriptElementLabels.M_FULLY_QUALIFIED | ScriptElementLabels.I_FULLY_QUALIFIED | ScriptElementLabels.M_PARAMETER_TYPES
				| ScriptElementLabels.T_FULLY_QUALIFIED | ScriptElementLabels.D_QUALIFIED | ScriptElementLabels.CF_QUALIFIED  | ScriptElementLabels.CU_QUALIFIED;
		else if (orderFlag == SHOW_PATH) {
			flags |= ScriptElementLabels.F_FULLY_QUALIFIED | ScriptElementLabels.M_FULLY_QUALIFIED | ScriptElementLabels.I_FULLY_QUALIFIED | ScriptElementLabels.M_PARAMETER_TYPES
				| ScriptElementLabels.T_FULLY_QUALIFIED | ScriptElementLabels.D_QUALIFIED | ScriptElementLabels.CF_QUALIFIED  | ScriptElementLabels.CU_QUALIFIED;
			flags |= ScriptElementLabels.PREPEND_ROOT_PATH;
		}
		setTextFlags(flags);
	}
}
