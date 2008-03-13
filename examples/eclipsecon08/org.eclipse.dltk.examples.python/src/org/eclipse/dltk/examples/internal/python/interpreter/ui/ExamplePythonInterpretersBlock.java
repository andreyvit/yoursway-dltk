/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.examples.internal.python.interpreter.ui;

import org.eclipse.dltk.examples.internal.python.core.ExamplePythonNature;
import org.eclipse.dltk.internal.debug.ui.interpreters.AddScriptInterpreterDialog;
import org.eclipse.dltk.internal.debug.ui.interpreters.InterpretersBlock;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.ScriptRuntime;

public class ExamplePythonInterpretersBlock extends InterpretersBlock {
	protected AddScriptInterpreterDialog createInterpreterDialog(
			IInterpreterInstall standin) {
		ExampleAddPythonInterpreterDialog dialog = new ExampleAddPythonInterpreterDialog(
				this, getShell(), ScriptRuntime
						.getInterpreterInstallTypes(getCurrentNature()),
				standin);
		return dialog;
	}

	protected String getCurrentNature() {
		return ExamplePythonNature.PYTHON_NATURE;
	}
}
