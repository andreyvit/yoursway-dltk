/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.debug.ui.actions;

import java.util.Iterator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.debug.core.model.IScriptBreakpoint;
import org.eclipse.dltk.debug.core.model.IScriptMethodEntryBreakpoint;
import org.eclipse.jface.viewers.IStructuredSelection;

public class EntryToggleAction extends BreakpointToggleAction {

	/**
	 * @see BreakpointToggleAction#getToggleState(IScriptBreakpoint)
	 */
	protected boolean getToggleState(IScriptBreakpoint breakpoint)
			throws CoreException {
		return ((IScriptMethodEntryBreakpoint) breakpoint).shouldBreakOnEntry();
	}

	/**
	 * @see BreakpointToggleAction#doAction(IScriptBreakpoint)
	 */
	public void doAction(IScriptBreakpoint breakpoint) throws CoreException {
		((IScriptMethodEntryBreakpoint) breakpoint)
				.setBreakOnEntry(!((IScriptMethodEntryBreakpoint) breakpoint)
						.shouldBreakOnEntry());
	}

	/**
	 * @see BreakpointToggleAction#isEnabledFor(IStructuredSelection)
	 */
	public boolean isEnabledFor(IStructuredSelection selection) {
		Iterator iter = selection.iterator();
		while (iter.hasNext()) {
			Object element = iter.next();
			if (!(element instanceof IScriptMethodEntryBreakpoint)) {
				return false;
			}

		}
		return true;
	}
}
