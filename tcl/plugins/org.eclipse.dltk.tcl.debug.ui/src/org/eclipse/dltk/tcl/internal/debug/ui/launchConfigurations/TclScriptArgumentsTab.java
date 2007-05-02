/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.debug.ui.launchConfigurations;

import org.eclipse.dltk.debug.ui.launchConfigurations.ScriptArgumentsTab;
import org.eclipse.dltk.internal.debug.ui.launcher.InterpreterArgumentsBlock;

public class TclScriptArgumentsTab extends ScriptArgumentsTab {

	protected InterpreterArgumentsBlock createInterpreterArgsBlock() {
		return null;
	}
}
