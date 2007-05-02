/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.ui.infoviews;

import org.eclipse.dltk.tcl.core.TclNature;
import org.eclipse.dltk.tcl.internal.ui.TclUI;
import org.eclipse.dltk.ui.infoviews.AbstractDocumentationView;
import org.eclipse.jface.preference.IPreferenceStore;


public class TclDocumentationView extends AbstractDocumentationView {
	protected IPreferenceStore getPreferenceStore() {
		return TclUI.getDefault().getPreferenceStore();
	}

	protected String getNature() {
		return TclNature.NATURE_ID;
	}
}
