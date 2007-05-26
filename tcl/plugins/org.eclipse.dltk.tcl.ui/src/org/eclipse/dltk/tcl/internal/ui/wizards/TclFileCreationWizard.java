/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.ui.wizards;

import org.eclipse.dltk.ui.wizards.NewSourceModulePage;
import org.eclipse.dltk.ui.wizards.NewSourceModuleWizard;

public class TclFileCreationWizard extends NewSourceModuleWizard {
	public static final String ID_WIZARD = "org.eclipse.dltk.tcl.internal.ui.wizards.TclFileCreationWizard";
	
	protected NewSourceModulePage createNewSourceModulePage() {
		return new TclFileCreationPage();
	}
}
