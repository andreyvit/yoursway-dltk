/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.ui.wizards;

import org.eclipse.dltk.tcl.core.TclNature;
import org.eclipse.dltk.ui.wizards.NewSourceModulePage;


public class TclFileCreationPage extends NewSourceModulePage {

	protected String getRequiredNature() {
		return TclNature.NATURE_ID;
	}

	protected String getPageDescription() {
		return "This wizard creates a new Tcl file.";
	}

	protected String getPageTitle() {		
		return "Create new Tcl file";
	}
}
