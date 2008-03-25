/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.viewsupport;

import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.jface.viewers.Viewer;

public class ModelElementFlagsFilter extends AbstractModelElementFilter {
	private int fFlags;

	public ModelElementFlagsFilter(int flags) {
		this.fFlags = flags;
	}

	public String getFilteringType() {
		return "ModelElementFlagsFilter:" + Integer.toString(fFlags);
	}

	public boolean isFilterProperty(Object element, Object property) {
		return false;
	}

	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (element instanceof IMember) {
			IMember member = (IMember) element;
			try {
				if ((member.getFlags() & this.fFlags) != 0) {
					return false;
				}
			} catch (ModelException e) {
				if (DLTKCore.DEBUG) {
					e.printStackTrace();
				}
			}
		}

		return true;
	}
}
