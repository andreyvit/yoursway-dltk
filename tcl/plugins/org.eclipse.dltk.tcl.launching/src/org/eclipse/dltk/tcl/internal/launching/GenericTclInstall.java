/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.launching;

import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.dltk.launching.AbstractInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterInstallType;
import org.eclipse.dltk.launching.IInterpreterRunner;
import org.eclipse.dltk.tcl.core.TclNature;

public class GenericTclInstall extends AbstractInterpreterInstall {

	public GenericTclInstall(IInterpreterInstallType type, String id) {
		super(type, id);
	}

	public IInterpreterRunner getInterpreterRunner(String mode) {
		if (mode.equals(ILaunchManager.RUN_MODE)) {
			return new TclInterpreterRunner(this);
		} else if (mode.equals(ILaunchManager.DEBUG_MODE)) {
			return new TclInterpreterDebugger(this);
		} 
		
		//TODO: possible throw exception
		return null;
	}
	
	public String getNatureId() {
		return TclNature.NATURE_ID;
	}
}