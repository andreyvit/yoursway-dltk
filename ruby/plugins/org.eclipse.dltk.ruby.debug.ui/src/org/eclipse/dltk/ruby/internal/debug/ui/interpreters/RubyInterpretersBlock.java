/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.debug.ui.interpreters;

import org.eclipse.dltk.internal.debug.ui.interpreters.AddDLTKInterpreterDialog;
import org.eclipse.dltk.internal.debug.ui.interpreters.InterpretersBlock;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.ScriptRuntime;
import org.eclipse.dltk.ruby.core.RubyNature;

public class RubyInterpretersBlock extends InterpretersBlock {

	protected AddDLTKInterpreterDialog createInterpreterDialog(IInterpreterInstall standin) {
		AddRubyInterpreterDialog dialog = new AddRubyInterpreterDialog(this, 
				getShell(), ScriptRuntime.getInterpreterInstallTypes(getCurrentNature()), 
				standin);
		return dialog;
	}

	protected String getCurrentNature() {
		return RubyNature.NATURE_ID;
	}
}
 