/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.scriptview;

import org.eclipse.dltk.ui.ModelElementSorter;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IWorkingSet;


public class WorkingSetAwareModelElementSorter extends ModelElementSorter {
	
	public int compare(Viewer viewer, Object e1, Object e2) {
		if (e1 instanceof IWorkingSet || e2 instanceof IWorkingSet)
			return 0;

		return super.compare(viewer, e1, e2);
	}
}
