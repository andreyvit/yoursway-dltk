/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.debug.ui.interpreters;

import org.eclipse.dltk.internal.debug.ui.interpreters.AbstractInterpreterComboBlock;
import org.eclipse.dltk.tcl.core.TclNature;
import org.eclipse.jface.preference.IPreferencePage;


public class TclInterpreterComboBlock extends AbstractInterpreterComboBlock {
	
	protected void showInterpreterPreferencePage()  { 
		IPreferencePage page = new TclInterpreterPreferencePage(); 
		showPrefPage("org.eclipse.dltk.tcl.debug.ui.interpreters.TclInterpreterPreferencePage", page); //$NON-NLS-1$
	}

	protected String getCurrentLanguageNature() {
		return TclNature.NATURE_ID;
	}
}
