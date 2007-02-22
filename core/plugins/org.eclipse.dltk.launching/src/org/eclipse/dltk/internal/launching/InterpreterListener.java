/*******************************************************************************
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.launching;

import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterInstallChangedListener;
import org.eclipse.dltk.launching.PropertyChangeEvent;

/**
 * Simple interpreter listener that reports whether interpreter settings have changed.
 */
public class InterpreterListener implements IInterpreterInstallChangedListener {
	
	private boolean changed = false;

	public void defaultInterpreterInstallChanged(IInterpreterInstall previous, IInterpreterInstall current) {
		changed = true;
	}

	public void interpreterAdded(IInterpreterInstall Interpreter) {
		changed = true;
	}

	public void interpreterChanged(PropertyChangeEvent event) {
		changed = true;
	}

	public void interpreterRemoved(IInterpreterInstall Interpreter) {
		changed = true;
	}

	public boolean isChanged() {
		return changed;
	}
}
