/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.debug.ui.interpreters;

import java.text.MessageFormat;

import org.eclipse.dltk.debug.ui.messages.ScriptLaunchMessages;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.ScriptRuntime;

/**
 * Interpreter Descriptor used for the Interpreter container wizard page.
 */
public class BuildInterpreterDescriptor extends InterpreterDescriptor {
	
	private String fNature;
	
	public BuildInterpreterDescriptor(String nature) {
		fNature = nature;
	}
		
	public String getDescription() {
		String name = ScriptLaunchMessages.InterpreterTab_7;
		IInterpreterInstall Interpreter = ScriptRuntime.getDefaultInterpreterInstall(fNature);
		if (Interpreter != null) {
			name = Interpreter.getName();
		}
		return MessageFormat.format(ScriptLaunchMessages.InterpreterTab_8, new String[] {
			name
		});
	}
}
