/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.debug.ui.interpreters;

import org.eclipse.dltk.internal.debug.ui.interpreters.AbstractInterpreterEnvironmentVariablesBlock;
import org.eclipse.dltk.internal.debug.ui.interpreters.AbstractInterpreterLibraryBlock;
import org.eclipse.dltk.internal.debug.ui.interpreters.AddScriptInterpreterDialog;
import org.eclipse.dltk.internal.debug.ui.interpreters.IAddInterpreterDialogRequestor;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterInstallType;
import org.eclipse.dltk.tcl.internal.core.packages.PackagesManager;
import org.eclipse.swt.widgets.Shell;

public class AddTclInterpreterDialog extends AddScriptInterpreterDialog {
	public AddTclInterpreterDialog(IAddInterpreterDialogRequestor requestor,
			Shell shell, IInterpreterInstallType[] interpreterInstallTypes,
			IInterpreterInstall editedInterpreter) {
		super(requestor, shell, interpreterInstallTypes, editedInterpreter);
	}

	protected AbstractInterpreterLibraryBlock createLibraryBlock(
			AddScriptInterpreterDialog dialog) {
		return new TclInterpreterLibraryBlock(dialog);
	}

	protected AbstractInterpreterEnvironmentVariablesBlock createEnvironmentVariablesBlock(
			AddScriptInterpreterDialog dialog) {
		return new TclInterpreterEnvironmentVariablesBlock(dialog);
	}

	protected boolean useInterpreterArgs() {
		return false;
	}

	protected boolean isRediscoverSupported() {
		return false;
	}

	protected void okPressed() {
		super.okPressed();
		// Remove all information for packages infrastructure for this
		// interpreter.
		IInterpreterInstall install = getLastInterpreterInstall();
		if( install != null ) {
			PackagesManager.getInstance().removeInterprterInfo(install);
		}
	}
}
