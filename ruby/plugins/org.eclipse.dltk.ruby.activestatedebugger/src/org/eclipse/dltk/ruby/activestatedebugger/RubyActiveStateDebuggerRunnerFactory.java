/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.eclipse.dltk.ruby.activestatedebugger;

import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterRunner;
import org.eclipse.dltk.launching.IInterpreterRunnerFactory;

/**
 * Factory responsible for creating instances of ActiveState's ruby debugging
 * engine.
 */
public class RubyActiveStateDebuggerRunnerFactory implements
		IInterpreterRunnerFactory {

	public IInterpreterRunner createRunner(IInterpreterInstall install) {
		return new RubyActiveStateDebuggerRunner(install);
	}
}
